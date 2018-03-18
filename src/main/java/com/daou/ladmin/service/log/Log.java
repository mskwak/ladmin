package com.daou.ladmin.service.log;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Component
public enum Log {
	INSTANCE;

	private static final Logger logger = LoggerFactory.getLogger(Log.class);

	private static final String[] logs = {"tmtad", "tmss-routed", "tremoted", "t4imapd", "pop", "catalina/webmail", "gopush", "gonote"};
	//private static final String[] logs = Arrays.stream(Daemons.values()).map()
	//private static final String[] logs = Stream.of(Daemons.val
	//private static final List<String> logs = Lists.of("tmtad", "tmss-routed", "tremoted", "t4imapd", "pop", "catalina/webmail", "gopush", "gonote");



	private static final ConcurrentHashMap<String, Integer> m = new ConcurrentHashMap<>(logs.length);

	static {
		List<String> list = Stream.of(Daemons.values()).map(Daemons::getValue).collect(Collectors.toList());

		list.stream().forEach(l -> {
			System.out.println(l);
		});

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
