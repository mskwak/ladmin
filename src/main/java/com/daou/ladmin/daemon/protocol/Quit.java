package com.daou.ladmin.daemon.protocol;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.daou.ladmin.util.LadminProtocolUtils;

@Component
public class Quit implements LadminProtocol {

	@Override
	public String execute(Map<String, String> map) {
		String tag = map.get("tag");
		String protocol = map.get("protocol");
		return LadminProtocolUtils.getOkResponse(tag, protocol);
	}

	@Override
	public boolean isClose() {
		return true;
	}

	public Quit() {
		super();
	}
}
