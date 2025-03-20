<<<<<<< HEAD
/*
 * BOJ 2206번 : 벽 부수고 이동하기
 * 메모리 : 107,248kb
 * 시간 : 556ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon2206 {
	static int N, M;
	static int[][] map;
	static boolean[][][] visited;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };
	static int minDis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M][2];
		for(int n = 0; n < N; n++) {
			String str = br.readLine();
			for(int m = 0; m < M; m++) {
				map[n][m] = str.charAt(m)-'0';
			}
		}
		
		minDis = Integer.MAX_VALUE;
		bfs();
		
		if(minDis == Integer.MAX_VALUE) {
			sb.append(-1);
		} else {
			sb.append(minDis);
		}
		System.out.print(sb);
		br.close();
	}
	
	private static void bfs() {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { 0, 0, 0, 1 });
		visited[0][0][0] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int cBreak = curr[2];
			int cDis = curr[3];
			
			if(cx == N-1 && cy == M-1) {
				minDis = cDis;
				return;
			}
			
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M 
						&& map[nx][ny] == 0 && !visited[nx][ny][cBreak]) {
					visited[nx][ny][cBreak] = true;
					q.offer(new int[] { nx, ny, cBreak, cDis+1 });
				}
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M 
						&& map[nx][ny] == 1 && cBreak == 0) { 
					if(!visited[nx][ny][cBreak+1]) {
						visited[nx][ny][cBreak+1] = true;
						q.offer(new int[] { nx, ny, cBreak+1, cDis+1 });
					}
				}
			}
		}
	}		
=======

public class Main {

	public static void main(String[] args) {
		
	}
>>>>>>> f8a4cc1b59636f9b772aeea870b91b29b6c5c533
}
