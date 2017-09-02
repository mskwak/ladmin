package com.daou.ladmin.daemon.protocol;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Protocol {
	private static Map<String, LadminProtocol> map = new HashMap<String, LadminProtocol>();

	static {
		map.put("help", new Help());
		map.put("quit", new Quit());
		map.put("config_syncallwithdb", new ConfigSyncAllWithDb());
	}

	public static Map<String, LadminProtocol> getMap() {
		return map;
	}
}
