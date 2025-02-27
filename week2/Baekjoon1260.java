package week2;

import java.io.*;
import java.util.*;

public class Baekjoon1260 {
	
	static int n,m,v = 0;
	static ArrayList<ArrayList<Integer>> graph;
	static Deque<Integer> deque;
	static ArrayList<Integer> result;
	static boolean[] visited; 
	
	public static void DFS(int v) {
		if(visited[v]) return;
	
		visited[v] = true;
		result.add(v);
		for(Integer num : graph.get(v)) {
			DFS(num);
		}
		
	}
	
	public static void BFS(int v) {

		visited[v] = true;
		deque.offerLast(v);
		
		while (!deque.isEmpty()){
			int node = deque.pollFirst();
			result.add(node);
			visited[node] = true;

			for(int num : graph.get(node)) {
				if(!visited[num]) {
					deque.offerLast(num);
					visited[num] = true;
				}
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		// DFS 초기화
		graph = new ArrayList<ArrayList<Integer>>();
		visited = new boolean[n+1];
		result = new ArrayList<Integer>(n+1);
		
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
		
		for(int i=1;i<=n;i++) {
			graph.get(i).sort(null);
		}
		
		// DFS
		DFS(v);
		for(Integer num : result) {
			bw.write(num+" ");
		}
		bw.write("\n");
		
		// BFS 초기화
		deque = new ArrayDeque<>(n+1);
		visited = new boolean[n+1];
		result = new ArrayList<Integer>(n+1);
		
		// BFS
		BFS(v);
		for(Integer num : result) {
			bw.write(num+" ");
		}
		
		bw.flush();
		bw.close();
	}

}
