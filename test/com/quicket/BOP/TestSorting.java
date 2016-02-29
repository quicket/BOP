package com.quicket.BOP;

import java.util.Arrays;
import java.util.Random;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.quicket.BOP.ParMergeSort;
import com.quicket.BOP.Sorting;

public class TestSorting {
	public static Logger logger = Logger.getLogger(TestSorting.class);
	
	@Test
	public void testPartition() {
		int T3[] = {8,4,5};
		int ip3=Sorting.partition(T3, 0, T3.length-1);
		boolean flag3= Sorting.isPartitioned(T3,ip3);
		Assert.assertEquals(true, flag3);
		
		int T4[] = {4,7,6,1};
		int ip4=Sorting.partition(T4, 0, T4.length-1);
		boolean flag4= Sorting.isPartitioned(T4,ip4);
		Assert.assertEquals(true, flag4);
		
		for(int i=0;i<10000;i++) {
			int length = (new Random()).nextInt(100);
			int T [] =new int[length];
			for (int j=0;j<T.length;j++) {
				T[j] = (new Random()).nextInt(1000);
			}
			int ip = Sorting.partition(T, 0,T.length-1); 
			boolean flag= Sorting.isPartitioned(T,ip);
			Assert.assertEquals(true, flag); 
		}
	}
	//@Test
	public void testBinarySearch() {
		int x1 = 4;
		int [] T1 = {1};
		int i1 = Sorting.BinarySearch(x1, T1, 0, T1.length-1);
		Assert.assertTrue(i1==1);

		int x2 = 4;
		int [] T2 = {6};
		int i2 = Sorting.BinarySearch(x2, T2, 0, T2.length-1);
		Assert.assertTrue(i2==0);
		
		int x3 = 4;
		int [] T3 = null;
		int i3 = Sorting.BinarySearch(x3, T3, 0, 0);
		Assert.assertTrue(i3==0);
		
		for(int i=0;i<10000;i++) {
			int length = (new Random()).nextInt(100)+1;
			int A [] =new int[length];
			for (int j=0;j<A.length;j++) {
				A[j] = (new Random()).nextInt(1000);
			}
			Arrays.sort(A);
			boolean flag= Sorting.isSorted(A);
			Assert.assertEquals(true, flag);
			int x = (new Random()).nextInt(150);
			
			int index = Sorting.BinarySearch(x, A, 0, A.length-1);
			Assert.assertTrue(index>=0);
			if(A != null || A.length<=0) {
				if(index >= A.length) {
					Assert.assertTrue(index==A.length);
					Assert.assertTrue(x>A[A.length-1]);
				}
				else {
					Assert.assertTrue(x<=A[index]);
					if(index>0) {
						Assert.assertTrue(x>A[index-1]);
					}
				}
			}
			else {
				Assert.assertTrue(index ==0);
			}
		}
	}
	
	//@Test 
	public void testRecursiveMerge() {
/*		int T1 [] = {1};
		int A1 [] = new int[T1.length];
		Sorting.recursiveMerge(T1,0,0,1,0,A1,0);
		boolean flag1 = Sorting.isSorted(A1);
		Assert.assertEquals(true, flag1);
		logger.info("************************************");
		
		int T2 [] = {2,6,3};
		int A2 [] = new int[T2.length];
		Sorting.recursiveMerge(T2,0,1,2,2,A2,0);
		boolean flag2 = Sorting.isSorted(A2);
		Assert.assertEquals(true, flag2);
		logger.info("************************************");
		
		int T3 [] = {2,6,3,5};
		int A3 [] = new int[T3.length];
		Sorting.recursiveMerge(T3,0,1,2,3,A3,0);
		boolean flag3 = Sorting.isSorted(A3);
		Assert.assertEquals(true, flag3);*/
		
		
		for(int i=0;i<100;i++) {
			int length1 = (new Random()).nextInt(100);
			int TN1 [] =new int[length1];
			for (int j=0;j<TN1.length;j++) {
				TN1[j] = (new Random()).nextInt(1000);
			}
			Arrays.sort(TN1);
			
			int length2 = (new Random()).nextInt(100);
			int TN2 [] =new int[length2];
			for (int j=0;j<TN2.length;j++) {
				TN2[j] = (new Random()).nextInt(1000);
			}
			Arrays.sort(TN2);
			
			int T[] = new int [TN1.length+TN2.length];
			int A[] = new int [TN1.length+TN2.length];
			
			for(int j=0;j<TN1.length;j++) {
				T[j]=TN1[j];
			}
			for(int j=0;j<TN2.length;j++){
				T[j+TN1.length]=TN2[j];
			}
			
			Sorting.recursiveMerge(T,0,TN1.length-1,TN1.length,TN1.length+TN2.length-1,A,0);
			boolean flag = Sorting.isSorted(A);
			Assert.assertEquals(true, flag);
		}
	}
	//@Test
	public void testIsSorted() {
		int a1[]= {1};
		boolean flag1 = Sorting.isSorted(a1);
		Assert.assertEquals(true, flag1);
		
		int a2[]= {1,2,4,5,7,8,9};
		boolean flag2 = Sorting.isSorted(a2);
		Assert.assertEquals(true, flag2);
		
		int a3[]= {1,3,4,5,7,8,9,2};
		boolean flag3 = Sorting.isSorted(a3);
		Assert.assertEquals(false, flag3);
	}
	
	//@Test 
	public void testMergeSort() {
		Sorting.logger.setLevel(Level.INFO);
		int A1[] = null;
		Sorting.mergeSort(A1,0,0);
		
		int A2[] = {1};
		Sorting.mergeSort(A2, 0, A2.length-1);
		boolean flag2= Sorting.isSorted(A2);
		Assert.assertEquals(true, flag2);
		
		int A3[] = {9,6};
		Sorting.mergeSort(A3, 0, A3.length-1);
		boolean flag3= Sorting.isSorted(A3);
		Assert.assertEquals(true, flag3);
		
		int A4[] = {9,6,4,24, 5, 1,16};
		Sorting.mergeSort(A4, 0, A4.length-1);
		boolean flag4= Sorting.isSorted(A4);
		Assert.assertEquals(true, flag4);
		
		int A5[] = {9,6,5,4, 5, 5,5};
		Sorting.mergeSort(A5, 0, A5.length-1);
		boolean flag5= Sorting.isSorted(A5);
		Assert.assertEquals(true, flag5);
		
		for(int i=0;i<100;i++) {
			int length = (new Random()).nextInt(100);
			int A [] =new int[length];
			for (int j=0;j<A.length;j++) {
				A[j] = (new Random()).nextInt(1000);
			}
			Sorting.mergeSort(A, 0, A.length-1);
			boolean flag= Sorting.isSorted(A);
			Assert.assertEquals(true, flag);
		}
	}
	
	//@Test 
	public void testRecursiveMergeSort() {
		Sorting.logger.setLevel(Level.INFO);
		int A1[] = null;
		Sorting.recursiveMergeSort(A1,0,0);
		
		
		int A2[] = {1};
		Sorting.recursiveMergeSort(A2, 0, A2.length-1);
		boolean flag2= Sorting.isSorted(A2);
		Assert.assertEquals(true, flag2);
		logger.info("*******************************");
		int A3[] = {9,6};
		Sorting.recursiveMergeSort(A3, 0, A3.length-1);
		boolean flag3= Sorting.isSorted(A3);
		Assert.assertEquals(true, flag3);
		logger.info("*******************************");
		int A4[] = {9,6,4,24, 5, 1,16};
		Sorting.recursiveMergeSort(A4, 0, A4.length-1);
		boolean flag4= Sorting.isSorted(A4);
		Assert.assertEquals(true, flag4);
		logger.info("*******************************");
		int A5[] = {9,6,5,4, 5, 5,5};
		Sorting.recursiveMergeSort(A5, 0, A5.length-1);
		boolean flag5= Sorting.isSorted(A5);
		Assert.assertEquals(true, flag5);
		logger.info("*******************************");	
		for(int i=0;i<100;i++) {
			int length = (new Random()).nextInt(100);
			int A [] =new int[length];
			for (int j=0;j<A.length;j++) {
				A[j] = (new Random()).nextInt(1000);
			}
			Sorting.recursiveMergeSort(A, 0, A.length-1);
			boolean flag= Sorting.isSorted(A);
			Assert.assertEquals(true, flag);
			logger.info("*******************************");
		}
	}
	
	//@Test 
	public void testParMergeSort() {
		Sorting.logger.setLevel(Level.INFO);
		int A1[] = null;
		ParMergeSort.mergeSort(A1,0,0);
		logger.info("Test null case");
		
		int A2[] = {1};
		ParMergeSort.mergeSort(A2, 0, A2.length-1);
		boolean flag2= Sorting.isSorted(A2);
		Assert.assertEquals(true, flag2);
		
		int A3[] = {9,6};
		ParMergeSort.mergeSort(A3, 0, A3.length-1);
		boolean flag3= Sorting.isSorted(A3);
		Assert.assertEquals(true, flag3);
		
		int A4[] = {9,6,4,24, 5, 1,16};
		ParMergeSort.mergeSort(A4, 0, A4.length-1);
		boolean flag4= Sorting.isSorted(A4);
		Assert.assertEquals(true, flag4);
		
		int A5[] = {9,6,5,4, 5, 5,5};
		ParMergeSort.mergeSort(A5, 0, A5.length-1);
		boolean flag5= Sorting.isSorted(A5);
		Assert.assertEquals(true, flag5);
		
		for(int i=0;i<100;i++) {
			int length = (new Random()).nextInt(100);
			int A [] =new int[length];
			for (int j=0;j<A.length;j++) {
				A[j] = (new Random()).nextInt(1000);
			}
			ParMergeSort.mergeSort(A, 0, A.length-1);
			boolean flag= Sorting.isSorted(A);
			Assert.assertEquals(true, flag);
		}
	}
	
	//@Test 
	public void testSwap() {
		int a=10,b=12;
		Sorting.swap(a, b);
		logger.info("a="+a+",b="+b);
	}
}
