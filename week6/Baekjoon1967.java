import java.util.*;

import java.io.*;

import java.util.*;

import java.io.*;

/*
 * 23284kb, 192ms
 */
//DFS 1번 돌려서 루트에서 가장 먼 정점을 찾고
//그 정점에서 DFS를 1번 더 돌려서 가장 먼 점을 찾는 것
public class Baekjoon1967 {
	static int answer;
	static int findNode;
	static ArrayList<Node>[] arr;
	static boolean[] visited;
	static public class Node{
		int node;
		int w;
		public Node(int node, int w) {
			this.node = node;
			this.w = w;
		}
	}
	
	public static void dfs(int node, int w) {
		if(w > answer) {
			answer = w;
			findNode = node;
		}
		
		for(Node nxt : arr[node]) {
			if(!visited[nxt.node]) {
				visited[nxt.node] = true;
				dfs(nxt.node, nxt.w + w);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		StringTokenizer st;
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[p].add(new Node(c, w));
			arr[c].add(new Node(p, w));
		}
		answer = 0;
		findNode = 1;
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1, 0);
		answer = 0;
		visited = new boolean[N+1];
		visited[findNode] = true;
		dfs(findNode, 0);
		System.out.println(answer);
	}
}


//DFS 1번 돌린 분할정복을 이용한 풀이
//분할정복을 이용해서 DFS 1번으로 찾는 풀이
//23284kb, 192ms
//
//public class Baekjoon1967 {
//	static int answer;
//	static ArrayList<Node>[] arr;
//	static boolean[] visited;
//	static public class Node{
//		int node;
//		int w;
//		public Node(int node, int w) {
//			this.node = node;
//			this.w = w;
//		}
//	}
//	
//	public static int dfs(int node, int w) {
//		PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2)->(o2-o1));
//		for(Node nxt : arr[node]) {
//			if(!visited[nxt.node]) {
//				visited[nxt.node] = true;
//				q.add(dfs(nxt.node, w + nxt.w) - w);
//			}
//		}
//		switch(q.size()) {
//		case 0:
//			return w;
//		case 1:
//			int num = q.poll();
//			answer = Math.max(num,  answer);
//			return num + w;
//		default:
//			int f = q.poll();
//			int s = q.poll();
//			answer = Math.max(answer, f + s);
//			return f + w;
//		}
//	}
//	
//	public static void main(String[] args) throws IOException{
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		int N = Integer.parseInt(br.readLine());
//		arr = new ArrayList[N+1];
//		for(int i = 1; i <= N; i++) {
//			arr[i] = new ArrayList<>();
//		}
//		StringTokenizer st;
//		for(int i = 0; i < N-1; i++) {
//			st = new StringTokenizer(br.readLine());
//			int p = Integer.parseInt(st.nextToken());
//			int c = Integer.parseInt(st.nextToken());
//			int w = Integer.parseInt(st.nextToken());
//			arr[p].add(new Node(c, w));
//		}
//		answer = 0;
//		visited = new boolean[N+1];
//		visited[1] = true;
//		dfs(1, 0);
//		System.out.println(answer);
//	}
//}
