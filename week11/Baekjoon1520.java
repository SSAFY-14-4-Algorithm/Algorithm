/*
 * BOJ 1520번 : 내리막길
 * 메모리 : 39,540번
 * 시간 : 276ms
 *
 * dfs로 모든 경로 재탐색하려고 했지만,
 * 불필요한 백트래킹이 발생해서 StackOverflowError 발생
 *
 * dp 배열에 경로값 저장해서 메모이제이션
 */

import java.io.*;
import java.util.*;

public class Baekjoon1520 {
	static int M, N;
	static int[][] map;
	static int[][] dp;
	static int H;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			for(int n = 0; n < N; n++) {
				map[m][n] = Integer.parseInt(st.nextToken());
				dp[m][n] = -1;
			}
		}

		H = dfs(0, 0); // x, y

		sb.append(H);
		System.out.print(sb);
		br.close();
	}

	private static int dfs(int x, int y) {
		if(x==M-1 && y==N-1) {
			return 1; // 도착지에 도착했으면 경로 1
		}

		if(dp[x][y] != -1) { // 0이 아니라면 이전의 경로값 사용
			return dp[x][y];
		}

		dp[x][y] = 0; // 초기화

		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
			if(map[x][y] > map[nx][ny]) {
				dp[x][y] += dfs(nx, ny);
			}
		}

		return dp[x][y];
	}
}