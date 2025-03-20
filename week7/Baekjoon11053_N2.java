/*
 * dp O(N^2)
 * 메모리 : 12,060kb
 * 시간 : 84ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon11053_N2 {
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
			dp[n] = 1;
		}
		
		for(int i = 1; i < N; i++) {
			for(int j = 0; j < i; j++) {
				if(A[j] < A[i]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		answer = 0;
		for(int n = 0; n < N; n++) {
			answer = Math.max(answer, dp[n]);
		}
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
}
