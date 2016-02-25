package com.quicket.BOP;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.log4j.Logger;

public class Fibonacci {
	public static Logger logger = Logger.getLogger(Fibonacci.class);
	// Time Complexity O(n)
	public static long getFibonacci(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		long pp = 0, p = 1, c = 0;
		for (int i = 0; i <= n - 2; i++) {
			c = pp + p;
			pp = p;
			p = c;
		}
		return c;
	}
	
	//Time Complexity O(log(n))
	public static long getFib2(int n) {
		if (n == 0)
			return 0;
		if (n ==1 )
			return 1;
		long [][] data = { {1,1},{1,0}};
		Matrix m0 = new Matrix(data);
		Matrix m1 = m0.power(n-1);
		long [][] data2= { {1},{0}};
		Matrix F0 = new Matrix(data2); 
		Matrix FN = m1.multiple(F0);
		logger.info("FN=\n"+FN+",getFib2("+n+")="+FN.data[0][0]);
		return FN.data[0][0];
	}
	//Though theorectically, it is faster. But In reality, due to precision, it is quite slow.
	public static long getFibFast(int n) {
		//double fn = 1/Math.sqrt(5) *( Math.pow((1+Math.sqrt(5))/2, n) + Math.pow((1-Math.sqrt(5))/2,n));
		// since abs( (1-Math.sqrt(5)/2)^n/Math.sqrt(5) ) <= 1/2 , 
		// FN is the closest integer to  (1+ Math.sqrt(5)/2)^n/Math.sqrt(5) 
		if (n == 0)
			return 0;
		if (n ==1 )
			return 1;
		BigDecimal sqrt5 = Util.bigSqrt(BigDecimal.valueOf(5));
		BigDecimal t= BigDecimal.valueOf(1).add(sqrt5).divide(BigDecimal.valueOf(2));
		
		logger.info("t="+t.doubleValue()+",sqrt5="+sqrt5.doubleValue());
		BigDecimal fnRaw0=BigDecimal.valueOf(1).add(sqrt5).divide(BigDecimal.valueOf(2)).pow(n);
		logger.info("fnRaw="+fnRaw0.doubleValue());
		BigDecimal fnRaw = fnRaw0.divide(sqrt5,10000,RoundingMode.HALF_DOWN);
		
		//BigDecimal fnRaw = Math.pow((1+Math.sqrt(5))/2,n)/Math.sqrt(5);
		logger.info("fnRaw="+fnRaw+",doubleValue="+fnRaw.doubleValue());
		long fn = (long)Math.floor(fnRaw.doubleValue()+0.5);
		return fn;
	}
}
