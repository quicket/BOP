package com.quicket.BOP;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

public class TestMatrix {
	public static Logger logger = Logger.getLogger(TestMatrix.class);
	@Test
	public void testMatrix() {
		long [][] data = { {1,1},{1,0}};
		Matrix m = new Matrix(data);
		logger.info("matrix=\n"+m.toString());
	}
	@Test
	public void testMatrix2() {
		long [][] data = { {1,1},{1,0}};
		Matrix m1 = new Matrix(data);
		
		Matrix m2 = m1.multiple(m1);
		Matrix m3 = m1.multiple(m2);
		logger.info("m1=\n"+m1.toString());
		logger.info("m2=\n"+m2.toString());
		logger.info("m3=\n"+m3.toString());
		
		long [][] data4= { {1},{0}};
		Matrix m4 = new Matrix(data4);
				
		Matrix m5 = m1.multiple(m4);
		logger.info("m5===\n" + m5);
	}
	
	@Test 
	public void testMatrixPower(){
		long [][] data = { {1,1},{1,0}};
		Matrix m1 = new Matrix(data);
		for(int i=1;i<10;i++) {
			Matrix m = m1.power(i);
			logger.info("m.power("+i+")=\n"+m);
		}
		
		Matrix m2= m1.power(50);
		logger.info("m.power("+50+")=\n"+m2);
		
		long dataExp[][] = { {20365011074L,12586269025L},{12586269025L,7778742049L}};
		Assert.assertArrayEquals(dataExp, m2.data);
	}
}
