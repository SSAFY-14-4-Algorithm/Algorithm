package ct2;
import java.util.*;
import java.io.*;
/*
 * 메모리:18868kb, 160ms
 */
public class Baekjoon25624 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 0; testCase < T; testCase++) {
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N+1];
			int[][] dp = new int[N+1][N+1];
			for(int i = 1; i < N+1; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				arr[i] += arr[i-1];
			}
			for(int i = 2; i <= N; i++) {
				int len = N-i+1;
				for(int j = 1; j <= len; j++) {
					int minValue = Integer.MAX_VALUE;
					int end = j+i-1;
					for(int k = j; k < end; k++) {
						minValue = Math.min(minValue, dp[j][k] + dp[k+1][end]);
					}
					dp[j][end] = minValue + arr[end]-arr[j-1];
				}
			}
			for(int i = 1; i <= N; i++) {
				System.out.println(Arrays.toString(dp[i]));
			}
			sb.append(dp[1][N]).append("\n");
		}
		System.out.print(sb);
	}
}