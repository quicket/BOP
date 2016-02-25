package com.quicket.BOP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

public class SimpleThreadPool {
	public static Logger logger = Logger.getLogger(SimpleThreadPool.class);
	public static void main(String [] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++) {
			Runnable worker = new WorkerThread("worker"+i);
			executor.execute(worker);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Finish all threads");
	}

}
