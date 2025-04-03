/*
 * BOJ 14500번 : 테트로미노
 * 메모리 : 322,268kb
 * 시간 : 356ms
 *
 * 그냥 구현한다... 총 19가지
 */

import java.io.*;
import java.util.*;

public class Baekjoon14500 {
	static int N, M;
	static int[][] map;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}

		max = 0;
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				check(n, m);
			}
		}

		sb.append(max);
		System.out.print(sb);
		br.close();
	}

	static void check(int x, int y) {
		// 1. ㅡ 가로
		if (y+3 < M)
			max = Math.max(max, map[x][y]+map[x][y+1]+map[x][y+2]+map[x][y+3]);

		// ㅣ 세로
		if (x+3 < N)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+2][y]+map[x+3][y]);

		// 2. ㅁ
		if (x+1 < N && y+1 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x][y+1]+map[x+1][y+1]);

		// 3. ㄴ
		if (x+2 < N && y+1 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+2][y]+map[x+2][y+1]);

		if (x+2 < N && y-1 >= 0)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+2][y]+map[x+2][y-1]);

		if (x+1 < N && y+2 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x][y+1]+map[x][y+2]);

		if (x+1 < N && y+2 < M)
			max = Math.max(max, map[x][y]+map[x][y+1]+map[x][y+2]+map[x+1][y+2]);

		if (x+2 < N && y+1 < M)
			max = Math.max(max, map[x][y]+map[x][y+1]+map[x+1][y+1]+map[x+2][y+1]);

		if (x+2 < N && y-1 >= 0)
			max = Math.max(max, map[x][y]+map[x][y-1]+map[x+1][y-1]+map[x+2][y-1]);

		if (x+1 < N && y-2 >= 0)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+1][y-1]+map[x+1][y-2]);

		if (x+1 < N && y+2 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+1][y+1]+map[x+1][y+2]);

		// 4. ㄴㄱ
		if (x+1 < N && y+2 < M)
			max = Math.max(max, map[x][y]+map[x][y+1]+map[x+1][y+1]+map[x+1][y+2]);

		if (x+1 < N && y-2 >= 0)
			max = Math.max(max, map[x][y]+map[x][y-1]+map[x+1][y-1]+map[x+1][y-2]);

		if (x+2 < N && y+1 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+1][y+1]+map[x+2][y+1]);

		if (x+2 < N && y-1 >= 0)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+1][y-1]+map[x+2][y-1]);

		// 5. ㅗ 
		if (x+1 < N && y+2 < M)
			max = Math.max(max, map[x][y]+map[x][y+1]+map[x][y+2]+map[x+1][y+1]);

		if (x+1 < N && y-1 >= 0 && y+1 < M)
			max = Math.max(max, map[x][y]+map[x+1][y-1]+map[x+1][y]+map[x+1][y+1]);

		if (x+2 < N && y+1 < M)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+2][y]+map[x+1][y+1]);

		if (x+2 < N && y-1 >= 0)
			max = Math.max(max, map[x][y]+map[x+1][y]+map[x+2][y]+map[x+1][y-1]);
	}
}