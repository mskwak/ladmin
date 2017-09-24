package com.daou.ladmin;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.ladmin.service.LadminService;

@Service
public class LaunchService {
	@Autowired
	List<LadminService> list;

	public void launch() {
		Collections.reverse(list);

		list.stream().forEach(action -> {
			action.startService();
		});
	}

	public void stop() {
		list.stream().forEach(action -> {
			action.stopService();
		});
	}
}