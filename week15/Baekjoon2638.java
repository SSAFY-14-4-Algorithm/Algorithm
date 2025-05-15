import java.util.*;
import java.io.*;
/* 치즈
 * 15428kb, 140ms
 */
public class Baekjoon2638 {
	static class Node{
		int x, y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static int N, M, cheese;
	static int[][] map;
	static Queue<Node> q;
	static void dfs(Node cur) {
		int x = cur.x;
		int y = cur.y;
		for(int i = 0; i < 4; i++) {
			int nx = x + dir[i][0];
			int ny = y + dir[i][1];
			if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
				if(map[nx][ny] == 0) {
					visited[nx][ny] = true;
					dfs(new Node(nx, ny));
				} else {
					map[nx][ny]++;
				}
				if(map[nx][ny] == 3) {
					visited[nx][ny] = true;
					cheese--;
					q.add(new Node(nx, ny));
				}
			}
		}
	}
	
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cheese = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					cheese++;
				}
			}
		}
		int ans = 0;
		q = new ArrayDeque<>();
		q.add(new Node(0, 0));
		visited = new boolean[N][M];
		visited[0][0] = true;
		while(cheese != 0) {
			int len = q.size();
			while(len-- > 0) {
				dfs(q.poll());
			}
			ans++;
		}
		System.out.println(ans);
	}
}
