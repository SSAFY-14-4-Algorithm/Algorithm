package week2;

import java.io.*;
import java.util.*;

public class Baekjoon1260 {
private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static ArrayList<ArrayList<Integer>> graph;
	private static ArrayList<Integer> dist;
	private static int N,M,V;
	public static void main(String [] args) throws IOException{
		String [] tokens = br.readLine().split(" ");
		graph = new ArrayList<>();
		dist = new ArrayList<>();
		N = Integer.parseInt(tokens[0]);
		M = Integer.parseInt(tokens[1]);
		V = Integer.parseInt(tokens[2]);
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			tokens = br.readLine().split(" ");
			int a = Integer.parseInt(tokens[0]);
			int b = Integer.parseInt(tokens[1]);
			
			graph.get(a).add(b);
			graph.get(b).add(a);

		}
		
		for(int i=0;i<graph.size();i++) {
			Collections.sort(graph.get(i));
		}
		
		dfs(V, new boolean[N+1]);
		for(int i : dist) {
			bw.write(i + " ");
		}bw.write("\n");
		
		dist.clear();
		bfs(V,new boolean[N+1]);
		for(int i : dist) {
			bw.write(i + " ");
		}bw.write("\n");
		bw.flush();
		bw.close();
		br.close();
	}
	private static void dfs(int node, boolean [] visited) {
		if(visited[node]) return;
		
		visited[node] = true;
		dist.add(node);
		for(int next : graph.get(node)) {
			dfs(next,visited);
		}
	}
	
	private static void bfs(int start, boolean [] visited) {
		Queue<Integer> queue = new LinkedList<>();
		visited[start] = true;
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();
			dist.add(curNode);
			for(int nextNode : graph.get(curNode)) {
				if(visited[nextNode]) continue;
				
				visited[nextNode] = true;
				queue.add(nextNode);
			}
		}
	}
}
