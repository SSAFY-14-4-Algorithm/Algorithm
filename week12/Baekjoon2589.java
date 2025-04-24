import java.io.*;
import java.util.*;

/*
 * 메모리: 161,604KB
 * 시간: 396ms
 */
public class Baekjoon2589 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Node{
		int x, y, d; // 좌표, 거리
		Node(int x, int y, int d) { this.x=x; this.y=y; this.d=d; }
	}
	
	// constants
	static final char LAND = 'L', WATER = 'W';
	static int[][] DELTA = {
			{+1,  0},
			{-1,  0},
			{ 0, +1},
			{ 0, -1},
	};
	// variables
	static int H, W;
	static char[][] mat;
	static int[][] minDist;
	
	
	static boolean inRange(int y, int x) { return 0 <= y && y < H && 0 <= x && x < W; }
	
	static Node longestPath(int y, int x) {
		for(int i = 0; i < H; ++i) Arrays.fill(minDist[i], -1);
		Deque<Node> q = new ArrayDeque<Node>();
		minDist[y][x] = 0;
		q.offerLast(new Node(x, y, 0));

		Node last = null; // 가장 마지막에 방문한 정점
		while(!q.isEmpty()) {
			last = q.pollFirst();
			
			for(int di = 0; di < DELTA.length; ++di) {
				int nx = last.x + DELTA[di][0];
				int ny = last.y + DELTA[di][1];
				if(!inRange(ny, nx) || mat[ny][nx] == WATER || minDist[ny][nx] >= 0) continue;
				
				minDist[ny][nx] = last.d + 1;
				q.offerLast(new Node(nx, ny, minDist[ny][nx]));
			}
		}
		return last;
	}
	
	static int solution() {
		int max = 0;
		for(int y = 0; y < H; ++y) {
			for(int x = 0; x < W; ++x) {
				if(mat[y][x] == WATER) continue;
				Node result = longestPath(y, x);
				max = Math.max(max, result.d);
			}
		}
		return max;
	}
	
	
	/*
	 * 각 섬마다 임의의 정점에서 가장 먼 정점을 하나 찾는다.
	 * 그 정점에서 가장 먼 정점을 찾는다.
	 * 두 정점사이의 거리가, 각 섬에서 최장 거리이다.
	 */
	public static void main(String[] args) throws IOException {
		// Input
		H = readInt(); W = readInt();
		mat = new char[H][];
		minDist = new int[H][W];
		
		for(int y = 0; y < H; ++y) mat[y] = br.readLine().toCharArray();
		// Output
		System.out.println(solution());
		
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F); 
		if(c == '\r') System.in.read();
		return n;
	}
}