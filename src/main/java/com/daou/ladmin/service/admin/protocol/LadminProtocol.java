package com.daou.ladmin.service.admin.protocol;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.daou.ladmin.config.Constants;
import com.daou.ladmin.util.SpringBean;

public interface LadminProtocol {
	public String execute(Map<String, String> map);
	public boolean isClose();

	public static Map<String, String> parse(String command) {
		Map<String, String> map = new HashMap<>();

		if(StringUtils.isEmpty(command)) {
			return map;
		}

		String[] array = StringUtils.split(command, null, 3);

		if(array.length <= 1) {
			return map;
		}

		if(array.length > 1) {
			map.put("tag", array[0].toLowerCase());
			map.put("protocol", array[1].toLowerCase());
		}

		if(array.length > 2) {
			map.put("args", array[2].toLowerCase());
		}

		return map;
	}

	public static String getBadResponse(Constants constants) {
		return getResponse(Constants.TAG, Constants.BAD, constants);
	}

	public static String getBadResponse(String tag, Constants constants) {
		return getResponse(tag, Constants.BAD.getValue(), constants.getValue());
	}

	public static String getOkResponse(String tag, String protocol) {
		return getResponse(tag, Constants.OK.getValue(), protocol);
	}

	public static String getResponse(String... args) {
		StringBuilder stringBuilder = new StringBuilder();

		Arrays.asList(args).stream().forEach(list -> {
			stringBuilder.append(list).append(" ");
		});

		return stringBuilder.append(Constants.CRLF.getValue()).toString();
	}

	public static String getResponse2(String... args) {
		StringBuilder stringBuilder = new StringBuilder();
		String str = Arrays.asList(args).stream().collect(Collectors.joining(" "));

		return stringBuilder.append(str).append(Constants.CRLF.getValue()).toString();
	}

	public static String getResponse(Constants... args) {
		List<String> list = Arrays.asList(args).stream().map(arg -> arg.getValue()).collect(Collectors.toList());
		//List<String> list2 = Arrays.asList(args).stream().map(Constants::getValue).collect(Collectors.toList());
		return getResponse(list.toArray(new String[list.size()]));
	}

	public static int getProtocolNameLength() {
		Map<String, LadminProtocol> m = SpringBean.getBeansOfType(LadminProtocol.class);
		Optional<String> longestString = m.keySet().stream().max(Comparator.comparingInt(String::length));
		return (longestString.isPresent()) ? longestString.get().length() + 4 : Byte.toUnsignedInt(Byte.MAX_VALUE);
	}
}
