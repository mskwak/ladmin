package com.daou.ladmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.daou.ladmin.config.LadminConfig;
import com.daou.ladmin.daemon.LadminInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

@Service
public class LadminService {
	@Autowired
	private LadminConfig ladminConfig;

	@Autowired
	@Qualifier("bossGroup")
	private NioEventLoopGroup bossGroup;

	@Autowired
	@Qualifier("workerGroup")
	private NioEventLoopGroup workerGroup;

	@Autowired
	private LadminInitializer ladminInitializer;

	public void startService() throws InterruptedException {
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(this.bossGroup, this.workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_REUSEADDR, true)
            .option(ChannelOption.SO_BACKLOG, 32)
            .handler(new LoggingHandler(LogLevel.INFO))
            .childHandler(this.ladminInitializer);

            b.bind(this.ladminConfig.getBossTcpPort()).sync().channel().closeFuture().sync();

		} finally {
			this.bossGroup.shutdownGracefully();
			this.workerGroup.shutdownGracefully();
		}
	}

	public void stopService() {
		this.bossGroup.shutdownGracefully();
		this.workerGroup.shutdownGracefully();
	}
}
