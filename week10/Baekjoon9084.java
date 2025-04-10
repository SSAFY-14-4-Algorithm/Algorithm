import java.util.*;
import java.io.*;
/*
 * 14120kb, 100ms
 */
public class Baekjoon9084 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] coins = new int[N];
			for(int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int K = Integer.parseInt(br.readLine());
			int[] dp = new int[K+1];
			dp[0] = 1;
			for(int i = 0; i < N; i++) {
               			int target = coins[i];
				for(int j = target; j <= K; j++) {
					dp[j] += dp[j-target];
				}
			}
			sb.append(dp[K]).append("\n");
		}
		System.out.print(sb);
	}
}
