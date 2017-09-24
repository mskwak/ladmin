package com.daou.ladmin.service.alert;

import org.springframework.stereotype.Service;

import com.daou.ladmin.service.LadminService;

@Service
public class AlertService implements LadminService {
	@Override
	public void startService() {
		System.out.println("starting AlertService");
	}

	@Override
	public void stopService() {
		// TODO Auto-generated method stub

	}
}
