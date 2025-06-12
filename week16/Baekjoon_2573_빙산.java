/*
 * BOJ 2573번 : 빙산
 * 메모리 : 162,376kb
 * 시간 : 500ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_2573_빙산 {
	static int N, M;
	static int[][] map; 
	static int[][] melt; 
	static boolean[][] visited; 
	static int year;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for(int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			int count = countIce();
			if(count >= 2) {
				sb.append(year);
				break;
			}
			if(count == 0) {
				sb.append(0);
				break;
			}
			
			meltIce();
			year++;
		}
		
		System.out.println(sb);
		br.close();
	}

	// 1. 빙산 덩어리 개수 세기
	private static int countIce() {
		boolean[][] visited = new boolean[N][M];
		int count = 0;
		
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] > 0 && !visited[n][m]) {
					bfs(n, m, visited);
					count++;
				}
			}
		}
		
		return count;
	}
	
	// 2. 하나의 빙산 덩어리 탐색
	private static void bfs(int x, int y, boolean[][] visited) {
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.offer(new int[] { x, y });
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] > 0) {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny });
				}
			}
		}
	}
	
	// 3. 빙산 녹이기
	private static void meltIce() {
		melt = new int[N][M]; // 녹을 양 저장
		
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] > 0) { // 빙산이 있는 칸
					int waterCount = 0;
					for(int d = 0; d < 4; d++) {
						int nx = n + dx[d];
						int ny = m + dy[d];
						
						if(nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
							waterCount++; // 주변이 물이면 카운트 증가
						}
					}
					melt[n][m] = waterCount;
				}
			}
		}
		
		for(int n = 0; n < N; n++) {
			for(int m = 0; m < M; m++) {
				if(map[n][m] > 0) {
					map[n][m] -= melt[n][m];
					if(map[n][m] < 0)
						map[n][m] = 0;
				}
			}
		}
	}

}
