package com.daou.ladmin.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sf_sysq_logmonitoring")
@Getter
@Setter
@IdClass(LogMonitoringId.class)
public class LogMonitoring {
	@Id
	private String tmTime;
	private String ehloDomain;
	@Id
	private String senderIp;
	private String senderEmail;
	private String senderDomain;
	private String fromEmail;
	private String messageId;
	private String sessionId;
	@Id
	private String hostId;
	private BigDecimal msgSize;
	private String msgSubject;
	private String fileName;
	private String receiverEmail;
	private String receiverDomain;
	private String msgType1;
	private String msgType2;
	private String msgType3;
	private String filterType;
	private String msgAct;
	private String actReason;
	private String bound;
	private String actDomain;
	private String box;
	private String procAct;

	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}

		if(o == null || getClass() != o.getClass()) {
			return false;
		}

		return (this == o);
	}

	//Predicate<String> predicate = (String s) -> s.equalsIgnoreCase("test");
}
