package com.quicket.BOP;

import org.apache.log4j.Logger;

public class ParMergeSort extends Thread{
	public static Logger logger = Logger.getLogger(ParMergeSort.class);
	private int T0 [] ;
	private int p0;
	private int r0;
	public ParMergeSort(int T[], int p, int r) {
		this.T0=T;
		this.p0=p;
		this.r0=r;
	}
	
	public static void merge(int[] A, int p,int q,int r) {
		logger.debug("Thread("+Thread.currentThread().getName()+"),merge(A,p="+p+",q="+q+",r="+r+") begin....");
		if(A == null || p >= r) 
			return;
		int T[] = new int[r-p+1];
		int i1=p,i2=q,it=0;
		while (i1<q && i2<=r){
			if(A[i1] <= A[i2]) {
				T[it++]=A[i1++];
			}
			else {
				T[it++]=A[i2++];
			}
		}
		while(i1<q) {
			T[it++]=A[i1++];
		}
		while(i2<=r) {
			T[it++]=A[i2++];
		}
		
		for(int i=0;i<r-p+1;i++){
			A[i+p] = T[i];
		}
		logger.debug("Thread("+Thread.currentThread().getName()+"),merge(A,p="+p+",q="+q+",r="+r+") end....");
	}
	
	/*
	 * Keep the function same as single-thread mergeSort to achieve minimum changes.
	 * Multi-Thread Merge Sort should keep the function definition almost the same as single-thread Merge Sort. 
	 */
	public static void mergeSort(int T[], int p , int r) {
		logger.info("Thread("+Thread.currentThread().getName()+"),p_mergeSort(T"+","+p+","+r+") begin...");
		if((T==null) || (p>=r))  {
			logger.info("Thread("+Thread.currentThread().getName()+"),p_mergeSort(T"+","+p+","+r+") end...");
			return;
		}
		int m = p + (r - p)/2;
		try {
			Thread t1 = new ParMergeSort(T,p,m);
			t1.start();
			mergeSort(T,m+1,r);
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		merge(T,p, m+1,r);
		logger.info("Thread("+Thread.currentThread().getName()+"),p_mergeSort(T"+","+p+","+r+") end...");
	}
	
	public void run() {
		mergeSort(this.T0, this.p0, this.r0);
	}
	
}
