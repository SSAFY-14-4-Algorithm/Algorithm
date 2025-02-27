package week2;

import java.io.*;
import java.util.*;

public class Baekjoon2644 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	
	static ArrayList<ArrayList<Integer>> graph;
	static Deque<Node> deque;
	static boolean[] visited;
	static int n,m,startNode,endNode,depth = 0;
	
	private static class Node {
		int number;
		int depth;
		
		Node(int n, int d){
			this.number = n;
			this.depth = d;
		}
	};
	
	public static int DFS(int v, int nodeCount) {
		if(endNode == v) return nodeCount++;
		
		visited[v] = true;
		nodeCount++;
		
		for(Integer num : graph.get(v)) {
			if(!visited[num]) {
				
				int result = DFS(num, nodeCount++);
				
				if(result != -1) return result;
			}
		}

		return -1;
	}
	
	public static int BFS(int v, int nodeCount) {
		
		deque = new ArrayDeque<>();
		deque.offerLast(new Node(v, nodeCount));
		visited[v] = true;
		
		while(!deque.isEmpty()) {
			
			Node node = deque.pollFirst();
			
			if(node.number == endNode) return node.depth;
			
			for(Integer num : graph.get(node.number)) {
				if(!visited[num]) {
					Node tmp = new Node(num, node.depth+1);
					visited[num] = true;
					deque.offerLast(tmp);
				}
			}	
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		startNode = Integer.parseInt(st.nextToken());
		endNode = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());
		
		graph = new ArrayList<ArrayList<Integer>>();
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
		
		
//		bw.write(DFS(startNode,0)+"");
		bw.write(BFS(startNode,0)+"");
		bw.flush();
		bw.close();
		br.close();
	}

}
