package com.daou.ladmin.service.log;

public enum Daemons {
	TMTAD("tmtad");

	private String value;

	public String getValue() {
		return this.value;
	}
	private Daemons(String value) {
		this.value = value;
	}
}
