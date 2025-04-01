import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
	int from;
	int to;
	int weight;
	
	
	
	public Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}


	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return this.weight - o.weight;
	}
}
public class Baekjoon1647 {
	static int n, m;
	static int[] parent;
	static ArrayList<Edge> edgeList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		edgeList = new ArrayList<>();
		for (int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edgeList.add(new Edge(a, b, cost));
		}
		
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
		
		Collections.sort(edgeList);
		
		int ans = 0;
		int bigCost = 0;
		for (int i = 0; i < edgeList.size(); i++) {
			Edge edge = edgeList.get(i);

			// 사이클이 발생하는 간선은 제외.
			if (find(edge.from) != find(edge.to)) {
				ans += edge.weight;
				union(edge.from, edge.to);
				
				bigCost = edge.weight;
			}
		}
		
		System.out.println(ans - bigCost);
				
	}
	
	public static int find(int x) {
		if (x == parent[x]) return x;

		return parent[x] = find(parent[x]);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (x != y) {
			parent[y] = x;
		}
	}
}
