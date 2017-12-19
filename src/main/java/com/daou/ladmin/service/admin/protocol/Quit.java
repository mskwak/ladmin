package com.daou.ladmin.service.admin.protocol;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Quit implements LadminProtocol {

	@Override
	public String execute(Map<String, String> map) {
		String tag = map.get("tag");
		String protocol = map.get("protocol");
		return LadminProtocol.getOkResponse(tag, protocol);
	}

	@Override
	public boolean isClose() {
		return true;
	}

	public Quit() {
		super();
	}
}
