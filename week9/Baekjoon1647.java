import java.io.*;
import java.util.*;
/*
 * 메모리: 319672KB	
 * 시간 : 1636ms	
 */
public class Baekjoon1647 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;
	static class Edge{
		int x;
		int y;
		int cost;
		
		public Edge(int x,int y,int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	//Union-Find
	public static void union(int x, int y) {
		int a = find(x);
		int b = find(y);
		
		if(a>b)
			parent[a] = b;
		else
			parent[b] = a;
		
	}
	public static int find(int x) {
		if(parent[x]!= x)
			return parent[x] = find(parent[x]);
		return parent[x];
	}
	
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> (a.cost-b.cost));
		
		parent = new int[n+1];
		for(int i=1;i<=n;i++) {
			parent[i] = i;
		}
		for(int i=0;i<m;i++) {
			String[] s2 = br.readLine().split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			pq.add(new Edge(a,b,c));
		}
		int sum = 0;
		int maxCost = 0;
		//MST 마지막 값을 maxCost에 저장해 전체 비용에서 빼준다
		while(!pq.isEmpty()) {
			Edge tmp = pq.poll();
			if(find(tmp.x)!= find(tmp.y)) {
				union(tmp.x, tmp.y);
				sum+=tmp.cost;
				maxCost = tmp.cost;
			}
		}
		System.out.println(sum-maxCost);
	}
}
