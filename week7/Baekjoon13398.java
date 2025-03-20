/*
 * BOJ 13398번 : 연속합2
 * 메모리 : 26,308kb
 * 시간 : 212ms
 * 
 * 연속된 수 선택해서 가장 큰 합 구하기 
 * 수열에서 수 하나 제거 가능 (제거 안해도 됨)
 * 
 * 제거X : 그냥 연속합 
 * dp[n] = Math.max(arr[n], dp[n-1]+arr[n])
 * 
 * 제거O : 이전에 수 제거한 상태+arr[n], 지금 arr[n] 제거 
 * dpRemove[n] = Math.max(dpRemove[n-1]+arr[n], dp[n-1]);
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon13398 {
	static int N;
	static int[] arr;
	static int[] dp;
	static int[] dpRemove;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			arr[n] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N];
		dpRemove = new int[N];
		dp[0] = arr[0];
		dpRemove[0] = arr[0];
		answer = arr[0];
		
		for(int n = 1; n < N; n++) {
			dp[n] = Math.max(arr[n], dp[n-1]+arr[n]);
			dpRemove[n] = Math.max(dpRemove[n-1]+arr[n], dp[n-1]);
			answer = Math.max(answer, Math.max(dp[n], dpRemove[n]));
		}
		
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
}
