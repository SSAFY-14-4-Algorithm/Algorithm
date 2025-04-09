import java.util.*;
import java.io.*;

public class Baekjoon15486 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N+1];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(dp[i], i > 0 ? dp[i-1] : 0);
			if(i+t < N+1) {
				dp[i+t] = Math.max(dp[i]+p, dp[i+t]);
			}
		}
		dp[N] = Math.max(dp[N-1], dp[N]);
		System.out.println(dp[N]);
	}
}
