package com.daou.ladmin;

import org.junit.Test;
import org.springframework.util.Assert;

public class Test1 {
	@Test
	public void test1() {
		Assert.notNull(null, "must not be null");

		while(true) {
			try {
				System.out.println("hello");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
