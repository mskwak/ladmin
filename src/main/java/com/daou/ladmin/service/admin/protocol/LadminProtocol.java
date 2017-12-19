package com.daou.ladmin.service.admin.protocol;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.daou.ladmin.config.Constants;

public interface LadminProtocol {
	public String execute(Map<String, String> map);
	public boolean isClose();

	public static Map<String, String> parse(String command) {
		Map<String, String> map = new HashMap<String, String>();

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

	public static String getResponse(Constants... args) {
		List<String> list = Arrays.asList(args).stream().map(arg -> arg.getValue()).collect(Collectors.toList());
		return getResponse(list.toArray(new String[list.size()]));
	}
}
