package com.daou.ladmin;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.handler.timeout.WriteTimeoutHandler;

@Sharable
public class Test extends WriteTimeoutHandler {

	public Test(int timeoutSeconds) {
		super(timeoutSeconds);
		// TODO Auto-generated constructor stub
	}
}
