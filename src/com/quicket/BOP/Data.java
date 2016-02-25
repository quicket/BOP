package com.quicket.BOP;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.log4j.Logger;

public class Data {
	public static Logger logger = Logger.getLogger(Data.class);
	private int data;
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public void set(int data) {
		logger.info(Thread.currentThread().getName()+" Ready to Write init");
		rwl.writeLock().lock();
		logger.info(Thread.currentThread().getName()+" Ready to Write lock acquired");
		try {
			Thread.sleep(200);
			this.data = data;
			logger.info(Thread.currentThread().getName()+" Ready to Write data="+data);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
		finally {
			rwl.writeLock().unlock();
			logger.info(Thread.currentThread().getName()+" Ready to Write lock released");
		}
		logger.info(Thread.currentThread().getName()+" Write Done");
	}
	
	public int get(){
		logger.info(Thread.currentThread().getName()+" Ready to Read");
		rwl.readLock().lock();
		logger.info(Thread.currentThread().getName()+" Ready to Read lock acquired");
		try {
			Thread.sleep(3000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			rwl.readLock().unlock();
			logger.info(Thread.currentThread().getName()+" Ready to Read lock released");
		}
		logger.info(Thread.currentThread().getName()+" Read Done:"+data);
		return data;
	}
}
