package week2;

import java.io.*;
import java.util.*;

public class Baekjoon2606 {
	static int n,m,v = 0;
	static ArrayList<ArrayList<Integer>> graph;
	static Deque<Integer> deque;
	static boolean[] visited; 
	static int result = 0;
	
	public static void DFS(int v) {
		if(visited[v]) return;
		
		visited[v] = true;
		result++;
		
		for(Integer num : graph.get(v)) {
			DFS(num);
		}
	}
	
	public static void BFS(int v) {
		visited[v] = true;
		deque.offerLast(v);
		
		while(!deque.isEmpty()) {
			int node = deque.pollFirst();
			for(Integer num : graph.get(node)) {
				if(!visited[num]) {
					visited[num] = true;
					deque.offerLast(num);
					result++;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<ArrayList<Integer>>();
		deque = new ArrayDeque<Integer>();
		visited = new boolean [n+1];
		
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
//		DFS(1); 
		BFS(1);
		
//		bw.write(result-1+""); // DFS
		bw.write(result+""); //BFS
		bw.flush();
		bw.close();
		
		
	}

}