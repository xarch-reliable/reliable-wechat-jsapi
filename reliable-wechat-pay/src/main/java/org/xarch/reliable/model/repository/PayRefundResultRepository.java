package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.result.WxPayRefundResult;

public interface PayRefundResultRepository extends JpaRepository<WxPayRefundResult, Long> {
	
	WxPayRefundResult findByOutTradeNo(String prepay_id);
}
