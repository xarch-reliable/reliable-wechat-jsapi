package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.service.DistributionService;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.utils.BaseResultTools;
import org.xarch.reliable.utils.RandomUtil;

@Service
public class DistributionServiceImpl implements DistributionService {

	private static final Logger logger = LoggerFactory.getLogger(DistributionServiceImpl.class);
	
	/**
	 * 这里为了避免某一个红包占用大量资金，我们需要设定非最后一个红包的最大金额，
	 * 我们把他设置为红包金额平均值的N倍
	 */
	private static final Integer TIMES = 3;
	
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private FeignDataManager feignDataManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean RandomDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid) {
		
		Integer count = ReliableMap.size();

		//每个红包的最大的金额为平均金额的TIMES倍
		Integer max = sumTotalFee*TIMES/count;

		//分配红包
		for (Entry<String, Object> entry: ReliableMap.entrySet()) {
			int reliableMoney = RandomUtil.randomRedPacket(sumTotalFee, 1, max, count);
			sumTotalFee -= reliableMoney;
			count--;
			
			Map<String, Object> sendpayidmap2 = new HashMap<String, Object>();
			sendpayidmap2.put("xrdataction", "getpayid");
			Map<String, Object> payidmap2 = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap2).get("body");
			String payid2 = (String)payidmap2.get("payid");
			if (payid2 == null) {
				return false;
			}
			
			Map<String, Object> pay2usermap = new HashMap<String, Object>();
			pay2usermap.put("openid", entry.getKey());
			pay2usermap.put("partner_trade_no", payid2);
			pay2usermap.put("check_name", "NO_CHECK");
			pay2usermap.put("re_user_name", "靠谱达人");
			pay2usermap.put("amount", String.valueOf(reliableMoney));
			pay2usermap.put("desc", "靠谱金");
			rabbitTemplate.convertAndSend("pay.exchange", "pay.touser.test", BaseResultTools.JsonObjectToStr(pay2usermap));
			
			Map<String, Object> billmap = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("openid", entry.getKey());
			data.put("actid", actid);
			data.put("reliableMoney", String.valueOf(reliableMoney));
			billmap.put("xrdataction", "setBillinfo");
			billmap.put("data", data);
			feignDataManager.doSupport2DataCenter(billmap);
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean EqualDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid) {
		
		Integer reliableMoney = sumTotalFee/(ReliableMap.size());
		Map<String, Object> sendmap = new HashMap<String, Object>();
		
		for (Entry<String, Object> entry: ReliableMap.entrySet()) {
			
			Map<String, Object> sendpayidmap1 = new HashMap<String, Object>();
			sendpayidmap1.put("xrdataction", "getpayid");
			Map<String, Object> payidmap1 = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap1).get("body");
			String payid1 = (String)payidmap1.get("payid");
			if (payid1 == null) {
				return false;
			}

			Map<String, Object> datatmp = new HashMap<String, Object>();
			datatmp.put("out_trade_no", entry.getValue());
			sendmap.put("data", datatmp);
			Map<String, Object> getTotalFeemap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
			String totalFee = (String)getTotalFeemap.get("total_fee");
			Map<String, Object> refundmap = new HashMap<String, Object>();
			refundmap.put("out_trade_no", (String)entry.getValue());
			refundmap.put("out_refund_no", payid1);
			refundmap.put("total_fee", totalFee);
			refundmap.put("refund_fee", totalFee);
			rabbitTemplate.convertAndSend("pay.exchange", "refund.touser.test", BaseResultTools.JsonObjectToStr(refundmap));

			
			Map<String, Object> sendpayidmap2 = new HashMap<String, Object>();
			sendpayidmap2.put("xrdataction", "getpayid");
			Map<String, Object> payidmap2 = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap2).get("body");
			String payid2 = (String)payidmap2.get("payid");
			if (payid2 == null) {
				return false;
			}
			
			Map<String, Object> pay2usermap = new HashMap<String, Object>();
			pay2usermap.put("openid", entry.getKey());
			pay2usermap.put("partner_trade_no", payid2);
			pay2usermap.put("check_name", "NO_CHECK");
			pay2usermap.put("re_user_name", "靠谱达人");
			pay2usermap.put("amount", String.valueOf(reliableMoney));
			pay2usermap.put("desc", "靠谱金");
			rabbitTemplate.convertAndSend("pay.exchange", "pay.touser.test", BaseResultTools.JsonObjectToStr(pay2usermap));
			
			Map<String, Object> billmap = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("openid", entry.getKey());
			data.put("actid", actid);
			data.put("reliableMoney", String.valueOf(reliableMoney));
			billmap.put("xrdataction", "setBillinfo");
			billmap.put("data", data);
			feignDataManager.doSupport2DataCenter(billmap);
			
		}

		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean SeparateDistribution(Map<String, Object> ReliableMap, Integer sumTotalFee, String actid) {
		String[] keys = new String[ReliableMap.size()];
		ReliableMap.keySet().toArray(keys);
		Random random = new Random();
		String randomKey = keys[random.nextInt(keys.length)];
		logger.info("大奖openid :" + randomKey);
		
		Map<String, Object> sendpayidmap2 = new HashMap<String, Object>();
		sendpayidmap2.put("xrdataction", "getpayid");
		Map<String, Object> payidmap2 = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap2).get("body");
		String payid2 = (String)payidmap2.get("payid");
		if (payid2 == null) {
			return false;
		}
		
		Map<String, Object> pay2usermap = new HashMap<String, Object>();
		pay2usermap.put("openid", randomKey);
		pay2usermap.put("partner_trade_no", payid2);
		pay2usermap.put("check_name", "NO_CHECK");
		pay2usermap.put("re_user_name", "靠谱达人");
		pay2usermap.put("amount", String.valueOf(sumTotalFee));
		pay2usermap.put("desc", "靠谱金");
		rabbitTemplate.convertAndSend("pay.exchange", "pay.touser.test", BaseResultTools.JsonObjectToStr(pay2usermap));
		
		Map<String, Object> billmap = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid", randomKey);
		data.put("actid", actid);
		data.put("reliableMoney", String.valueOf(sumTotalFee));
		billmap.put("xrdataction", "setBillinfo");
		billmap.put("data", data);
		feignDataManager.doSupport2DataCenter(billmap);
		return true;
	}

}
