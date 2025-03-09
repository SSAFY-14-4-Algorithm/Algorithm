package algorithm;
import java.io.*;
import java.util.*;

public class Baekjoon1987 {
	static int R, C;
	static char[][] board;
	static boolean[] alpha;
	static int max;
	static int[] dx = new int[] { -1, 1, 0, 0 };
	static int[] dy = new int[] { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		alpha = new boolean[26];
		for(int r = 0; r < R; r++) {
			String str = br.readLine();
			for(int c = 0; c < C; c++) {
				board[r][c] = str.charAt(c);
			}
		}
		
		max = Integer.MIN_VALUE;
//		System.out.println(board[0][0]-65);
		dfs(0, 0, 1);
		
		sb.append(max);
		System.out.print(sb);
		br.close();
	}
	
	private static void dfs(int x, int y, int len) {
		alpha[board[x][y]-65] = true;
		max = Math.max(max, len);
		
		for(int d = 0; d < 4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if(nx >= 0 && ny >= 0 && nx < R && ny < C && !alpha[board[nx][ny]-65]) {
				dfs(nx, ny, len+1);
				alpha[board[nx][ny]-65] = false;
			}
		}
	}
}
