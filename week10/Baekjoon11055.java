/*
 * BOJ 11055번 : 가장 큰 증가하는 부분수열
 * 메모리 : 12,076kb
 * 시간 : 88ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon11055 {
	static int N;
	static int[] A;
	static int[] dp;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		dp = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
			dp[n] = A[n];
		}
		
		// dp[i] = A[i]을 마지막으로하는 부분수열 
		// A[j]까지의 최대 부분수열 합 + A[i]
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(A[j] < A[i]) {
					dp[i] = Math.max(dp[i], dp[j]+A[i]); 
				}
			}
		}
		
		answer = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			answer = Math.max(answer, dp[i]);
		}
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
}