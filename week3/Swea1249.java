import java.util.*;
import java.io.*;

public class Swea1249 {
	static class Node {
		int x;
		int y;
		int d;
		public Node(int x, int y, int d) {
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			for(int i = 0; i < N; i++) {
				char[] cs = br.readLine().toCharArray();
				for(int j = 0; j < N; j++) {
					map[i][j] = cs[j] - '0';
				}
			}
			int[][] dist = new int[N][N];
			boolean[][] visited = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			dist[0][0]= 0;
			int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> o1.d-o2.d);
			q.add(new Node(0, 0, 0));
			while(!q.isEmpty()) {
				Node cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				int d = cur.d;
				if(visited[x][y] || dist[x][y] < d) continue;
				visited[x][y] = true;
				if(x == N-1 && y == N-1) {
					break;
				}
				for(int i = 0; i < 4; i++) {
					int nx = x + dir[i][0];
					int ny = y + dir[i][1];
					if(0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
						int cost = d + map[nx][ny];
						if(cost < dist[nx][ny]) {
							dist[nx][ny] = cost;
							q.add(new Node(nx, ny, cost));
						}
					}
				}
			}
			sb.append(dist[N-1][N-1]).append("\n");
		}
		System.out.print(sb);
	}
}
