package com.quicket.BOP;

import org.apache.log4j.Logger;

public class Drop {
	public static Logger logger = Logger.getLogger(Drop.class);
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
    	logger.info("take() begin...."); 
        // Wait until message is
        // available.
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        notifyAll();
    	logger.info("take() end...."); 
        return message;
    }

    public synchronized void put(String message) {
    	logger.info("put() begin...."); 
        // Wait until message has
        // been retrieved.
        while (!empty) {
            try { 
                wait();
            } catch (InterruptedException e) {}
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        notifyAll();
    	logger.info("put() end...."); 
    }
    
    public static void main(String [] args) {
    	Drop drop = new Drop();
    	new Thread(new Producer(drop)).start();
    	new Thread(new Consumer(drop)).start();
    }
}