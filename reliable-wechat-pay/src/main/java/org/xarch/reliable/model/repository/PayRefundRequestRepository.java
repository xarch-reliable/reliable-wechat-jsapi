package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.request.WxPayRefundRequest;

public interface PayRefundRequestRepository extends JpaRepository<WxPayRefundRequest, Long> {
	
	WxPayRefundRequest findByOutTradeNo(String out_trade_no);
}
