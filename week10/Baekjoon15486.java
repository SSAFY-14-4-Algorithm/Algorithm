package AlgorithmStudy.week10;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 307,920 kb
 * 실행 시간 : 624 ms
 * 
 * [해결 프로세스]
 * 1.f(n) : n일까지 벌 수 있는 최대 수익
 *   - dp[i] = i일에 상담이 끝났을 때 최대 수익
 *   - dp[i-1]까지의 누적 수익을 기준으로 상담을 선택할지 말지 결정
 *   - 종료 시점을 기준으로 갱신하는 구조
 *   
 *   https://presentnine.tistory.com/137
 */

public class BJ_15486_퇴사2 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, result;
	static int[] time, profit;
	static int[] dp;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		time = new int[N+1];
		profit = new int[N+1];
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			
			time[i] = Integer.parseInt(st.nextToken());
			profit[i] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N+2];
		for(int i=1;i<=N;i++) {
			if(i+time[i]-1 <= N) {
				dp[i+time[i]-1] = Math.max(dp[i+time[i]-1], dp[i-1]+profit[i]);
			}
			 
			dp[i] = Math.max(dp[i], dp[i-1]);
		}
		
		System.out.println(dp[N]);
		
	}

}
