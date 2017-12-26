package com.daou.ladmin.service.admin.protocol;

import java.util.HashMap;
import java.util.Map;

import com.daou.ladmin.util.SpringBean;

@Deprecated
public class Protocol {
	private Protocol() {}

	private static Map<String, LadminProtocol> map = new HashMap<String, LadminProtocol>();

	static {
		map.put("help", SpringBean.getBean(Help.class));
		map.put("quit", SpringBean.getBean(Quit.class));
		map.put("config_syncallwithdb", SpringBean.getBean(ConfigSyncAllWithDb.class));
		map.put("config_syncwithdb", SpringBean.getBean(ConfigSyncWithDb.class));
		map.put("custom_pkg_file_upload", SpringBean.getBean(CustomPkgFileUpload.class));
		map.put("custom_pkg_patch", SpringBean.getBean(CustomPkgPatch.class));
		map.put("domain_delete", SpringBean.getBean(DomainDelete.class));
	}

	public static Map<String, LadminProtocol> getMap() {
		return map;
	}
}
