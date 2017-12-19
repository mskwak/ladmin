package com.daou.ladmin.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class LogUtils {
	private LogUtils() {}

	public static Map<String, String> getKeyAndValue(String line) {
		String[] s = line.split(" SJ:", 2);
		Stream<String> str = Stream.of(s[0].split(" "));

		Map<String, String> m = new HashMap<String, String>();

		str.forEach(p -> {
			String[] kv = p.split(":");
			m.put(kv[0], kv[1]);
		});

		//TODO s[1] 존재하지 않을 수 있다
		m.put("SJ", s[1]);

		return m;
	}


	public static <T extends Runnable> Map<T, T> xxx(T  t) {
		t.run();
		return null;
	}

	public static <T> T yyy(Set<T> t) {
		return t.stream().findAny().get();
	}
}
