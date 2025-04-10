package AlgorithmStudy.week10;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 11,668 kb	
 * 실행 시간 : 68 ms
 */

public class BJ_9084_동전 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st ;
	
	static int N, price, result;
	static int[] coins;
	static int[] dp;
	
	public static void main(String[] args) throws  IOException {
		int tc = Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			
			N = Integer.parseInt(br.readLine());
			
			coins = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			price = Integer.parseInt(br.readLine());
			
			dp = new int[price+1];
			dp[0] = 1;
			
			for(int coin : coins) {
				for(int i=coin; i<=price;i++) {
					dp[i] += dp[i-coin];
				}
			}
			
			sb.append(dp[price]).append("\n");
		}

		System.out.println(sb);
	}

}
