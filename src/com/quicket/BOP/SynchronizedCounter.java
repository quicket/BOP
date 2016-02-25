package com.quicket.BOP;

import org.apache.log4j.Logger;

public class SynchronizedCounter {
	public static Logger logger = Logger.getLogger(SynchronizedCounter.class);
	private int count=0;
	public SynchronizedCounter(int count) {
		this.count=count;
	}
	public synchronized void increment() {
		logger.info("increment begin...");
		try {
			Thread.sleep(4000);
			count= count+4;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("increment end...");
	}
	public synchronized void decrement() {
		logger.info("decrement begin...");
		try {
			Thread.sleep(2000);
			count= count-2;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("decrement end...");
	}
	
	public static void main(String[] args) throws InterruptedException {
		final SynchronizedCounter counter = new SynchronizedCounter(5);
		new Thread(new Runnable() {
			public void run() {
				logger.info("call increment...");
				counter.increment();
			}
		}).start();
		Thread.sleep(1000);
		logger.info("call decrement...");
		counter.decrement();
	}
}
