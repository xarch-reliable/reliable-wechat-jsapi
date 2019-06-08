package org.xarch.reliable.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xarch.reliable.model.domain.notify.WxPayOrderNotifyResult;

public interface PayOrderNotifyRepository extends JpaRepository<WxPayOrderNotifyResult, Long> {
	
	WxPayOrderNotifyResult findByOutTradeNo(String out_trade_no);
}
