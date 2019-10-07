package org.xarch.reliable.service.thread;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.xarch.reliable.service.JoinQrCodeService;

@Component
public class ThreadPool {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	
	@Autowired
	private JoinQrCodeService joinQrCodeService;
	
	@Async("asyncExecutor")
	public void DeleteFolder(String sPath) {
		File file = new File(sPath);
	    // 路径为文件且不为空则进行删除
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        logger.info("文件删除成功"+sPath);
	    }
	    return;
	}
	
	@Async("asyncExecutor")
	public void CreateShareQrCode(String actid) {
		joinQrCodeService.createForeverTicket(actid);
	    return;
	}
}
