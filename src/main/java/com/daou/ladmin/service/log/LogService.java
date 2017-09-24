package com.daou.ladmin.service.log;

import java.util.concurrent.ExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.ladmin.config.LadminConfig;
import com.daou.ladmin.service.LadminService;

@Service
public class LogService implements LadminService {
	@Autowired
	private LadminConfig ladminConfig;

	@Autowired
	private ExecutorService executorService;

	@Autowired
	private LogReader logReader;

	@Override
	public void startService() {
		System.out.println("starting LogService");

		Log.INSTANCE.getList().stream().forEach(dir -> {
			//Assert.notNull(dir, "log must not be null");
			executorService.execute(logReader);
		});
	}

	@Override
	public void stopService() {
		executorService.shutdownNow();
	}
}
