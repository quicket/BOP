package com.quicket.BOP;

import java.util.Random;

import junit.framework.Assert;

import org.junit.Test;

public class TestSorting {
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
	
	@Test 
	public void testMergeSort() {
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
		
		for(int i=0;i<10000;i++) {
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
}
