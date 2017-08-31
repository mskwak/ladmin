package com.daou.ladmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class LadminMain {
	private static final Logger logger = LoggerFactory.getLogger(LadminMain.class);

	public static void main(String[] args) throws InterruptedException {
		String configLocation = "classpath:/ladmin.xml";
		FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(configLocation);
		LadminService ladminService = fileSystemXmlApplicationContext.getBean(LadminService.class);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			ladminService.stopService();
			fileSystemXmlApplicationContext.close();
		}));

		ladminService.startService();
	}
}
