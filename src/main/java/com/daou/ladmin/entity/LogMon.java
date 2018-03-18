package com.daou.ladmin.entity;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "sf_logmonmail")
@Getter
public class LogMon {
	@EmbeddedId
	private LogMonId logMonId;

	private String ehloDomain;
	private String senderEmail;
	private String senderDomain;
	private String fromEmail;
	private String messageId;
	private String sessionId;
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
}
