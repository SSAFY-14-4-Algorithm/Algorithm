/*
 * BOJ 14888번 : 연산자 끼워넣기
 * 메모리 : 12,436kb
 * 시간 : 72ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon14888 {
	static int N;
	static int[] A;
	static int[] oper;
	static int max, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		oper = new int[4];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			oper[i] = Integer.parseInt(st.nextToken());
		}

		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		dfs(A[0], 1);

		sb.append(max).append("\n").append(min);
		System.out.println(sb);
		br.close();
	}

	public static void dfs(int num, int cnt) {
		if(cnt == N) {
			max = Math.max(max, num);
			min = Math.min(min, num);
			return;
		}

		for(int i = 0; i < 4; i++) {
			if(oper[i] > 0) {
				oper[i]--;
				switch (i) {
					case 0:
						dfs(num+A[cnt], cnt+1);
						break;
					case 1:
						dfs(num-A[cnt], cnt+1);
						break;
					case 2:
						dfs(num*A[cnt], cnt+1);
						break;
					case 3 :
						dfs(num/A[cnt], cnt+1);
						break;
				}
			}
			oper[i]++;
		}
	}
}