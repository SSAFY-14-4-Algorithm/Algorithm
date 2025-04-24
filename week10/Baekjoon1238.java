import java.io.*;
import java.util.*;

/*
 * 메모리: 17,288KB
 * 시간: 148ms
 */
public class Baekjoon1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static class Node implements Comparable<Node> {
		int v, w;
		
		Node() {}
		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
		@Override
		public int compareTo(Node other) {
			return this.w - other.w;
		}
	}
	
	static final int MAX_N = 1000;
	static final int INF = 10_000 * MAX_N;
	
	static int N, M, X;
	static List<Node>[] adj = new List[MAX_N + 1];
	static List<Node>[] iAdj = new List[MAX_N + 1];
	static int[] minDist = new int[MAX_N + 1];
	static int[] iMinDist = new int[MAX_N + 1];
	
	static void dijkstra(int s, List<Node>[] adj, int[] minDist) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.offer(new Node(s, 0));
		minDist[s] = 0;
		
		while(!q.isEmpty()) {
			Node node = q.poll();
			int u = node.v, w = node.w;
			
			for(Node nextNode : adj[u]) {
				int v = nextNode.v;
				int dist = nextNode.w + w;
				if(dist < minDist[v]) {
					minDist[v] = dist;
					q.offer(new Node(v, dist));
				}
			}
		}
	}
	
	static int solution() {
		dijkstra(X, adj, minDist);
		dijkstra(X, iAdj, iMinDist);
		
		int max = Integer.MIN_VALUE;
		for(int i = 1; i <= N; ++i) {
			max = Math.max(max, minDist[i] + iMinDist[i]);
		}
		return max;
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		
		for(int i = 1; i <= N; ++i) {
			adj[i] = new ArrayList<>();
			iAdj[i] = new ArrayList<>();
		}
		Arrays.fill(minDist, INF);
		Arrays.fill(iMinDist, INF);
		
		for(int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adj[u].add(new Node(v, w));
			iAdj[v].add(new Node(u, w));
		}
		System.out.println(solution());
		
	}
}
