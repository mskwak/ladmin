package com.daou.ladmin.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daou.ladmin.config.LadminConfig;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;

@Component
public class AdminInitializer extends ChannelInitializer<SocketChannel> {
/*	아래 3개의 클래스에 대해서는 @Sharable 지정 불가
 *  io.netty.channel.ChannelPipelineException: io.netty.handler.timeout.ReadTimeoutHandler is not a @Sharable handler, so can't be added or removed multiple times.
 *  @Autowired
	private ReadTimeoutHandler readTimeoutHandler;

	@Autowired
	private WriteTimeoutHandler writeTimeoutHandler;

	@Autowired
	private LineBasedFrameDecoder lineBasedFrameDecoder;

	@Autowired
	private Test test;
*/
	@Autowired
	private LadminConfig ladminConfig;

	@Autowired
	private AdminHandler adminHandler;

	@Autowired
	private StringEncoder stringEncoder;

	@Autowired
	private StringDecoder stringDecoder;

	@Override
	public void initChannel(SocketChannel ch) {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new ReadTimeoutHandler(this.ladminConfig.getBossIoTimeout()));
		pipeline.addLast(new WriteTimeoutHandler(this.ladminConfig.getBossIoTimeout()));
		//pipeline.addLast(this.test);
		pipeline.addLast(new LineBasedFrameDecoder(64 * 1024));
		pipeline.addLast(this.stringEncoder);
		pipeline.addLast(this.stringDecoder);
		pipeline.addLast(this.adminHandler);
	}
}
