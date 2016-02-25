package com.quicket.BOP;

public class Matrix {
	public int rows;
	public int cols;
	public long [][] data ;
	public Matrix (long [][] data) {
		this.data= data;
		this.rows=data.length;
		if(data !=null && data[0] != null) {
			this.cols = data[0].length;
		}
	}
	
	public Matrix multiple (Matrix other) {
		//(r1,c1) * (r2,c2) (c1=r2) ==>r1 * c2
		if (other == null) 
			return null;
		int r1 =this.rows, c1=this.cols;
		int r2= other.rows,c2=other.cols;
		if(c1 != r2) {
			return null;
		}
		long newData [][] = new long[r1][c2]; 
		long thisData [][] = this.data;
		long otherData [][] = other.data;
		for(int i=0;i<r1;i++) {
			for(int j=0;j<c2;j++) {
				long sum = 0;
				for(int m=0;m<c1;m++) {
					sum += thisData[i][m] * otherData[m][j];
				}	
				newData[i][j]=sum;
			}
		}
		Matrix m = new Matrix(newData);
		return m;
	}
	
	public Matrix power(int n) {
		if(n<1) return null;
		if(n==1) return this;
		if(n ==2 ) return this.multiple(this);
		Matrix m1= this.power(n/2);
		Matrix m2= m1.multiple(m1);
		Matrix m3 = m2;
		if(n%2 ==1) {
			m3 = this.multiple(m2);
		}
		return m3;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for(int i=0;i<this.rows;i++){
			for(int j=0;j<this.cols;j++){
				sb.append(this.data[i][j]).append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
