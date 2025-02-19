import java.util.*;
import java.io.*;
public class Baekjoon17472 {
	static int[] parent;
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class Edge{
		int s;
		int e;
		int d;
		public Edge(int s, int e, int d) {
			this.s = s;
			this.e = e;
			this.d = d;
		}
	}
	
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public static void union(int x, int y) {
		if(x > y) {
			parent[x] = y;
		} else {
			parent[y] = x;
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean[][] visited = new boolean[N][M];
		int islandCnt = 1;
		int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 1 && !visited[i][j]) {
					Queue<Node> q = new ArrayDeque<>();
					q.add(new Node(i, j));
					visited[i][j] = true;
					map[i][j] = islandCnt;
					while(!q.isEmpty()) {
						Node cur = q.poll();
						int x = cur.x;
						int y = cur.y;
						for(int k = 0; k < 4; k++) {
							int nx = x + dir[k][0];
							int ny = y + dir[k][1];
							if(0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && map[nx][ny] == 1) {
								map[nx][ny] = islandCnt;
								visited[nx][ny] = true;
								q.add(new Node(nx, ny));
							}
						}
					}
					islandCnt++;
				}
			}
		}
		islandCnt -= 1;
		PriorityQueue<Edge> q = new PriorityQueue<>((o1, o2) -> o1.d-o2.d);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 0) {
					for(int k = 0; k < 4; k++) {
						int cnt = 0;
						int x = i;
						int y = j;
						while(true) {
							int nx = x + dir[k][0];
							int ny = y + dir[k][1];
							if(0 <= nx && nx < N && 0 <= ny && ny < M) {
								if(map[nx][ny] == 0) {
									x = nx;
									y = ny;
									cnt++;
								} else if(map[nx][ny] != 0) {
									if(cnt > 1) {
										q.add(new Edge(map[i][j], map[nx][ny], cnt));
									}
									break;
								}
							} else {
								break;
							}
						}
					}
				}
			}
		}
		int answer = 0;
		parent = new int[islandCnt+1];
		for(int i = 1; i <= islandCnt; i++) {
			parent[i] = i;
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			Edge cur = q.poll();
			int s = find(cur.s);
			int e = find(cur.e);
			if(s != e) {
				union(s, e);
				answer += cur.d;
				cnt++;
			}
			if(cnt == islandCnt-1) {
				break;
			}
		}
		if(cnt != islandCnt-1) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}

}
