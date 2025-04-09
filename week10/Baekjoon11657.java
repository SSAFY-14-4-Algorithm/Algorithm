
import java.io.*;
import java.util.*;

public class Baekjoon11657 {
	class Edge {
		int from;
		int to;
		int cost;
		
		
		public Edge(int from, int to, int cost) {
			super();
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

	}
	
	static final long INF = Long.MAX_VALUE;
	static int n, m;
	static ArrayList<Edge> edges;
	static long[] dist; // 시작점에서 각 정점까지의 최단 거리를 저장하는 배열
	
	//벨만 포드 알고리즘
	//음수 가중치를 가진 간선이 있을때, 최단거리를 구하는 알고리즘
	//메모리 19532kb 시간 280ms
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	}
	
	static boolean bellmanFord(int start) {
		dist = new long[n+1];
		//dis 초기화
		Arrays.fill(dist, INF);
		dist[start] = 0; //시작점 거리는 0
		
		for (int i = 1; i <= n; i++) {
			for (Edge e : edges) {
				int u = e.from;
				int v = e.to;
				int weight = e.cost;
				
				if (dist[u] != INF && dist[v] > dist[u] + weight) {
					dist[v] = dist[u] + weight;
					
					if (i == n) {
						return true;
					}
				}
			}
		}
		
		return false; // 음수 사이클이 존재하지 않음을 반환
	}

}
