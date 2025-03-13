import java.io.*;
import java.util.*;
/*
 * 메모리: 14280KB
 * 시간: 112ms
 */

public class Baekjoon10830{
	static int N;
	static long B;
	static int[][] matrix;
	static final int Mod =1000;
	
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	
	N = Integer.parseInt(st.nextToken());
	B = Long.parseLong(st.nextToken());
	
	matrix = new int[N][N];
	
	for(int i=0;i<N;i++) {
		st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++) {
			matrix[i][j] = Integer.parseInt(st.nextToken())%Mod;
			
			}
		}
	int[][] res = power(matrix,B);
	
	StringBuilder sb = new StringBuilder();
	for(int i=0;i<N;i++) {
		for(int j=0;j<N;j++) {
			sb.append(res[i][j]).append(" ");
		}
		sb.append("\n");
	}
	System.out.println(sb);
	}
	
	static int[][] power(int[][] base, long exp){
		if(exp ==1) return base;
		
		int[][] half = power(base, exp/2);
		int[][] res = multiply(half,half);
		
		if(exp%2==1) {
			res = multiply(res,matrix);
		}
		return res;
	}
	
	static int[][] multiply(int[][] A, int[][] B){
		int[][] C = new int[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					C[i][j] = (C[i][j]+A[i][k]*B[k][j])%Mod;
				}
			}
		}
		return C;
	}
}
