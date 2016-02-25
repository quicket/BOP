package com.quicket.BOP;

import java.security.SecureRandom;
import java.util.Random;

public class ReadWriteLockTest {
	public static void main(String[] args) {
		final Data data = new Data();	
		for(int i=0;i<3;i++) {
			new Thread(new Runnable() {
				public void run() {
					data.set(new Random().nextInt(30));
				}
			}).start();
		}
		
		for(int i=0;i<3;i++) {
			new Thread(new Runnable() {
				public void run() {
					data.get();
				}
			}).start();
		}
	}

	
}
