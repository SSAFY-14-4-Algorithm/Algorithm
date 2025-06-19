import java.io.*;
import java.util.*;


/**
 * 시간: 104ms
 * 메모리: 11,752KB
 */
public class Baekjoon_17182_우주탐사선 {
	// IO
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	// variables
	static int N, K;
	static int[][] minDist;
	static int minTime = Integer.MAX_VALUE;
	
	static void floyd() {
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(minDist[i][j] > minDist[i][k] + minDist[k][j]) {
						minDist[i][j] = minDist[i][k] + minDist[k][j];
					}
				}
			}
		}
	}
	
	static void minTravel(int from, int elapsed, int isVisited) {
		if(isVisited == ((0x01 << N) - 1)) {
			minTime = Math.min(minTime, elapsed);
			return;
		}
		for(int to = 0; to < N; ++to) {
			if(((isVisited >> to) & 0x01) != 0) continue;
			if(elapsed + minDist[from][to] >= minTime) continue;
			minTravel(to, elapsed + minDist[from][to], isVisited | (0x01 << to));
		}
	}
	
    public static void main(String[] args) throws IOException {
    	N = readInt();
    	K = readInt();
    	minDist = new int[N][N];
    	for(int i = 0; i < N; ++i) {
    		for(int j = 0; j < N; ++j) {
    			minDist[i][j] = readInt();
    		}
    	}
    	floyd();
    	minTravel(K, 0, 0x01 << K);
    	System.out.print(minTime);
    }
    
    static int readInt() throws IOException {
    	int c, n = 0;
    	while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
    	if(c == '\r') System.in.read();
    	return n;
    }
}