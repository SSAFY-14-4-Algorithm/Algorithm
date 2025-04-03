import java.util.*;
import java.io.*;
/*
 * 14272kb, 104ms
 */
public class Baekjoon1535 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] L = new int[N];
		int[] J = new int[N];
		int[] dp = new int[101];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			L[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			J[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < N; i++) {
			for(int j = 100; j > L[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j-L[i]] + J[i]);
			}
		}
		System.out.println(dp[100]);
	}

}
