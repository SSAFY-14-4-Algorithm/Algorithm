import java.util.*;
import java.io.*;
/* 개구리점프
 * 74472kb, 752ms
 */
public class Baekjoon17619 {
	static class Node{
		int idx, x1, x2;
		public Node(int idx, int x1, int x2) {
			this.idx = idx;
			this.x1 = x1;
			this.x2 =x2;
		}
	}
	
	static int[] parent;
	
	public static int find(int x) {
		if(parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}
	
	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x > y) {
			parent[y] = x;
		} else {
			parent[x] = y;
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2)-> o1.x1-o2.x1);
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			pq.add(new Node(i+1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Node first = pq.poll();
		int index = first.idx;
		int lastX = first.x2;
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			if(cur.x1 <= lastX) {
				union(index, cur.idx);
				lastX = Math.max(cur.x2, lastX);
			} else {
				index = cur.idx;
				lastX = cur.x2;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			num1 = find(num1);
			num2 = find(num2);
			if(num1 == num2) {
				sb.append(1).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
}
