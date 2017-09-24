package com.daou.ladmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class LadminMain {
	private static final Logger logger = LoggerFactory.getLogger(LadminMain.class);

	public static void main(String[] args) throws InterruptedException {
		String configLocation = "classpath:/ladmin.xml";
		FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(configLocation);
		LaunchService launchService = fileSystemXmlApplicationContext.getBean(LaunchService.class);

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			launchService.stop();
			fileSystemXmlApplicationContext.close();
		}));

		launchService.launch();
	}
}
