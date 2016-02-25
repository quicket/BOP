package com.quicket.BOP;

import org.apache.log4j.Logger;

public class SimpleThread {
	public static Logger logger = Logger.getLogger(SimpleThread.class);
	public static void threadMessage(String message) {
		String threadName = Thread.currentThread().getName();
		logger.info(threadName +": "+message+"\n");
	}
	
	private static class MessageLoop implements Runnable {
		public void run() {
			String importantInfo[] = {
					"Mares eat oats",
					"Does eat Oats",
					"LIttle lambs eat ivy",
					"A kid will eat ivy too"
			};
			try {
				for(int i=0;i<importantInfo.length;i++) {
					Thread.sleep(4000);
					threadMessage(importantInfo[i]);
				}
			}
			catch (InterruptedException e) {
			//	e.printStackTrace();
				threadMessage("I wasn't done!");
			}
		}
	}
	
	public static void main(String [] args) throws InterruptedException {
		threadMessage("Starting MessageLoop Thread");
		long startTime= System.currentTimeMillis();
		Thread t = new Thread(new MessageLoop(),"messageLoopThread");
		t.start();
		
		threadMessage("Wait for MessageLoop thread to finish");
		while(t.isAlive()) {
			threadMessage("Still waiting...");
			t.join(1000);
			long curTime = System.currentTimeMillis();
			if((curTime - startTime > 11*1000) && t.isAlive()) {
				threadMessage("Tired of Waiting,interrupt...");
				t.interrupt();
				threadMessage("Waiting again by t.join()...");
				t.join();
			}
		}
		threadMessage("Finally");
	}
}
