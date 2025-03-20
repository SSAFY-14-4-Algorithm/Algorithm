/*
 * BOJ 2579번 : 계단 오르기 
 * 메모리 : 11,612kb
 * 시간 : 64ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon2579 {
	static int N;
	static int[] stair;
	static int[] dp;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		stair = new int[N+1];
		for(int n = 1; n <= N; n++) {
			stair[n] = Integer.parseInt(br.readLine());
		}
		
		dp = new int[N+1];
		dp[1] = stair[1];
		dp[2] = stair[1]+stair[2];
		
		// 마지막 계단을 밟기 위한 경우
		// n-1에서 올라오는 경우 : n-2는 건너뛰어야 함
		// n-2에서 올라오는 경우
		for(int n = 3; n <= N; n++) {
			dp[n] = Math.max(dp[n-3]+stair[n-1]+stair[n], dp[n-2]+stair[n]);
		}
		
		answer = dp[N];
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
}
