<<<<<<< HEAD
/*
 * BOJ 17070번 : 파이프 옮기기 1
 * 메모리 : 13,736kb
 * 시간 : 140ms
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon17070 {
	static int N;
	static int[][] map;
	static int answer;
	static int[] dx = new int[] { 1, 0, 1 }; // 우, 하, 우하대각선
	static int[] dy = new int[] { 0, 1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		for(int r = 1; r <= N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		answer = 0;
		dfs(1, 2, 0);
		
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
	
	private static void dfs(int x, int y, int dir) {
		if(x == N && y == N) {
			answer++;
			return;
		}
		
		// 가로 파이프
		if(dir != 1 && y+1 <= N && map[x][y+1] == 0) {
			dfs(x, y+1, 0);
		}
		
		// 세로 파이프
		if(dir != 0 && x+1 <= N && map[x+1][y] == 0) {
			dfs(x+1, y, 1);
		}
		
		// 대각선 파이프
		if(x+1 <= N && y+1 <= N && map[x+1][y+1] == 0 && map[x][y+1] == 0 && map[x+1][y] == 0) {
			dfs(x+1, y+1, 2);
		}
=======

public class Main {

	public static void main(String[] args) {
		
>>>>>>> f8a4cc1b59636f9b772aeea870b91b29b6c5c533
	}
}
