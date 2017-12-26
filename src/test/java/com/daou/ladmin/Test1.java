package com.daou.ladmin;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.Assert;

public class Test1 {
	@Test
	public void test1() {
		Assert.notNull(null, "must not be null");

		List<? extends Number> foo1 = new ArrayList<Number>();  // Number "extends" Number (in this context)
		List<? extends Number> foo2 = new ArrayList<Integer>(); // Integer extends Number
		List<? extends Number> foo3 = new ArrayList<Double>();

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
