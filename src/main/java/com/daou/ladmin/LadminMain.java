package com.daou.ladmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LadminMain implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(LadminMain.class);

	@Autowired
	LaunchService launchService;

	public static void main(String[] args) {
//		String configLocation = "classpath:/ladmin.xml";
//		FileSystemXmlApplicationContext fileSystemXmlApplicationContext = new FileSystemXmlApplicationContext(configLocation);
//		LaunchService launchService = fileSystemXmlApplicationContext.getBean(LaunchService.class);
//
//		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//			launchService.stop();
//			fileSystemXmlApplicationContext.close();
//		}));
//
//		launchService.launch();

		SpringApplication.run(LadminMain.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			launchService.stop();
		}));


		launchService.launch();
	}
}
