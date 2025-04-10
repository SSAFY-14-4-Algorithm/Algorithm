import java.io.*;
import java.util.*;
/*
 * 메모리: 11684KB
 * 시간: 68ms
 * 
 * T: 테스트 케이스 
 * N: 동전 가지 수
 * 동전 금액을 오름차순으로
 * M: 만들어야 할 금액
 *
 */

public class Main {
	static int T,N,M;
	static int[] coins;
	
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder sb = new StringBuilder();
	
	T = Integer.parseInt(br.readLine());
	
	for(int i=0;i<T;i++) {
		
		N = Integer.parseInt(br.readLine());
		
		coins = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int j=0;j<N;j++) {
			coins[j] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		int[] dp = new int[M+1];
		dp[0] = 1;
		
		numWay(dp);
		
		sb.append(dp[M]).append("\n");
		
		}	
	System.out.print(sb.toString());
	}
	
	public static void numWay(int[] dp) {
		for(int coin: coins) {
			for(int i=coin;i<=M;i++) {
				dp[i] += dp[i-coin];
			}
		}
		
	}
	
}
