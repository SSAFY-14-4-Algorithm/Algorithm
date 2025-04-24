import java.util.*;
import java.io.*;
/*
 * 169472kb, 340ms
 */
public class Baekjoon2589 {
	static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] map = new char[N][M];
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		Queue<Node> q = new ArrayDeque<>();
		int answer = 0;
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 'L') {
					q.clear();
					q.add(new Node(i, j));
					boolean[][] visited = new boolean[N][M];
					visited[i][j] = true;
					int level = -1;
					while(!q.isEmpty()) {
						int len = q.size();
						for(int  k = 0; k < len; k++) {
							Node cur = q.poll();
							int x = cur.x;
							int y = cur.y;
							for(int l = 0; l < 4; l++) {
								int nx = x + dir[l][0];
								int ny = y + dir[l][1];
								if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && map[nx][ny] == 'L') {
									visited[nx][ny] = true;
									q.add(new Node(nx, ny));
								}
							}
						}
						level++;
					}
					answer = Math.max(answer, level);
				}
			}
		}
		System.out.println(answer);
	}
}
