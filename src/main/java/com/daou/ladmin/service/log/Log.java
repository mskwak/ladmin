package com.daou.ladmin.service.log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public enum Log {
	INSTANCE;

	private static final Logger logger = LoggerFactory.getLogger(Log.class);

	private static final String[] logs = {"tmtad", "tmss-routed", "tremoted", "t4imapd", "pop", "catalina/webmail", "gopush", "gonote"};

	private static final ConcurrentHashMap<String, Integer> m = new ConcurrentHashMap<>(logs.length);

	static {
		Arrays.stream(logs).forEach(log -> {
			m.put(log, 0);
		});
	}

	public synchronized String getLog() {
//		try {
//			String log = m.keySet().stream()
//					.filter(k -> m.get(k) == 0)
//					.findAny().get();
//			m.put(log, 1);
//
//			return log;
//		} catch (NoSuchElementException e) {
//			logger.error("", e);
//		}

//		String log =  m.keySet().stream()
//				.filter(k -> m.get(k) == 0)
//				.findAny().orElse(null);
//		m.put(log, 1);
//
//		return log;

		String log =  m.keySet().stream()
				.filter(k -> m.get(k) == 0)
				.findAny().orElseThrow(() -> new IllegalArgumentException()); //IllegalArgumentException, RuntimeException
		m.put(log, 1);

		return log;
	}

	public List<String> getList() {
		return Arrays.asList(logs);
	}

	public int getCount() {
		return Math.toIntExact(Arrays.asList(logs).stream().count());
	}
}
