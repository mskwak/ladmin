package com.daou.ladmin.util;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimeUtils {
	private TimeUtils() {}

	private static final Logger logger = LoggerFactory.getLogger(TimeUtils.class);

	public static void sleepSecond(long s) {
		try {
			TimeUnit.SECONDS.sleep(s);
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}
}
