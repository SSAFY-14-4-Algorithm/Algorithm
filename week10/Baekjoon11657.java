import java.io.*;
import java.util.*;
/*
 * 메모리 17696KB
 * 시간 212ms
 */
public class Baekjoon11657 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Edge[] edge;
	static long[] dist;
	static class Edge{
		int v;
		int w;
		int cost;
		
		public Edge(int v,int w,int cost) {
			this.v = v;
			this.w = w;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException {
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		edge = new Edge[m];
		dist = new long[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		for(int i=0;i<m;i++) {
			String[] s2 = br.readLine().split(" ");
			int v = Integer.parseInt(s2[0]);
			int w = Integer.parseInt(s2[1]);
			int cost = Integer.parseInt(s2[2]);
			edge[i] = new Edge(v,w,cost);
		}
		dist[1] = 0;
		
		boolean notMinus = BellManFord(n,m,1);
		//음수사이클이 없다면 출력
		if(notMinus) {
			for(int i=2;i<=n;i++) {
				System.out.println(dist[i]==Integer.MAX_VALUE?-1:dist[i]);
			}
		}
	}
	//벨만-포드 알고리즘
	public static boolean BellManFord(int n, int m, int start) {
		//정점개수만큼 반복
		for(int i=0;i<n;i++) {
			//각 정점마다 모든 간선 경우 탐색
			for(int j=0;j<m;j++) {
				int v = edge[j].v;
				int w = edge[j].w;
				int cost = edge[j].cost;
				//무한경로가 아니고 dist[w]는 1,2중 최소값
				//1.dist[w] : 지금까지 계산한 w에 도착하는 최단거리
				//2.dist[v] + cost(v, w) : v에 도착하는 최단거리 + v에서 w가는 간선의 가중치
				if(dist[v]!=Integer.MAX_VALUE && dist[w] > dist[v]+cost) {
					dist[w] = dist[v]+cost;
				}
			}
		}
		//n번 반복했는데 더 작은값이 또 생기면 음수사이클 존재
		for(int i=0;i<m;i++) {
			int v = edge[i].v;
			int w = edge[i].w;
			int cost = edge[i].cost;
			if(dist[v]!=Integer.MAX_VALUE && dist[w] > dist[v]+cost) {
				System.out.println(-1);
				return false;
			}
		}
		
		return true;
	}
}
