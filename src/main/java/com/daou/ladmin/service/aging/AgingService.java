package com.daou.ladmin.service.aging;

import org.springframework.stereotype.Service;

import com.daou.ladmin.service.LadminService;

@Service
public class AgingService implements LadminService {
	@Override
	public void startService() {
		System.out.println("start AgingService");
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub

	}
}
