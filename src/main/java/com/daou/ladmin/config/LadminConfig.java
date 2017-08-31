package com.daou.ladmin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.daou.ladmin.Test;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Configuration
@ComponentScan("com.daou.ladmin")
@PropertySource("classpath:ladmin-config.properties")
public class LadminConfig {
	@Value("${base.dir}") private String baseDir;
	@Value("${boss.thread.count}") private int bossCount;
	@Value("${worker.thread.count}") private int workerCount;
	@Value("${boss.tcp.port}") private int bossTcpPort;
	@Value("${boss.io.timeout.second}") private int bossIoTimeout;
	@Value("${client.io.timeout.second}") private int clientIoTimeout;
	// 기본값 설정 방법 @Value("${boss.log.level:INFO}")
	@Value("${boss.log.level:INFO}") private String bossLogLevel;
	@Value("${worker.log.level:INFO}") private String workerLogLevel;

	public String getBaseDir() {
		return this.baseDir;
	}

	public int getBossCount() {
		return this.bossCount;
	}

	public int getWorkerCount() {
		return this.workerCount;
	}

	public int getBossTcpPort() {
		return this.bossTcpPort;
	}

	public int getBossIoTimeout() {
		return this.bossIoTimeout;
	}

	public int getClientIoTimeout() {
		return this.clientIoTimeout;
	}

	public String getBossLogLevel() {
		return this.bossLogLevel;
	}

	public String getWorkerLogLevel() {
		return this.workerLogLevel;
	}

	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(this.bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(this.workerCount);
	}

//	@Bean(name = "readTimeoutHandler")
//	public ReadTimeoutHandler readTimeoutHandler() {
//		return new ReadTimeoutHandler(this.bossIoTimeout);
//	}
//
//	@Bean(name = "writeTimeoutHandler")
//	public WriteTimeoutHandler writeTimeoutHandler() {
//		return new WriteTimeoutHandler(this.bossIoTimeout);
//	}
//
//	@Bean(name = "lineBasedFrameDecoder")
//	public LineBasedFrameDecoder lineBasedFrameDecoder() {
//		return new LineBasedFrameDecoder(1024 * 64);
//	}

	@Bean(name = "test")
	public Test test() {
		return new Test(this.bossIoTimeout);
	}

	@Bean(name = "stringDecoder")
	public StringDecoder stringDecoder() {
		return new StringDecoder();
	}

	@Bean(name = "stringEncoder")
	public StringEncoder stringEncoder() {
		return new StringEncoder();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
