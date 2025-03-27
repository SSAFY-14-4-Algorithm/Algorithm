package study;



import java.io.*;
import java.util.*;

/**
 * 
 *  1. bfs로 인접가능한 나라들 찾기
 *  2. 나라 찾으면 인구 다 더해서 나눠줌
 *  3. 반복 지져스
 *  
 *  
 * 메모리: 	294760 kb
 * 시간 : 	576 ms
 *
 */


public class Baekjoon16234 {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	static int N, L, R, result;
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean check = false;
		
		while(!check) {
			visited = new boolean[N][N];
			int count = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N ; j++) {
					
					if(!visited[i][j]) {
						bfs(i, j);
						count++;
					}
				}
			}
			
			if(count == N * N) check = true;
			else result++;
		}
		
		System.out.println(result);
	}
	
	public static void bfs(int i, int j) {
		Queue<int[]> q = new ArrayDeque<>();
		List<int[]> union = new ArrayList<>();
		int count = 0;
		int sum = 0;
		
		q.offer(new int[] {i, j});
		visited[i][j] = true;
		
		while(!q.isEmpty()) {
			
			int[] cur = q.poll();
			
			int x = cur[0];
			int y = cur[1];
			
			union.add(new int[] {x, y});
			count++;
			sum += map[x][y];
			
			for(int dir = 0; dir < 4; dir++) {
				
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;
				if(Math.abs(map[x][y] - map[nx][ny]) < L || Math.abs(map[x][y] - map[nx][ny]) > R) continue;
				
				q.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
		
		if (count == 1) return;

		int newPopulation = sum / count;
		for (int[] p : union) {
			map[p[0]][p[1]] = newPopulation;
		}
		
	}
}

