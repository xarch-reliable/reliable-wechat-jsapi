package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.request.WxPayUnifiedOrderRequest;

public interface PayUORequestRepository extends JpaRepository<WxPayUnifiedOrderRequest, Long> {
	
	WxPayUnifiedOrderRequest findByOutTradeNo(String out_trade_no);
}
