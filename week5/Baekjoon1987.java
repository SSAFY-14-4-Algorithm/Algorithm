package week5;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 12,240 kb
 * 실행 시간 : 824 ms
 */

public class Baekjoon1987 {
	
	static final int[] dr = {0,1,0,-1};
	static final int[] dc = {1,0,-1,0};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static char[][] alpha;
	static boolean[] visited; 
	static int R, C, maxCnt;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		maxCnt = 1;
		alpha = new char[R][C];
		visited = new boolean[26]; // A(65) ~ Z(90)
		for(int i=0;i<R;i++) {
			String input = br.readLine()
;			for(int j=0;j<C;j++) {
				alpha[i][j] = input.charAt(j);
			}
		}
		
		visited[alpha[0][0] - 'A'] = true; 
		dfs(0,0,1);

		System.out.println(maxCnt);
	}
	private static void dfs(int r, int c, int count) {
		maxCnt =  Math.max(maxCnt, count);
		
		for(int k=0;k<4;k++) {
			int nr = r + dr[k];
			int nc = c + dc[k];
			
			if(!inRange(nr, nc) || visited[alpha[nr][nc] - 'A']) continue;
			
			visited[alpha[nr][nc] - 'A'] = true;
			dfs(nr, nc, count+1);
			visited[alpha[nr][nc] - 'A'] = false; //백트래킹 : 방문 복원
		}
	}
	
	private static boolean inRange(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}
	
}
