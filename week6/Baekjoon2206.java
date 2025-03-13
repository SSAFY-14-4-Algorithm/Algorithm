package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 180356
 * 시간: 1068
 *
 *벽들들을 모두 하나씩 뺐을 경우에서 bfs 돌리가지고 최단경로 찾아가지고 기 -> 시간초과
 *
 */

public class Baekjoon2206 {
	
		static int[] dx = {0, 0, 1, -1};
		static int[] dy = {1, -1, 0, 0};
		
		static int N, M, result;
		static int[][] map;
		static boolean[][][] visited;
		
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        
	        N = Integer.parseInt(st.nextToken());
	        M = Integer.parseInt(st.nextToken());
	        result = Integer.MAX_VALUE;
	        
	        map = new int[N][M];
	        visited = new boolean[N][M][2];
	        
	        for(int i = 0; i < N ; i++) {
	        	String[] word = br.readLine().split("");
	        	for(int j = 0; j < M ; j++) {
	        		map[i][j] = word[j].charAt(0) - '0';

	        	}
	        }
	        
	        int result = bfs();
	        System.out.println(result);
	        
	    }
		
		public static int bfs() {
			Queue<int[]> q = new ArrayDeque<>();
			
			q.offer(new int[] {0, 0, 1, 0});
			visited[0][0][0] = true;
			
			while (!q.isEmpty()) {
				int[] cur = q.poll();
				int x = cur[0], y = cur[1], dist = cur[2], broken = cur[3];
				
				 if (x == N - 1 && y == M - 1) return dist;
				
				for(int dir = 0; dir < 4 ; dir++) {
					int nx = x + dx[dir];
					int ny = y + dy[dir];
					
					if (nx >= 0 && ny >= 0 && nx < N && ny < M) {
	                    if (map[nx][ny] == 0 && !visited[nx][ny][broken]) {
	                        q.offer(new int[]{nx, ny, dist + 1, broken});
	                        visited[nx][ny][broken] = true;
	                    }

	                    if (map[nx][ny] == 1 && broken == 0 && !visited[nx][ny][1]) {
	                        q.offer(new int[]{nx, ny, dist + 1, 1});
	                        visited[nx][ny][1] = true;
	                    }
	                }
	            }
	        }

	        return -1;
	    }
	}
