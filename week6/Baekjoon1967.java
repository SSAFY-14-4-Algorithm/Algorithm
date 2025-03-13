package AlgorithmStudy.week6;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 20,856 mb
 * 실행 시간 : 152 ms
 */

public class Baekjoon1967 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static ArrayList<Node>[] graph;
	static boolean[] visited;
	static int N, max, idxMax;
	
	private static class Node{
		int num;
		int weight;
		
		Node(int num, int weight){
			this.num = num;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N+1];
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<Node>();
		}
		
		for(int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph[parent].add(new Node(child, weight));
			graph[child].add(new Node(parent, weight));
		}
		
		max = Integer.MIN_VALUE;
		idxMax = -1;
		
		visited = new boolean[N+1];
		visited[1] = true;
		dfs(1, 0);
		
		visited = new boolean[N+1];
		visited[idxMax] = true;
		dfs(idxMax, 0);
		
		System.out.println(max);
		
	}
	private static void dfs(int num, int cnt) {
		
		if(cnt > max) {
			max = cnt;
			idxMax = num;
		}
		
		for(Node g : graph[num]) {
			if(visited[g.num]) continue;
			visited[g.num] = true;
			dfs(g.num, cnt+g.weight);
		}
		
	}

}
