package com.daou.ladmin.service.stat;

import org.springframework.stereotype.Service;

import com.daou.ladmin.service.LadminService;

@Service
public class StatService implements LadminService {
	@Override
	public void startService() {
		System.out.println("starting StatService");
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub
	}
}
