package com.daou.ladmin.service.admin.protocol;

import java.util.Map;

import org.springframework.stereotype.Service;

@Service(value = "config_syncwithdb")
public class ConfigSyncWithDb implements LadminProtocol {

	@Override
	public String execute(Map<String, String> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClose() {
		// TODO Auto-generated method stub
		return false;
	}

}
