import java.util.*;
import java.io.*;

/* 벨만포드
 * 19196kb, 240ms
 */

public class Baekjoon11657 {
	static class Edge {
		int s, e, w;
		public Edge(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Edge[] edges = new Edge[M];
		long[] dist = new long[N+1];
		int INF = (int)1e9;
		Arrays.fill(dist, INF);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edges[i] = new Edge(s, e, w);
		}
		boolean mCycle = false;
		dist[1] = 0;
		A:for(int i = 1; i <= N; i++) {
			for(Edge edge : edges) {
				int s = edge.s;
				int e = edge.e;
				int w = edge.w;
				if(dist[s] != INF && dist[e] > dist[s] + w) {
					if(i == N) {
						mCycle = true;
						break A;
					}
					dist[e] = dist[s] + w;
				}
			}
		}
		if(mCycle) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();
			for(int i = 2; i < N+1; i++) {
				if(dist[i] == INF) {
					sb.append(-1).append("\n");
				} else {
					sb.append(dist[i]).append("\n");
				}
			}
			System.out.print(sb);
		}
	}
}
