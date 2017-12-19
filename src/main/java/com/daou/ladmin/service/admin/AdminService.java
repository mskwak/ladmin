package com.daou.ladmin.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daou.ladmin.config.LadminConfig;
import com.daou.ladmin.service.LadminService;
import com.daou.ladmin.util.TimeUtils;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Service
public class AdminService implements LadminService {
	private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

	@Autowired
	private LadminConfig ladminConfig;

	@Autowired
	private NioEventLoopGroup bossGroup;

	@Autowired
	private NioEventLoopGroup workerGroup;

	@Autowired
	private AdminInitializer adminInitializer;

	@Override
	public void startService() {
		logger.info("starting AdminService");

		ServerBootstrap serverBootstrap = new ServerBootstrap();

		serverBootstrap.group(this.bossGroup, this.workerGroup)
			.channel(NioServerSocketChannel.class)
			.option(ChannelOption.SO_REUSEADDR, true)
			.option(ChannelOption.SO_BACKLOG, 32)
			.handler(new LoggingHandler(LogLevel.DEBUG))
			.childHandler(this.adminInitializer);

		//bind 작업이 진행 중이지만 bindChannelFuture 객체가 바로 리턴된다.
		ChannelFuture bindChannelFuture = serverBootstrap.bind(this.ladminConfig.getBossTcpPort());

		while(true) {
			if(bindChannelFuture.isDone()) {
				//ChannelFuture closeChannelFuture = bindChannelFuture.channel().closeFuture().sync();
				while(true) {
					ChannelFuture closeChannelFuture = bindChannelFuture.channel().closeFuture();

					if(closeChannelFuture.isDone()) {
						// channel이 close 되면 이 부분에 있는 코드가 실행된다.
						logger.info("channel is closed.");
					} else {
						// channel이 open된 상태에서 무언가 다른 작업을 하고자 할 경우 이 곳에 코딩한다.
						//logger.info("channel is active. I can do other job as parent here.");
						TimeUtils.sleepSecond(1);
					}
				}
			} else {
				//bind 작업이 진행 중인 와중에 무언가 다른 작업을 하고자 할 경우, 이 곳에 코딩한다.
				//logger.info("bind is in progress. I can do other job as parent here.");
			}
		}
	}

	@Override
	public void stopService() {
		this.bossGroup.shutdownGracefully();
		this.workerGroup.shutdownGracefully();
	}
}
