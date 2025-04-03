import java.util.*;
import java.io.*;
/*
 * 178600kb, 960ms
 */
class Baekjoon13418
{	
	static class Node{
		int s, e, w;
		public Node(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static int[] parent;
	static int N;
	
	static int find(int x) {
		if(x != parent[x]) return parent[x] = find(parent[x]);
		return x;
	}
	
	static void union(int a, int b) {
		if(a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}
	}
	
	static int kruskal(PriorityQueue<Node> pq) {
		int cnt = 0;
		int cntE = 0;
		while(!pq.isEmpty()) {
			Node edge = pq.poll();
			int s = find(edge.s);
			int e = find(edge.e);
			
			if(s != e) {
				union(s, e);
				cnt += (edge.w == 0 ? 1 : 0);
				cntE++;
			}
			if(cntE == N) {
				break;
			}
		}
		return cnt*cnt;
	}
	
	
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> pq1 = new PriorityQueue<>((o1, o2)->(o1.w-o2.w));
		PriorityQueue<Node> pq2 = new PriorityQueue<>((o1, o2)->(o2.w-o1.w));
		parent = new int[N+1];
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		for(int i = 0; i < M+1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq1.add(new Node(s, e, w));
			pq2.add(new Node(s, e, w));
		}
		int max = kruskal(pq1);
		for(int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		int min = kruskal(pq2);
		System.out.println(max-min);
	}
}