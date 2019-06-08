package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.result.WxPayUnifiedOrderResult;

public interface PayUOResultRepository extends JpaRepository<WxPayUnifiedOrderResult, Long> {
	
	WxPayUnifiedOrderResult findByPrepayId(String prepay_id);
}
