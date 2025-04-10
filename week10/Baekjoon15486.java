/*
 * BOJ 15486번 : 퇴사2
 * 메모리 : 308,420kb
 * 시간 : 632ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon15486 {
	static int N;
	static int[] T;
	static int[] P;
	static long[] dp; // 얻을 수 있는 최대 수익 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		T = new int[N]; // 걸리는 기간
		P = new int[N]; // 받을 수 있는 금액
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[n] = Integer.parseInt(st.nextToken());
			P[n] = Integer.parseInt(st.nextToken());
		}
		
		dp = new long[N+1];
		for(int n = 0; n < N; n++) {
			if(n+T[n] <= N) { // 현재 날짜에서 상담이 가능할 때
				dp[n+T[n]] = Math.max(dp[n+T[n]], dp[n]+P[n]);
			} 
			// 상담을 선택하지 않고 다음 날짜로 넘어갈 때 (해당 날짜에 상담하지 않는 경우도 항상 고려해야 한다!!!)
			dp[n+1] = Math.max(dp[n+1], dp[n]);
		}
		
		sb.append(dp[N]);
		System.out.print(sb);
		br.close();
	}
}