package com.daou.ladmin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class LogMonId implements Serializable {
	@Column(name = "tm_time")
	private String tmTime;

	@Column(name = "sender_ip")
	private String senderIp;

	@Column(name = "host_id")
	private String hostId;
}
