package com.daou.ladmin.service.admin.protocol;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.ladmin.config.Constants;

@Service(value = "help")
public class Help implements LadminProtocol {
	@Autowired
	Map<String, LadminProtocol> ladminProtocolMap;

	@Override
	public String execute(Map<String, String> map) {
		StringBuffer sb = new StringBuffer();

		ladminProtocolMap.keySet().stream().sorted().forEach(str -> {
			sb.append(Constants.TAG.getValue()).append(" ").append(str).append("\r\n");
		});

//		Protocol.getMap().keySet().stream().sorted().forEach(str -> {
//			sb.append(Constants.TAG.getValue()).append(" ").append(str).append("\r\n");
//		});

		String tag = map.get("tag");
		String protocol = map.get("protocol");

		return sb.append(LadminProtocol.getOkResponse(tag, protocol)).toString();
	}

	@Override
	public boolean isClose() {
		return false;
	}
}
