package AlgorithmStudy.week6;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 11,576 kb
 * 실행 시간 : 68 ms
 */

public class Baekjoon10830 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static long[][] procession_result;
	static long[][] procession_base;
	static long[][] procession_copy;
	static int N, mod;
	static Long B;
	public static void main(String[] args) throws IOException {
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		procession_result = new long[N][N];
		procession_base = new long[N][N];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				procession_result[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		for(int i=0;i<N;i++) {
			procession_base[i] = procession_result[i].clone();
		}
		
		mod = 1000;
		
		divideConquer(procession_base, B-1, mod);
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(procession_result[i][j]+ " ");
			}
			System.out.println();
		}
		
		
	}

	private static void divideConquer(long[][] base, long ex, int mod) {

		if(ex < 1) return;
		
		if(ex % 2 != 0) {
			multiplication(procession_result, procession_base);
		}
		
		ex = ex / 2;
		
		multiplication(procession_base, procession_base);

		divideConquer(procession_base, ex, mod);

	}
	private static void multiplication(long[][] a, long[][] b) {
		
		procession_copy = new long [N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				for(int k=0;k<N;k++) {
					procession_copy[i][j] += 
							(a[i][k] * b[k][j]) % mod;
					
				}
				procession_copy[i][j] %= mod;
			}
		}
		
		for(int i=0;i<N;i++) {
			a[i] = procession_copy[i].clone();
		}
		
	}

}
