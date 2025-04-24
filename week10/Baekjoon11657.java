import java.io.*;
import java.util.*;

/*
 * 메모리: 12,528KB
 * 시간: 104ms
 */
public class Baekjoon11657 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	static class Edge{
		int s, e, w;
		public Edge(int s, int e, int w) { this.s = s; this.e = e; this.w = w; }
	}
	// constants
	// variables	
	static int N, M;
	static Edge[] edges = new Edge[6_000];
	static long[] distances = new long[501];
	
	
	static boolean bellmanFord(int s) {
		distances[s] = 0;
		for(int i = 0; i < N; ++i) {
			for(int j = 0; j < M; ++j) {
				Edge edge = edges[j];
				if(distances[edge.s] != Integer.MAX_VALUE && distances[edge.e] > distances[edge.s] + edge.w) {
					distances[edge.e] = distances[edge.s] + edge.w;
					if(i == N - 1) return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt(); M = readInt();
		Arrays.fill(distances, 1 , N + 1, Integer.MAX_VALUE);
		for(int i = 0; i < M; ++i) {
			edges[i] = new Edge(readInt(), readInt(), readInt());
		}
		
		if(bellmanFord(1)) System.out.println("-1");
		else {
			for(int i = 2; i <= N; ++i) {
				sb.append(distances[i] == Integer.MAX_VALUE ? -1 : distances[i])
					.append("\n");
			}
			System.out.println(sb);
		}
	}
	
	static int readInt() throws IOException {
		int c, n = System.in.read() & 0x0F, s = 1;
		if(n == ('-' & 0x0F)) {
			s = -1;
			n = 0;
		}
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n*s;
	}
}