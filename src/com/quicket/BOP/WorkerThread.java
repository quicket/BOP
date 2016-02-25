package com.quicket.BOP;

import org.apache.log4j.Logger;

public class WorkerThread implements Runnable{
	public static Logger logger = Logger.getLogger(WorkerThread.class);
	private String command;
	
	public WorkerThread(String command) {
		this.command=command;
	}
	
	public String toString() {
		return this.command;
	}
	
	public void run() {
		logger.info(Thread.currentThread().getName()+" Start. Command="+command);
		processCommand();
		logger.info(Thread.currentThread().getName()+" End.");
	}
	
	public void processCommand() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
