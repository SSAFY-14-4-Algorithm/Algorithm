import java.io.*;
import java.util.*;


/**
 * 시간: 380ms
 * 메모리: 71,212KB
 */
public class Baekjoon_2573_빙산 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static final int[] dy = {0,0,1,-1};
	static final int[] dx = {1,-1,0,0};
	
	static int N, M;
	static int[][] mat;
	static int[][] melts;
	
	
	static boolean inRange(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static void floodFill(int ay, int ax, boolean[][] isVisisted) {
		Deque<int[]> q = new ArrayDeque<>();
		
		q.offerLast(new int[] {ax, ay});
		isVisisted[ay][ax] = true;
		while(!q.isEmpty()) {
			int[] node = q.pollFirst();
			int y = node[1];
			int x = node[0];
			
			for(int d = 0; d < dy.length; ++d) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(inRange(ny, nx) && !isVisisted[ny][nx] && mat[y][x] > 0) {
					q.offerLast(new int[] {nx, ny});
					isVisisted[ny][nx] = true;
				}
			}
		}
	}
	
	static int getCount() {
		boolean[][] isVisisted = new boolean[N][M];
		
		int cnt = 0;
		for(int y = 0; y < N; ++y) {
    		for(int x = 0; x < M; ++x) {
    			if(isVisisted[y][x] || mat[y][x] <= 0) continue;
    			floodFill(y, x, isVisisted);
    			++cnt;
    		}
    	}
		return cnt;
	}
	
	static void update() {
		Deque<int[]> q = new ArrayDeque<>();
		
		for(int y = 0; y < N; ++y) {
    		for(int x = 0; x < M; ++x) {
    			if(mat[y][x] <= 0) continue;
    			
    			if((mat[y][x] -= melts[y][x]) <= 0) {
    				q.offerLast(new int[] {x, y});
    			}
    		}
		}
		
		while(!q.isEmpty()) {
			int[] node = q.pollFirst();
			int y = node[1];
			int x = node[0];
			
			for(int d = 0; d < dy.length; ++d) {
				int ny = y + dy[d];
				int nx = x + dx[d];
				if(inRange(ny, nx)) {
					++melts[ny][nx]; 
				}
			}
		}
	}
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	M = readInt();
    	mat = new int[N][M];
    	melts = new int[N][M];
    	
    	for(int y = 0; y < N; ++y) {
    		for(int x = 0; x < M; ++x) {
    			mat[y][x] = readInt();
    		}
    	}
    	for(int y = 0; y < N; ++y) {
    		for(int x = 0; x < M; ++x) {
    			for(int d = 0; d < dy.length; ++d) {
    				int ny = y + dy[d];
    				int nx = x + dx[d];
    				if(inRange(ny, nx) && mat[ny][nx] == 0) {
    					++melts[y][x]; 
    				}
    			}
    		}
    	}
    	
    	int t = 0;
    	while(true) {
    		int cnt = getCount();
    		if(cnt == 1) {
    			update();
    			t++;
    			continue;
    		}
    		if(cnt == 0) System.out.print(0);
    		else System.out.print(t);    			
    		return;
    	}
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}