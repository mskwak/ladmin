package com.daou.ladmin.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringBean implements ApplicationContextAware {
	private static ApplicationContext context;

	private SpringBean() {}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> requiredType) {
		return context.getBean(requiredType);
	}

	public static Object getBean(String name) {
		return context.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return context.getBean(name, requiredType);
	}
}
