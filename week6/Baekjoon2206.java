import java.io.*;
import java.util.*;

import java.util.*;
import java.io.*;

/*
 * 65548kb, 464ms 
 */
//BFS 2번한 풀이
//시작점에서 BFS 1번
//도착점에서 BFS 1번
//벽에도 체크를 해주되, 벽에서는 큐에 넣지 않기
//시작점 BFS, 도착점 BFS가 벽에 둘 다 도착했다면
//그 두 값의 합 -1 (벽 중복으로 들어간 거 빼주기)
//혹은, 시작점에서 도착점까지 갈 수 있다면
//시작점 BFS의 도착점의 값 중 최소값

public class Baekjoon2206 {
	static int N, M;
	static String[] map;
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void bfs(int[][] dist, int sx, int sy) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(sx, sy));
		dist[sx][sy] = 1;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			for(int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if(0 <= nx && nx < N && 0 <= ny && ny < M && dist[nx][ny] == 0) {
					dist[nx][ny] = dist[cur.x][cur.y] + 1;
					if(map[nx].charAt(ny) != '1') {
						q.add(new Node(nx, ny));
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new String[N];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine();
		}
		int[][] fdist = new int[N][M];
		int[][] bdist = new int[N][M];
		bfs(fdist, 0, 0);
		bfs(bdist, N-1, M-1);
		int res = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i].charAt(j) == '1' && fdist[i][j] != 0 && bdist[i][j] != 0) {
					res = Math.min(res, fdist[i][j] + bdist[i][j] -1);
				}
			}
		}
		if(fdist[N-1][M-1] != 0) {
			res = Math.min(res, fdist[N-1][M-1]);
		}
		
		if(res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}
}



/*
 * 124520kb, 872ms
 */
//3차원 배열을 이용한 정석 풀이
//
//public class Baekjoon2206 {
//	static class Node{
//		int x;
//		int y;
//		boolean b;
//		public Node(int x, int y, boolean b) {
//			this.x = x;
//			this.y = y;
//			this.b = b;
//		}
//	}
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int N = Integer.parseInt(st.nextToken());
//        int M = Integer.parseInt(st.nextToken());
//        int[][] map = new int[N][M];
//        for(int i = 0; i < N; i++) {
//        	char[] arr = br.readLine().toCharArray();
//        	for(int j = 0; j < M; j++) {
//        		map[i][j] = arr[j] - '0';
//        	}
//        }
//        Queue<Node> q = new ArrayDeque<>();
//        q.add(new Node(0, 0, false));
//        boolean[][][] visited = new boolean[N][M][2];
//        visited[0][0][0] = true;
//        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//        int[][][] cnt = new int[N][M][2];
//        cnt[0][0][0] = 1;
//        cnt[0][0][1] = 1;
//        A: while(!q.isEmpty()) {
//        	Node cur = q.poll();
//        	int x = cur.x;
//        	int y = cur.y;
//        	for(int i = 0; i < 4; i++) {
//        		int nx = x + dir[i][0];
//        		int ny = y + dir[i][1];
//        		if(nx >= N || nx < 0 || ny < 0 || ny >= M) continue;
//        		if(visited[nx][ny][0]) continue;
//        		if(cur.b && map[nx][ny] == 1) continue;
//        		boolean b = cur.b;
//        		if(map[nx][ny] == 1) {
//        			b = true;
//        		}
//        		if(visited[nx][ny][b?1:0]) continue;
//        		q.add(new Node(nx, ny, b));
//        		visited[nx][ny][b?1:0] = true;
//        		cnt[nx][ny][b?1:0] = cnt[x][y][cur.b?1:0] + 1;
//        		if(nx == N-1 && ny == M-1) {
//        			break A;
//        		}
//        	}
//        }
//        int answer = Integer.MAX_VALUE;
//        if(cnt[N-1][M-1][0] != 0) {
//        	answer = cnt[N-1][M-1][0];
//        }
//        if(cnt[N-1][M-1][1] != 0) {
//        	answer = Math.min(answer, cnt[N-1][M-1][1]);
//        }
//        if(answer == Integer.MAX_VALUE) {
//        	System.out.println(-1);
//        }else {
//        	System.out.println(answer);
//        }
//    }
//}