import java.io.*;
import java.util.*;

/**
 * 문자열 잘라내기, 2866
 * 메모리: 151,860KB
 * 시간: 320ms
 */
public class Baekjoon2866 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE = 1_000;
	static final int RANGE = 26;
	static final int ROOT = 0;
	static final int NPOS = 0;
	// variables
	static int R, C;
	static char[][] mat;
	
	static int size = 0;
	static int[] values = new int[SIZE * SIZE + 1];
	static int[][] nexts = new int[SIZE * SIZE + 1][RANGE];
	
	
	static int solution() {
		int cnt = R - 1;
		
		Deque<Integer> q = new ArrayDeque<Integer>();
		q.offerLast(ROOT);
		while(!q.isEmpty()) {
			int width = 0;
			for(int i = 0, size = q.size(); i < size; ++i) {
				int u = q.pollFirst();
				
				for(int c = 0; c < RANGE; ++c) {
					if(nexts[u][c] == 0) continue;
					q.offerLast(nexts[u][c]);
					++width;
				}
			}
			if(width == C) break; // 레벨에서 방문하는 노드 수가 열의 수와 같으면 중복이 없음
			--cnt;
		}
		return cnt;
	}
	
	public static void main(String[] args) throws IOException {
		R = readInt(); C = readInt();
		mat = new char[R][];
		for(int r = 0; r < R; ++r) mat[r] = br.readLine().toCharArray();
		
		// 역방향 트라이
		for(int c = 0; c < C; ++c) {
			int cur = ROOT;
			for(int r = R - 1; r >= 0; --r) { 
				int ch = mat[r][c] - 'a';
				
				if(nexts[cur][ch] == NPOS) {
					values[++size] = ch + 'a';
					nexts[cur][ch] = size;
				}
				cur = nexts[cur][ch];
			}
		}
		
		System.out.print(solution());
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r') System.in.read();
		return n;
	}
}
