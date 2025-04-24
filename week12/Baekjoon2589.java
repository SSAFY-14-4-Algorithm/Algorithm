/*
 * BOJ 2589번 : 보물섬
 * 메모리 : 219,000kb
 * 시간 : 424ms
 * 
 * 1. 모든 육지(L)를 시작점으로 bfs 실행
 * 2. 최대 거리 구하기
 * 3. 최대 거리에서 최간 거리(대각선 이동 포함) bfs 실행
 * 4. 최대 거리에서 가장 가까운 L 탐색
 */

import java.io.*;
import java.util.*;

public class Baekjoon2589 {
	static int N, M;
	static char[][] map;
	static int[] dx = new int[] { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dy = new int[] { 0, 0, -1, 1, -1, 1, 1, -1 };
	static int maxDist, minDist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		maxDist = Integer.MIN_VALUE;
		List<int[]> maxDistList = new ArrayList<>();
		
		for (int n = 0; n < N; n++) {
			String str = br.readLine();
			for (int m = 0; m < M; m++) {
				map[n][m] = str.charAt(m);
			}
		}
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if(map[n][m] == 'L') {
					int dist = bfs(n, m);
					if(dist > maxDist) {
						maxDist = dist;
						maxDistList.clear(); // 새로운 최장 거리 찾았으니까 클리어
						maxDistList.add(new int[] { n, m });
					} else if(dist == maxDist) {
						maxDistList.add(new int[] { n, m });
					}
				}
			}
		}
		
		minDist = Integer.MAX_VALUE;
		for(int[] list : maxDistList) {
			minDist = Math.max(minDist, bfs2(list[0], list[1]));
		}
		
		sb.append(maxDist);
		System.out.println(sb);
		br.close();
	}
	
	// 1. 모든 L에서 최장 거리 찾기(4방)
	private static int bfs(int x, int y) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { x, y, 0 });
		visited[x][y] = true;
		
		int maxDist = 0;
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int dist = curr[2];
			
			maxDist = Math.max(maxDist, dist);
			
			for(int d = 0; d < 4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 'L') {
					visited[nx][ny] = true;
					q.offer(new int[] { nx, ny, dist+1 });
				}
			}
		}
		
		return maxDist;
	}
	
	// 2. 최장 거리에서 최단 거리 찾기(8방)
	private static int bfs2(int x, int y) {
		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new ArrayDeque<int[]>();
		q.add(new int[] { x, y, 0 });
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int cx = curr[0];
			int cy = curr[1];
			int dist = curr[2];
			
			for(int d = 0; d < 8; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(nx >= 0 && ny >= 0 && nx < N && ny < M && !visited[nx][ny] && map[nx][ny] == 'L') {
					visited[nx][ny] = true;
					return dist+1;
				}
			}
		}
		
		return Integer.MAX_VALUE;
	}
}