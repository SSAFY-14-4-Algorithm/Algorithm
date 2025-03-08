import java.io.*;
import java.util.*;

//42428KB, 232ms
//비트마스킹 + BFS
public class Baekjoon1987 {
	static class Node {
		int bit;
		int x;
		int y;
		int cnt;
		public Node(int x, int y, int bit, int cnt) {
			this.x = x;
			this.y = y;
			this.bit = bit;
			this.cnt = cnt;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		for(int i = 0; i < R; i++) {
			String s = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = s.charAt(j) - 'A';
			}
		}
		int[][] visited = new int[R][C];
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(0, 0, 1<<map[0][0], 1));
		int answer = 0;
		A:while(!q.isEmpty()) {
			Node cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			for(int i = 0; i < 4; i++) {
				int nx = x + dir[i][0];
				int ny = y + dir[i][1];
				if(0 <= nx && nx < R && 0 <= ny && ny < C && (cur.bit & (1<<map[nx][ny])) == 0) {
					if(visited[nx][ny] != (cur.bit|(1<<map[nx][ny]))) { 
						visited[nx][ny] = cur.bit|(1<<map[nx][ny]);
						q.add(new Node(nx, ny, visited[nx][ny], cur.cnt+1));
						if(cur.cnt+1 == 26) {
							answer = 26;
							break A;
						}
					}
				}
			}
			answer = Math.max(answer, cur.cnt);
		}
		System.out.println(answer);
	}
}