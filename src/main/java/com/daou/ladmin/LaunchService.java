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
		// admin 데몬이 Listen 하는 유일한 데몬이다. listen 상태에서 block 하고 있기 때문에 무조건 다른 서비스(데몬)들 보다 늦게 (맨 마지막에) 실행되어야 한다.
		Collections.reverse(list);

		list.stream().forEach(s -> {
			s.startService();
		});
	}

	public void stop() {
		list.stream().forEach(s -> {
			s.stopService();
		});
	}
}