/*
 * BOJ 9084번 : 동전
 * 메모리 : 11,764kb
 * 시간 : 68ms
 * 
 * 동전의 종류가 주어질 때 주어진 금액을 만드는 모든 방법을 세야한다.
 * dp[만들고 싶은 금액] = dp[만들고 싶은 금액-coin]
 */

import java.io.*;
import java.util.*;

public class Baekjoon9084 {
	static int N;
	static int[] coin;
	static int M;
	static int[] dp; // 만들 수 있는 경우의 수 저장
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			coin = new int[N+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int n = 1; n <= N; n++) {
				coin[n] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			
			dp = new int[M+1];
			dp[0] = 1; // 금액 0 일때, 아무것도 사용하지 않는 경우의 수 1
			
			for(int n = 1; n <= N; n++) {
				for(int m = coin[n]; m <= M; m++) {
					dp[m] += dp[m-coin[n]]; 
				}
			}
			
			sb.append(dp[M]).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
}