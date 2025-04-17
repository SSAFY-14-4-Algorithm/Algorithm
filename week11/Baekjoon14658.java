/*
 * BOJ 14658번 : 하늘에서 별똥별이 빗발친다
 * 메모리 : 12,100kb
 * 시간 : 104ms
 *
 * 최적해를 구하기 위해 -> x축 y축으로 나누기!!!
 */

import java.io.*;
import java.util.*;

public class Baekjoon14658 {
	static int N, M, L, K;
	static int[][] stars;
	static int maxCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		stars = new int[K][2];
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			stars[k][0] = Integer.parseInt(st.nextToken());
			stars[k][1] = Integer.parseInt(st.nextToken());

		}

		maxCount = 0;

		for(int i = 0; i < K; i++) {
			for(int j = 0; j < K; j++) {
				int baseX = stars[i][0];
				int baseY = stars[j][1];

				int count = 0;

				for(int k = 0; k < K; k++) {
					int x = stars[k][0];
					int y = stars[k][1];

					if(x >= baseX && y >= baseY && x <= baseX+L && y <= baseY+L) {
						count++;
					}
				}

				maxCount = Math.max(maxCount, count);
			}
		}

		sb.append(K-maxCount);
		System.out.print(sb);
		br.close();
	}
}