package com.daou.ladmin.daemon.protocol;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daou.ladmin.config.Constants;
import com.daou.ladmin.util.LadminProtocolUtils;

@Component(value="help")
public class Help implements LadminProtocol {
	@Autowired
	private Map<String, LadminProtocol> ladminProtocolMap;

	@Override
	public String execute(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();

		this.ladminProtocolMap.keySet().stream().sorted().forEach(str -> {
			sb.append(Constants.TAG.getValue()).append(" ").append(str).append("\r\n");
		});

		String tag = map.get("tag");
		String protocol = map.get("protocol");

		return sb.append(LadminProtocolUtils.getResponse(tag, Constants.OK.getValue(), protocol)).toString();
	}

	@Override
	public boolean isClose() {
		return false;
	}
}
