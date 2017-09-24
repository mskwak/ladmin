package com.daou.ladmin.service.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.daou.ladmin.config.LadminConfig;
import com.daou.ladmin.service.LadminService;

import io.netty.bootstrap.ServerBootstrap;
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
	@Qualifier("bossGroup")
	private NioEventLoopGroup bossGroup;

	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup workerGroup;

	@Autowired
	private AdminInitializer adminInitializer;

	@Override
	public void startService() {
		System.out.println("starting AdminService");

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(this.bossGroup, this.workerGroup)
		    .channel(NioServerSocketChannel.class)
		    .option(ChannelOption.SO_REUSEADDR, true)
		    .option(ChannelOption.SO_BACKLOG, 32)
		    .handler(new LoggingHandler(LogLevel.INFO))
		    .childHandler(this.adminInitializer);

			b.bind(this.ladminConfig.getBossTcpPort()).sync().channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error("", e);
		} finally {
			this.bossGroup.shutdownGracefully();
			this.workerGroup.shutdownGracefully();
		}
	}

	@Override
	public void stopService() {
		this.bossGroup.shutdownGracefully();
		this.workerGroup.shutdownGracefully();
	}
}
