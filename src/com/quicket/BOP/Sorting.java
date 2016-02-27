package com.quicket.BOP;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class Sorting {
	public static Logger logger = Logger.getLogger(Sorting.class);
	/*
	 * Check whether array is sorted (Ascending)
	 * 
	 */
	public static boolean isSorted(int [] A) {
		if(A== null) {
			return false;
		}
		boolean flag = true;
		for(int i=1;i<A.length;i++){
			if(A[i]<A[i-1]) {
				flag=false;
				break;
			}
		}
		logger.info(Arrays.toString(A)+" is sorted?="+flag);
		return flag;
	}
	
	public static void merge(int[] A, int p,int q,int r) {
		logger.info("merge(A,p="+p+",q="+q+",r="+r+") begin....");
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
		logger.info("merge(A,p="+p+",q="+q+",r="+r+") end....");
	}
	
	public static void mergeSort(int[] A, int p, int r) {
		logger.info("mergeSort(A,"+p+","+r+")"+Arrays.toString(A)+" begin...");
		if(A == null || p>=r)
			return;
		int i= p+ (r-p)/2;
		
		mergeSort(A,p,i);
		mergeSort(A,i+1,r);
		merge(A,p,i+1,r);
		logger.info("mergeSort(A,"+p+","+r+")" +Arrays.toString(A)+" end...");
	}
}
