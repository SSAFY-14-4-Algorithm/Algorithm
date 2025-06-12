import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

//메모리 시간
public class Baekjoon2573 {
	static int[][] iceberg;
	static int[] dr = {0, -1, 0, 1};
	static int[] dc = {1, 0, -1, 0};
	static int n, m, icebergCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		iceberg = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				iceberg[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int year = 0;

		while(true) {
			int icebergCount = countIceberg();

			if (icebergCount >= 2) {
				System.out.println(year);
				break;
			}

			if (icebergCount == 0) {
				System.out.println(0);
				break;
			}

			year++;
			melt();

		}
	}

	static void melt() {
		int[][] meltAmount = new int[n][m];

		//각 빙하가 얼마나 녹을지 meltAmount에 따로 계산
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (iceberg[i][j] > 0) {
					int seaCount = 0;
					for (int d = 0; d < 4; d++) {
						int nr = i + dr[d];
						int nc = j + dc[d];
						if (nr >= 0 && nr < n && nc >= 0 && nc < m && iceberg[nr][nc] == 0) {
							seaCount++;
						}
					}
					meltAmount[i][j] = seaCount;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				iceberg[i][j] = Math.max(0, iceberg[i][j] - meltAmount[i][j]);
			}
		}
	}

	static int countIceberg() {
		boolean[][] visited = new boolean[n][m];
		int count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (iceberg[i][j] > 0 && !visited[i][j]) {
					bfs(i, j, visited);
					count++;
				}
			}
		}
		return count;
	}

	static void bfs(int x, int y, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y});
		visited[x][y] = true;

		while(!q.isEmpty()) {
			int[] idx = q.poll();
			int dx = idx[0];
			int dy = idx[1];
			for (int d = 0; d < 4; d++) {
				int nr = dx + dr[d];
				int nc = dy + dc[d];

				if (nr >= n || nr < 0 || nc >= m || nc < 0) continue;

				if (!visited[nr][nc] && iceberg[nr][nc] > 0) {
					q.add(new int[] {nr, nc});
					visited[nr][nc] = true;
				}
			}
		}
	}
}
