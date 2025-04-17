import java.io.*;
/*
 * 메모리 43564KB
 * 시간 316ms
 */
public class Baekjoon1520 {
	static int m, n;
	static int[][] map, dp;
	static int[] dx = {1, -1, 0, 0}; 
	static int[] dy = {0, 0, 1, -1}; 
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String[] s = br.readLine().split(" ");
		m = Integer.parseInt(s[0]);
		n = Integer.parseInt(s[1]);

		map = new int[m][n];
		dp = new int[m][n];

		for(int i=0;i<m;i++) {
			String[] row = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(row[j]);
				dp[i][j] = -1;
			}
		}

		System.out.println(dfs(0, 0));
	}

	public static int dfs(int x, int y) {
		//도착지에 도달한 경우
		if(x == m-1 && y == n-1) return 1;
		//간적 있는 곳이면 계산한 값 사용
		if(dp[x][y] != -1) return dp[x][y];

		dp[x][y] = 0;

		for(int i=0;i<4;i++) {
			int nx = x + dy[i];
			int ny = y + dx[i];
			
			if(isRange(nx,ny)) {
				if(map[nx][ny] < map[x][y]) {
					dp[x][y] += dfs(nx, ny);
				}
			}
		}

		return dp[x][y];
	}
	public static boolean isRange(int x,int y) {
		return x>=0 && y>=0 && x<m && y<n;
	}
}