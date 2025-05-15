import java.io.*;
import java.util.*;
/**
 * 메모리: 14172KB
 * 시간: 116ms
 */
public class Baekjoon10159_저울 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N, M;
	static int[][] adj;
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		
		adj = new int[N][N];
		for(int i = 0; i < N; ++i) Arrays.fill(adj[i], Integer.MAX_VALUE);
		for(int i = 0; i < N; ++i) adj[i][i] = 0;
		
		M = readInt();
		for(int i = 0; i < M; ++i) {
			int u = readInt() - 1, v = readInt() - 1;
			adj[u][v] = 1;
		}
		
		for(int k = 0; k < N; ++k) {
			for(int i = 0; i < N; ++i) {
				for(int j = 0; j < N; ++j) {
					if(adj[i][k] == Integer.MAX_VALUE || adj[k][j] == Integer.MAX_VALUE) continue;
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
			}
		}
		for(int i = 0; i < N; ++i) {
			int cnt = 0;
			for(int j = 0; j < N; ++j) {
				if(adj[i][j] == Integer.MAX_VALUE && adj[j][i] == Integer.MAX_VALUE) ++cnt;
			}
			sb.append(cnt).append("\n");
		}
		System.out.print(sb);
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == 13) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n * s;
	}
}