package org.xarch.reliable.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xarch.reliable.config.event.DistributionType;
import org.xarch.reliable.service.ClearServer;
import org.xarch.reliable.service.DistributionService;
import org.xarch.reliable.service.feign.FeignDataManager;
import org.xarch.reliable.utils.BaseResultTools;

@Service
public class ClearServerImpl implements ClearServer {
	
	private static final Logger logger = LoggerFactory.getLogger(ClearServerImpl.class);

	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Autowired
	private FeignDataManager feignDataManager;
	
	@Autowired
	private DistributionService distributionService;
	
	@Override
	public Map<String, Object> parseClearData(Map<String, Object> data) {
		Map<String, Object> reliableMap = BaseResultTools.ObjectToMap(data.get("ReliableMap"));
		Map<String, Object> unreliableMap = BaseResultTools.ObjectToMap(data.get("UnReliableMap"));
		String actid = (String)data.get("actid");
		String distribution = (String)data.get("distribution");
		if(reliableMap==null || unreliableMap==null || actid==null || distribution==null) {
			Map<String, Object> resmap = new HashMap<String, Object>();
			resmap.put("error_msg", "清分数据格式错误");
			return resmap;
		}
		return ClearProcess(reliableMap, unreliableMap, actid, distribution);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> ClearProcess(Map<String, Object> ReliableMap, Map<String, Object> UnReliableMap, String actid, String distribution) {
		
		Map<String, Object> resmap = new HashMap<String, Object>();
		
		Integer sumTotalFee = new Integer(0);
		
		Map<String, Object> sendmap = new HashMap<String, Object>();
		sendmap.put("xrdataction", "getOrderTotalFee");
		
		for (Entry<String, Object> entry: UnReliableMap.entrySet()) {
			Map<String, Object> datatmp = new HashMap<String, Object>();
			datatmp.put("out_trade_no", entry.getValue());
			sendmap.put("data", datatmp);
			Map<String, Object> getTotalFeemap = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendmap).get("body");
			logger.info("[total_fee] " + (String)getTotalFeemap.get("total_fee"));
			int reliablemoney = Integer.parseInt((String)getTotalFeemap.get("total_fee"));
			
			sumTotalFee += Integer.parseInt((String)getTotalFeemap.get("total_fee"));
			
			Map<String, Object> billmap = new HashMap<String, Object>();
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("openid", entry.getKey());
			data.put("actid", actid);
			data.put("reliableMoney", String.valueOf(-reliablemoney));
			billmap.put("xrdataction", "setBillinfo");
			billmap.put("data", data);
			feignDataManager.doSupport2DataCenter(billmap);
			logger.info("billmap"+billmap);
		}
		
		for (Entry<String, Object> entry: ReliableMap.entrySet()) {
			
			Map<String, Object> sendpayidmap1 = new HashMap<String, Object>();
			sendpayidmap1.put("xrdataction", "getpayid");
			Map<String, Object> payidmap1 = (Map<String, Object>)feignDataManager.doSupport2DataCenter(sendpayidmap1).get("body");
			String payid1 = (String)payidmap1.get("payid");
			if (payid1 == null) {
				resmap.put("error_msg", "退款payid获取失败");
				return resmap;
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
		}
		
		DistributionType distributionType = DistributionType.valueOf(distribution);
		boolean clearSuccess = false;
		switch (distributionType) {
		case random:
			clearSuccess = distributionService.RandomDistribution(ReliableMap, sumTotalFee, actid);
			break;
		case equal:
			clearSuccess = distributionService.EqualDistribution(ReliableMap, sumTotalFee, actid);
			break;
		case separate:
			clearSuccess = distributionService.SeparateDistribution(ReliableMap, sumTotalFee, actid);
			break;
		default:
			clearSuccess = distributionService.EqualDistribution(ReliableMap, sumTotalFee, actid);
			break;
		}
		if(clearSuccess) {
			resmap.put("success_msg", "清分成功");
		}else {
			resmap.put("error_msg", "清分失败");
		}
		
		return resmap;
	}

}
