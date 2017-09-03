package com.daou.ladmin.daemon.protocol;

import java.util.HashMap;
import java.util.Map;

import com.daou.ladmin.util.SpringBean;

public class Protocol {
	private static Map<String, LadminProtocol> map = new HashMap<String, LadminProtocol>();

	static {
//		map.put("help", new Help());
//		map.put("quit", new Quit());
//		map.put("config_syncallwithdb", new ConfigSyncAllWithDb());

		map.put("help", SpringBean.getBean(Help.class));
		map.put("quit", SpringBean.getBean(Quit.class));
		map.put("config_syncallwithdb", SpringBean.getBean(ConfigSyncAllWithDb.class));
	}

	public static Map<String, LadminProtocol> getMap() {
		return map;
	}
}
