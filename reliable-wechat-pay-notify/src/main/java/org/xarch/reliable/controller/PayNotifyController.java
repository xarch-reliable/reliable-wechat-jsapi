package org.xarch.reliable.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xarch.reliable.service.WxPayNotifyService;

@RestController
@RequestMapping("/pay/notify")
public class PayNotifyController {

	@Autowired
	private WxPayNotifyService wxPayNotifyService;

	@RequestMapping("/unified")
	public String PayOrderNotify(@RequestBody String notifyStr) {
		return wxPayNotifyService.ProcessOrderNotify(notifyStr);
	}

	@RequestMapping("/refund")
	public String PayRefundNotify(@RequestBody String notifyStr) {
		return wxPayNotifyService.ProcessRefundNotify(notifyStr);
	}
}
