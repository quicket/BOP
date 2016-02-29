package com.quicket.BOP;

import java.util.Arrays;

import org.apache.log4j.Logger;

public class Sorting {
	public static Logger logger = Logger.getLogger(Sorting.class);
	public static void swap(int [] T, int p, int q) {
		if ( (T==null) || (p==q) )
			return;
		int temp = T[p];
		T[p]=T[q];
		T[q]=temp;
	}
	//use right-most as pivot, to keep ordering,
	//T[l....pi]<= T[pi] < T[pi+1...r]
	public static boolean isPartitioned(int [] T, int ip) {
		boolean flag = true;
		for(int i=0;i<ip;i++) {
			if(T[i]>T[ip]) {
				flag= false;
				break;
			}
		}
		if(flag == true) {
			for(int i=ip+1;i<T.length;i++) {
				if(T[i]<=T[ip]) {
					flag= false;
					break;
				}
			}
		}
		logger.info("isPartitioned(T,"+ip+")==="+flag+",T=["+Arrays.toString(T)+"]");
		return flag;
	}
	
	public static int partition( int [] T, int l, int r) {
		logger.info("partition(T,"+l+","+r+"),T=["+Arrays.toString(T)+"] begin...");
		if(T==null || T.length == 0) 
			return l;
		//don't need this, this case is handled properly in below code
/*		if(l==r) {
			return l;
		}*/
		int i = l-1;
		int j = l;
		int pv = T[r];
		for(;j<r;j++) {
			if(T[j]<=pv) {
				i++;
				swap(T,j,i);
			}
		}
		swap(T,r,i+1);
		logger.info("partition(T,"+l+","+r+")==="+(i+1)+",T=["+Arrays.toString(T)+"] end...");
		return i+1;
	}
	public static int partitionOld ( int []T, int l, int r) {
		logger.info("partition(T,"+l+","+r+")===,T=["+Arrays.toString(T)+"] begin...");
		if ((T == null) || (l>=r)) {
			return l;
		}
		//choose leftmost element as pivot
		int ip= l;
		int pivot= T[ip];
		int il= l+1;
		int ir= r;
		//not elegant!!
		while (il<ir) {
			while((il<r) && (T[il]<pivot))
				il++;
			while(ir>=il && (T[ir]>=pivot)) {
				ir--;
			}
			if(il<ir) {
				swap(T,il,ir);
				il++;
				ir--;
			}
		}
		if((T[ir]>=pivot) && (ir>=il)) {
			ir--;
		}
		if(T[ir]<T[ip]) {
			swap(T,ip,ir);
			ip=ir;
		}
		logger.info("partition(T,"+l+","+r+")==="+ip+",T=["+Arrays.toString(T)+"] end...");
		return ip;
	}
	
	
	
	public static int BinarySearch(int x, int[] T, int p, int r) {
		if((T == null) || (p> r)) {
			logger.info("BinarySearch(x="+x+",p="+p+",r="+r+ ") in Array:"+Arrays.toString(T)+"  ===>"+p);
			return p;
		}
		int low= p , high= r+1;
		while (low < high) {
			int mid = (low+high)/2;
			if(x <= T[mid]) {
				high = mid;
			}
			else {
				low = mid +1;
			}
		}
		logger.info("BinarySearch(x="+x+") in Array:"+Arrays.toString(T)+"  ===>"+high);
		return high;
	}
	
	public static void swap(int a, int b) {
		int temp = a;
		a= b;
		b= temp;
	}
	
	public static void recursiveMerge(int [] T, int p1,int r1, int p2, int r2, int []A , int p3) {
		logger.info("recursiveMerge(T,"+p1+","+r1+","+p2+","+r2+",A,"+p3+"),T="+Arrays.toString(T)+" Begin....");
		int n1= r1-p1+1;
		int n2= r2-p2+1;
		if(n1<n2) {
			int t1= p1;
			p1 = p2;
			p2 = t1;
			t1 = r1;
			r1 = r2;
			r2 = t1;
			t1 = n1;
			n1 = n2;
			n2 = t1;
			logger.info("==>recursiveMerge(T,"+p1+","+r1+","+p2+","+r2+",A,"+p3+"),T="+Arrays.toString(T));
		}
		if(n1==0) {
			logger.info("base case...");
			return ;
		}
		int q1 = (p1+r1)/2;
		int q2 = BinarySearch(T[q1],T,p2,r2);
		int q3 = p3 + (q1-p1) + (q2-p2);
		A[q3] = T[q1];
		logger.info("q1="+q1+",q2="+q2+",q3="+q3);
		recursiveMerge(T,p1,q1-1,p2,q2-1,A,p3);
		recursiveMerge(T,q1+1,r1,q2,r2,A,q3+1);
		logger.info("recursiveMerge(T,"+p1+","+r1+","+p2+","+r2+",A,"+p3+")="+Arrays.toString(A)+" Done....");
	}
	
	public static void recursiveMS(int[] T, int p, int r, int [] A) {
		logger.info("recursiveMS(A,"+p+","+r+")"+Arrays.toString(T)+" begin...");
		if(T == null || A==null || p>=r ) {
			logger.info("recursiveMS(A,"+p+","+r+")" +Arrays.toString(T)+" end...");
			return;
		}

		int i= p+ (r-p)/2;
		recursiveMS(T,p,i,A);
		recursiveMS(T,i+1,r,A);
		recursiveMerge(T,p,i,i+1,r,A,0);
		//have to copy here 
		for(int j=p;j<=r;j++) {
			T[j]=A[j];
		}
		logger.info("recursiveMS(A,"+p+","+r+")" +Arrays.toString(T)+" end...");
	}
	
	public static void recursiveMergeSort(int[] T, int p,int r) {
		logger.info("recursiveMergeSort(T,"+p+","+r+")");
		if (T==null || p>=r) 
			return;
		int A[] = new int[T.length];
		recursiveMS(T,p,r,A);
		for(int i=0;i<T.length;i++) {
			T[i]=A[i];
		}
	}
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
		logger.debug("merge(A,p="+p+",q="+q+",r="+r+") begin....");
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
		logger.debug("merge(A,p="+p+",q="+q+",r="+r+") end....");
	}
	
	public static void mergeSort(int[] A, int p, int r) {
		logger.debug("mergeSort(A,"+p+","+r+")"+Arrays.toString(A)+" begin...");
		if(A == null || p>=r)
			return;
		int i= p+ (r-p)/2;
		
		mergeSort(A,p,i);
		mergeSort(A,i+1,r);
		merge(A,p,i+1,r);
		logger.debug("mergeSort(A,"+p+","+r+")" +Arrays.toString(A)+" end...");
	}
	
}
