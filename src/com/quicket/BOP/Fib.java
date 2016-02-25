package com.quicket.BOP;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class Fib extends Thread {
	public static Logger logger = Logger.getLogger(Fib.class);
	public static ExecutorService executor= Executors.newFixedThreadPool(500);
	public int n;
	public BigInteger fn;
	
	public Fib(int n) {
		this.n=n;
		this.fn=BigInteger.ZERO;
	}
	// Thread out-number permissible maximum
	//java.lang.OutOfMemoryError: unable to create new native thread
	@Override
	public void run() {
		logger.info("Thread:"+Thread.currentThread().getName()+".run(n="+this.n+") begin...");
		if(n==0) {
			this.fn=BigInteger.ZERO;
		}
		else if(n==1) {
			this.fn=BigInteger.ONE;
		}
		else {
			try {
				Fib fnm1= new Fib(n-1);
				Fib fnm2= new Fib(n-2);
				fnm1.start();
				fnm2.start();
				fnm1.join();
				fnm2.join();
				this.fn= fnm1.fn.add(fnm2.fn);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Thread:"+Thread.currentThread().getName()+".run(n="+this.n+") end (fn="+this.fn+ ")...");
	}
	
	public static BigInteger getFib(int n) {
		Fib fib = new Fib(n);
		fib.run();
		return fib.fn;
	}
	
	public static void main(String [] args) {
		for(int i=0;i<17;i++) {
			logger.info("Begin Fib("+i+").....");
			BigInteger fn=getFib(i);
			logger.info("Finish Fib("+i+")="+fn);
			logger.info("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		}
		BigInteger fn=getFib(20);
	}

}
