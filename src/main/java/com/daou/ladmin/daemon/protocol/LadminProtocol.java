package com.daou.ladmin.daemon.protocol;

import java.util.Map;

public interface LadminProtocol {
	public String execute(Map<String, String> map);
	public boolean isClose();
}
