import java.io.*;
import java.util.*;
/*
 * 메모리:239332KB	
 * 시간 : 1136ms
 */
public class Baekjoon13418 {
	public static class Edge{
		int x;
		int y;
		int	cost;
		
		public Edge(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] parent;
	
	//Union-Find 
	public static void union(int x,int y) {
		int a = find(x);
		int b = find(y);
		
		if(a>b)
			parent[a] = b;
		else
			parent[b] = a;
	}
	public static int find(int x) {
		if(parent[x]!=x)
			return parent[x] = find(parent[x]);
		return parent[x];
	}
	
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		
		PriorityQueue<Edge> pq1 = new PriorityQueue<>((a,b) -> a.cost-b.cost);//오르막길 우선
		PriorityQueue<Edge> pq2 = new PriorityQueue<>((a,b) -> b.cost-a.cost);//내리막길 우선
		int count1 = 0;
		int count2 = 0;
		
		
		for(int i=0;i<=m;i++) {
			String[] s2 = br.readLine().split(" ");
			int a = Integer.parseInt(s2[0]);
			int b = Integer.parseInt(s2[1]);
			int c = Integer.parseInt(s2[2]);
			pq1.add(new Edge(a,b,c));
			pq2.add(new Edge(a,b,c));
		}
		
		// 오르막길 최대로 뽑는 경우
		parent = new int[n+1];
		for(int i=0;i<=n;i++) {
			parent[i] = i;
		}
		//MST 생성
		while(!pq1.isEmpty()) {
			Edge tmp = pq1.poll();
			if(find(tmp.x)!=find(tmp.y)) {
				union(tmp.x,tmp.y);
				if(tmp.cost==0) {
					count1++;				
				}
			}
		}	
		
		//내리막길 최대로 뽑는 경우
		parent = new int[n+1];
		for(int i=0;i<=n;i++) {
			parent[i] = i;
		}
		//MST 생성
		while(!pq2.isEmpty()) {
			Edge tmp = pq2.poll();
			if(find(tmp.x)!=find(tmp.y)) {
				union(tmp.x,tmp.y);
				if(tmp.cost==0) {
					count2++;		
				}
			}
		}
		System.out.println((int)(Math.pow(count1, 2)-Math.pow(count2, 2)));
	}
}
