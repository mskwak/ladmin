package com.daou.ladmin.entity;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class LogMonitoringId implements Serializable {
	private static final long serialVersionUID = -8341947519144590887L;
	private String tmTime;
	private String senderIp;
	private String hostId;

	public LogMonitoringId() {
		super();
	}
}
