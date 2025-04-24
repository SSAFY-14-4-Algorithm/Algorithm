import java.io.*;
import java.util.*;

/*
 * 메모리: 14,896KB
 * 시간: 116ms
 */
public class Baekjoon1520 {
	static StringBuilder sb = new StringBuilder(); 
	// types
	static class Node{
		int y, x, d;
		public Node(int y, int x, int d) { this.y = y; this.x = x; this.d = d; }
	}
	// constants
	static int SIZE = 500;
	static int[][] DELTA = {
			{ 1,  0},
			{-1,  0},
			{ 0,  1},
			{ 0, -1},
	};
	// variables
	static int M, N;
	static int[][] mat = new int[SIZE][SIZE];
	static int[][] dp = new int[SIZE][SIZE];
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < M && 0 <= x && x < N;
	}
	
	static int dfs(int y, int x) {
		if(dp[y][x] >= 0) return dp[y][x]; // 이미 도착점까지 도달하는 경로가 계산됨
		if(y == M - 1 && x == N - 1) return dp[y][x] = 1; // 기저: 도착점 도달
		
		dp[y][x] = 0;
		int cnt = 0;
		for(int d = 0; d < DELTA.length; ++d) {
			int nx = x + DELTA[d][0];
			int ny = y + DELTA[d][1];
			
			if(inRange(ny, nx) && mat[y][x] > mat[ny][nx]) {
				cnt += dfs(ny, nx);
			}
		}
		return dp[y][x] = cnt;
	}
	
	static int solution() {
		if(M == 1 && N == 1) return 1;
		return dfs(0, 0);
	}
	
	public static void main(String[] args) throws IOException {
		M = readInt(); N = readInt();
		for(int y = 0; y < M; ++y) {
			for(int x = 0; x < N; ++x) {
				mat[y][x] = readInt();
				dp[y][x] = -1;
			}
		}
		System.out.print(solution());
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}