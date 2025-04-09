import java.util.*;
import java.io.*;

public class Baekjoon1238 {
	static class Edge{
		int nxt, w;
		public Edge(int nxt, int w) {
			this.nxt = nxt;
			this.w = w;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		ArrayList<Edge>[] arr1 = new ArrayList[N+1];
		ArrayList<Edge>[] arr2 = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			arr1[i] = new ArrayList<>();
			arr2[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr1[s].add(new Edge(e, w));
			arr2[e].add(new Edge(s, w));
		}
		int[] dist1 = new int[N+1];
		int[] dist2 = new int[N+1];
		int INF = (int)1e9;
		Arrays.fill(dist1, INF);
		Arrays.fill(dist2, INF);
		dist1[X] = 0;
		dist2[X] = 0;
		PriorityQueue<Edge> pq1 = new PriorityQueue<>((o1, o2)->(o1.w-o2.w));
		PriorityQueue<Edge> pq2 = new PriorityQueue<>((o1, o2)->(o1.w-o2.w));
		pq1.add(new Edge(X, 0));
		pq2.add(new Edge(X, 0));
		while(!pq1.isEmpty()) {
			Edge cur = pq1.poll();
			if(dist1[cur.nxt] < cur.w) continue;
			for(Edge e : arr1[cur.nxt]) {
				int cost = e.w + cur.w;
				if(dist1[e.nxt] > cost) {
					dist1[e.nxt] = cost;
					pq1.add(new Edge(e.nxt, cost));
				}
			}
		}
		
		while(!pq2.isEmpty()) {
			Edge cur = pq2.poll();
			if(dist2[cur.nxt] < cur.w) continue;
			for(Edge e : arr2[cur.nxt]) {
				int cost = e.w + cur.w;
				if(dist2[e.nxt] > cost) {
					dist2[e.nxt] = cost;
					pq2.add(new Edge(e.nxt, cost));
				}
			}
		}
		
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			answer = Math.max(answer, dist1[i] + dist2[i]);
		}
		System.out.println(answer);
	}
}
