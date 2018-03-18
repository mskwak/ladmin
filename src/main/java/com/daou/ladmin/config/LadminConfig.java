package com.daou.ladmin.config;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.daou.ladmin.service.log.Log;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Getter;

@Configuration
@ComponentScan("com.daou.ladmin")
@Lazy
@EnableJpaRepositories(basePackages="com.daou.ladmin.repository")
@EnableTransactionManagement
//@PropertySources({
//	@PropertySource("classpath:ladmin-config.properties")
//})
//Caused by: org.springframework.beans.factory.BeanNotOfRequiredTypeException: Bean named 'logReader' is expected to be of type 'com.daou.ladmin.service.log.LogReader' but was actually of type 'com.sun.proxy.$Proxy49'
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class LadminConfig {
	private static final Logger logger = LoggerFactory.getLogger(LadminConfig.class);

	private static final String BASEDIR = "/opt/TerraceTims";

	@Value("${base.dir}")
	@Getter
	private String baseDir;

	@Value("${boss.thread.count}")
	@Getter
	private int bossCount;

	@Value("${worker.thread.count}")
	@Getter
	private int workerCount;

	@Value("${boss.tcp.port}")
	@Getter
	private int bossTcpPort;

	@Value("${boss.io.timeout.second}")
	@Getter
	private int bossIoTimeout;

	@Value("${client.io.timeout.second}")
	@Getter
	private int clientIoTimeout;

	// 기본값 설정 방법 @Value("${boss.log.level:INFO}")
	@Value("${boss.log.level:INFO}")
	@Getter
	private String bossLogLevel;

	@Value("${worker.log.level:INFO}")
	@Getter
	private String workerLogLevel;

//	public String getBaseDir() {
//		return this.baseDir;
//	}
//
//	public int getBossCount() {
//		return this.bossCount;
//	}
//
//	public int getWorkerCount() {
//		return this.workerCount;
//	}
//
//	public int getBossTcpPort() {
//		return this.bossTcpPort;
//	}
//
//	public int getBossIoTimeout() {
//		return this.bossIoTimeout;
//	}
//
//	public int getClientIoTimeout() {
//		return this.clientIoTimeout;
//	}
//
//	public String getBossLogLevel() {
//		return this.bossLogLevel;
//	}
//
//	public String getWorkerLogLevel() {
//		return this.workerLogLevel;
//	}



	@Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup bossGroup() {
		return new NioEventLoopGroup(this.bossCount);
	}

	@Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
	public NioEventLoopGroup workerGroup() {
		return new NioEventLoopGroup(this.workerCount);
	}

	@Bean(name = "executorService", destroyMethod = "shutdownNow")
	public ExecutorService executorService() {
		//return Executors.newCachedThreadPool();
		return Executors.newFixedThreadPool(Log.INSTANCE.getCount());
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

	public String getLogDir() {
		File file = new File(getBaseDir() + "/config/log.config");

		try {
			return FileUtils.readLines(file, "UTF-8").stream().filter(line -> StringUtils.startsWithIgnoreCase(line, "basedir")).map(line -> {
				String[] str = StringUtils.split(line, null, 2);
				return str[1];
			}).findAny().get();
		} catch (IOException e) {
			logger.error("", e);
		}
		return BASEDIR + "/log";
	}

//	@Bean(name = "test")
//	public Test test() {
//		return new Test(this.bossIoTimeout);
//	}

	@Bean(name = "stringDecoder")
	public StringDecoder stringDecoder() {
		return new StringDecoder();
	}

	@Bean(name = "stringEncoder")
	public StringEncoder stringEncoder() {
		return new StringEncoder();
	}

	/*
	 * DB configuration
	 */
	@Value("${datasource.driver.classname}") private String driverClassName;
	@Value("${datasource.url}") private String url;
	@Value("${datasource.username}") private String userName;
	@Value("${datasource.password}") private String password;
	@Value("${packages.to.scan}") private String packagesToScan;
	@Value("${hibernate.dialect}") private String dialect;

//	@Bean(destroyMethod = "close")
//	public DataSource dataSource() {
//		BasicDataSource dataSource = new BasicDataSource();
//		//DataSource dataSource = new DataSource();
//
//		dataSource.setDriverClassName(this.driverClassName);
//		dataSource.setUrl(this.url);
//		dataSource.setUsername(this.userName);
//		dataSource.setPassword(this.password);
//		dataSource.setInitialSize(1);
//		// Method org.postgresql.jdbc4.Jdbc4Connection.isValid(int) is not yet implemented
//		// basicDataSource.setValidationQuery("SELECT 1");
//
//		return dataSource;
//	}

//	@Bean
//	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//		Properties jpaProperties = new Properties();
//		// http://docs.jboss.org/hibernate/orm/4.3/manual/en-US/html_single/#configuration-optional-dialects 지원 가능한 방언 리스트
//		//jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
//		jpaProperties.put("hibernate.dialect", this.dialect);
//
//		// 하이버네이트가 실행한 SQL 쿼리를 출력한다.
//		jpaProperties.put("hibernate.show_sql", true);
//
//		// 하이버네이트가 실행한 SQL 쿼리를 출력할 때 보기 쉽게 정렬한다.
//		jpaProperties.put("hibernate.format_sql", true);
//
//		// 쿼리를 출력할 때 주석도 함께 출력한다.
//		jpaProperties.put("hibernate.use_sql_comments", true);
//
//		// 이 클래스는 테이블 명이나 컬럼 명이 생략되면 자바의 카멜 표기법을 테이블의 언더스코어 표기법으로 매핑한다.
//		// -> 설정이 안 먹힌다.
//		jpaProperties.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
//		//jpaProperties.put("hibernate.ejb.naming_strategy", "ImprovedNamingStrategy.INSTANCE");
//
//		// JPA 표준에 맞춘 새로운 키 생성 전략을 사용한다.
//		jpaProperties.put("hibernate.id.new_generator_mappings", true);
//
//		// p.127 자바 ORM 표준 JPA 프로그래밍
//		// create: 기존 테이블을 삭제하고 새로 생성한다. DROP + CREATE
//		// create-drop: 애플리케이션을 종료할 때 생성한 DDL을 제거한다. DROP + CREATE + DROP
//		// update: 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 변경 사항만 수정한다.
//		// validate: 데이터베이스 테이블과 엔티티 매핑정보를 비교해서 차이가 있으면 경고를 남기고 애플리케이션을 실행하지 않는다. 이 설정은 DDL을 수정하지 않는다.
//		// none: 자동 생성기능을 사용하지 않는다.
//		jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
//
//		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//		localContainerEntityManagerFactoryBean.setDataSource(this.dataSource());
//		localContainerEntityManagerFactoryBean.setPackagesToScan(this.packagesToScan);
//		localContainerEntityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//		localContainerEntityManagerFactoryBean.setJpaProperties(jpaProperties);
//
//		return localContainerEntityManagerFactoryBean;
//	}

//	@Bean
//	public PlatformTransactionManager transactionManager() {
//		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//		jpaTransactionManager.setEntityManagerFactory(this.entityManagerFactory().getObject());
//		return jpaTransactionManager;
//	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
