package com.daou.ladmin.service.admin.protocol;

import java.util.Map;

public interface LadminProtocol {
	public String execute(Map<String, String> map);
	public boolean isClose();
}
