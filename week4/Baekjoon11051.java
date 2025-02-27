import java.util.*;
import java.io.*;

public class Baekjoon11051 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][K+1];
		for(int i = 0; i < N; i++) {
			dp[i][0] = 1;
		}
		dp[0][0] = 1;
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= Math.min(i, K); j++) {
				if(j == 0) {
					dp[i][0] = 1;
				} else {
					dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
					dp[i][j] %= 10007;
				}
			}
		}
		System.out.println(dp[N][K]);
	}
}
