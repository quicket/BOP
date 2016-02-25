package com.quicket.BOP;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestFibonacci {
	public static Logger logger = Logger.getLogger(TestFibonacci.class);
	@Test 
	public void getFibonacci() {
		long [] fib = { 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368};
		for(int i=0;i< fib.length;i++) {
			long fibn= Fibonacci.getFibonacci(i);
			long fibn2=Fibonacci.getFib2(i);
			long fibnFast = Fibonacci.getFibFast(i);
			logger.info("Fib("+i+")="+fibn+",fibn2="+fibn2+",fibnFast="+fibnFast);
			Assert.assertTrue(fibn==fib[i]);
			Assert.assertTrue(fibn2==fib[i]);
			Assert.assertTrue(fibnFast==fib[i]);
		}
		long fibn2=Fibonacci.getFib2(52);
		Assert.assertEquals(fibn2,32951280099L);
		long fibnFast=Fibonacci.getFibFast(70);
		logger.info("fast="+fibnFast+",raw="+190392490709135L);
		Assert.assertEquals(fibnFast,190392490709135L);

	}
	
	@Test
	public void genFibonacci() {
		BigDecimal pp=BigDecimal.ZERO, p=BigDecimal.ONE, c=null;
		for(int i=2;i<100;i++) {
			c = pp.add(p);
			pp = p;
			p = c;
			logger.info("F["+i+"]="+c);
		}
		
		logger.info("Max Long="+Long.MAX_VALUE+",min_value="+Long.MIN_VALUE);
	}
	
}
