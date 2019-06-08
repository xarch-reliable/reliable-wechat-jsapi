package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.notify.WxPayRefundNotifyReqInfo;

public interface PayRefundNotifyRepository extends JpaRepository<WxPayRefundNotifyReqInfo, Long> {
	
	WxPayRefundNotifyReqInfo findByOutTradeNo(String out_trade_no);
}
