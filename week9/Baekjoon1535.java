/*
 * BOJ 1535번 : 안녕
 * 메모리 : 11,612kb
 * 시간 : 72ms
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] blood;
	static int[] happy;
	static int[] dp;
	static int maxHappy;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		blood = new int[N];
		happy = new int[N];
		dp = new int[100];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			blood[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			happy[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 99; j >= blood[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - blood[i]] + happy[i]);
			}
		}

		maxHappy = 0;
		for (int i = 0; i < 100; i++) {
			maxHappy = Math.max(maxHappy, dp[i]);
		}

		sb.append(maxHappy);
		System.out.print(sb);
		br.close();
	}
}
