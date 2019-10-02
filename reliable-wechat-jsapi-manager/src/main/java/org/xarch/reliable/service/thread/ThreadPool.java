package org.xarch.reliable.service.thread;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadPool {
	
	private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);
	
	@Async("asyncExecutor")
	public static boolean DeleteFolder(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
	    // 路径为文件且不为空则进行删除
	    if (file.isFile() && file.exists()) {
	        file.delete();
	        logger.info("文件删除成功"+sPath);
	        flag = true;
	    }
	    return flag;
	}
}
