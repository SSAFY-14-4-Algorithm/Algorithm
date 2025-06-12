import java.io.*;
import java.util.*;
/* 빙산
 * BFS풀이
 * 103088kb, 892ms
 */

public class Baekjoon2573 {
	public static class Node {
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean check = true;
		int cnt = 1;
		Queue<Node> q = new ArrayDeque<>();
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int time = 0;
		A:while(cnt != 0) {
			cnt = 0;
			boolean[][] visited = new boolean[N][M];
			for(int i = 1; i < N-1; i++) {
				for(int j = 1; j < M-1; j++) {
					if(arr[i][j] != 0 && !visited[i][j]) {
						q.add(new Node(i, j));
						visited[i][j] = true;
						while(!q.isEmpty()) {
							Node cur = q.poll();
							int x = cur.x;
							int y = cur.y;
							for(int k = 0; k < 4; k++) {
								int nx = x + dir[k][0];
								int ny = y + dir[k][1];
								if(1 <= nx && nx < N-1 && 1 <= ny && ny < M-1 && !visited[nx][ny] && arr[nx][ny] > 0) {
									visited[nx][ny] = true;
									q.add(new Node(nx, ny));
								}
							}
						}
						cnt++;
						if(cnt >= 2) {
							break A;
						}
					}
				}
			}
			boolean[][] zero = new boolean[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					if(arr[i][j] == 0 && !zero[i][j]) {
						for(int k = 0; k < 4; k++) {
							int nx = i + dir[k][0];
							int ny = j + dir[k][1];
							if(0 <= nx && nx < N && 0 <= ny && ny < M && arr[nx][ny] > 0) {
								arr[nx][ny] -= 1;
								zero[nx][ny] = true;
							}
						}
					}
				}
			}
			time++;
		}
		if(cnt >= 2) {
			System.out.println(time);
		} else {
			System.out.println(0);
		}
	}
}