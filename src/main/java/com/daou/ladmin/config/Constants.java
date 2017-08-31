package com.daou.ladmin.config;

public enum Constants {
	TAG("*"),
	OK("OK"),
	NO("NO"),
	BAD("BAD"),
	CRLF("\r\n"),
	VERSION("1.0"),
	//GREETING("sf_ladmd dbagt service ready protocol-version:"),
	GREETING_MESSAGE("* OK greeting sf_ladmd dbagt service ready protocol-version:1.0"),
	TIMEOUT_MESSAGE("request timeout. server die."),
	INVALID_COMMAND("invalid command.");

	private String value;

	public String getValue() {
		return this.value;
	}

	private Constants(String value) {
		this.value = value;
	}
}
