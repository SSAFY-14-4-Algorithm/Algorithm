import java.io.*;
import java.util.*;

public class Baekjoon2589 {

	static int l, w;
	static char[][] map;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};
	static int ans;
	static boolean visited[][];

	//메모리 222056kb 시간 508ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());
		map = new char[l][w];
		ans = 0;

		for (int i = 0; i < l ;i ++) {
			String str = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		for(int i = 0; i < l; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == 'L') {
					bfs(i, j, 0);
				}
			}
		}
		System.out.println(ans);

	}

	static void bfs(int x, int y, int level) {
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[l][w];
		q.add(new int[] {x, y, level});
		visited[x][y] = true;
		int maxLevel = 0;

		while(!q.isEmpty()) {
			int[] pos = q.poll();
			int posX = pos[0];
			int posY = pos[1];
			int posLevel = pos[2];
			maxLevel = Math.max(maxLevel, posLevel);
			for (int d = 0; d < 4; d++) {
				int nx = posX + dx[d];
				int ny = posY + dy[d];

				if(nx >= l || nx < 0 || ny >= w || ny < 0 || visited[nx][ny]) continue;
				if (map[nx][ny] == 'L') {
					q.add(new int[] {nx, ny, posLevel+1});
					visited[nx][ny] = true;
				}
			}
		}
		ans = Math.max(ans, maxLevel);
	}

}
