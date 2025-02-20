package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon2252 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st; 
	
	static List<Integer>[] graph;
	static Deque<Integer> deque = new ArrayDeque<>(); 
	static int[] indegree;
	
	static int n, m=0;
	
	public static void topologicalSort() throws IOException {
		for(int i=1;i<indegree.length;i++) {
			if (indegree[i] == 0) deque.offerLast(i);
		}
		
		while(!deque.isEmpty()) {
			
			int current = deque.pollFirst();
			bw.write(current + " ");
			
			for(int num : graph[current]) {
				indegree[num]--;
				
				if (indegree[num] == 0) deque.offerLast(num);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		indegree = new int[n+1];
		graph = new ArrayList[n+1];
		for(int i=0; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}

		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			indegree[v]++;
		}
		
		topologicalSort();
		
		bw.flush();
		bw.close();
		br.close();
		
	}

}