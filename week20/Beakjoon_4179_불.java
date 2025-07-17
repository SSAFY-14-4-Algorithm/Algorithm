import java.io.*;
import java.util.*;


/**
 * 시간: 456ms
 * 메모리: 47008KB
 */
public class Beakjoon_4179_불 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	// variables
	static int R, C;
	static char[][] mat;
	static Deque<int[]> jihon = new ArrayDeque<int[]>();
	static Deque<int[]> fire = new ArrayDeque<int[]>();
	
	static int solution() {
		while(!jihon.isEmpty()) {
			for(int i = 0, n = jihon.size(); i < n; ++i) {
				int[] node = jihon.pollFirst();
				int x = node[0], y = node[1], d = node[2];
				
				if(mat[y][x] == 'F') continue;
				if((x == 0 || x == C - 1) || (y == 0 || y == R - 1)) return d;
				
				for(int di = 0; di < dy.length; ++di) {
					int nx = x + dx[di], ny = y + dy[di];
					if(nx < 0 || C <= nx || ny < 0 || R <= ny) continue;
					if(mat[ny][nx] != '.') continue;
					
					jihon.offerLast(new int[] {nx, ny, d+1});
					mat[ny][nx] = 'J';
				}
			}
			
			for(int i = 0, n = fire.size(); i < n; ++i) {
				int[] node = fire.pollFirst();
				int x = node[0], y = node[1];
				
				for(int di = 0; di < dy.length; ++di) {
					int nx = x + dx[di], ny = y + dy[di];
					if(nx < 0 || C <= nx || ny < 0 || R <= ny) continue;
					if(mat[ny][nx] == '#' || mat[ny][nx] == 'F') continue;
					
					fire.offerLast(new int[] {nx, ny});
					mat[ny][nx] = 'F';
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		R = readInt();
		C = readInt();
		mat = new char[R][];
		for(int i = 0; i < R; ++i) {
			mat[i] = br.readLine().toCharArray();
			for(int j = 0; j < C; ++j) {
				if(mat[i][j] == 'J') jihon.offer(new int[] {j, i, 1});
				else if(mat[i][j] == 'F') fire.offer(new int[] {j, i});
			}
		}
		
		int ans = solution();
		System.out.print(ans <= 0 ? "IMPOSSIBLE" : ans);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}