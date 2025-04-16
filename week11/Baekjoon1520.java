
import java.io.*;
import java.util.*;

public class Baekjoon1520 {
	static int m,n ;
	static int[][] arr;
	static int[][] dp;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	//메모리 35820KB 시간 356ms
	//DP + DFS 사용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new int[m][n];
		dp = new int[m][n];
		for (int i = 0; i < m ; i++) {
			Arrays.fill(dp[i], -1);
		}

		for (int i = 0; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(dfs(0, 0));
	}

	static int dfs(int x, int y) {
		if (x == m-1 && y == n -1) {
			return 1;
		}

		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && nx < m && ny >= 0 && ny < n && arr[nx][ny] < arr[x][y]) {
				dp[x][y] += dfs(nx, ny);
			}
		}

		return dp[x][y];
	}


}
