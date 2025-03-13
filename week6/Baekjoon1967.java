import java.io.*;
import java.util.*;
/*
 * 메모리:31836KB	
 * 	시간: 3164ms
 */
public class Baekjoon1967 {
	static class Node{
		int node;
		int cost;
		
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static boolean[] visited;
	static int diameter = 0;
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		visited = new boolean[n+1];
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<Node>());
		}
		for(int i=0;i<n-1;i++) {
			String[] s = br.readLine().split(" ");
			int first = Integer.parseInt(s[0]);
			int second = Integer.parseInt(s[1]);
			int cost = Integer.parseInt(s[2]);
			graph.get(first).add(new Node(second,cost));
			graph.get(second).add(new Node(first,cost));
		}
		//1번 노드부터 시작해서 트리의 지름 구함
		for(int i=1;i<=n;i++) {
			visited[i] = true;
			dfs(i,0);
			visited[i] = false;
		}
		System.out.println(diameter);
	}
	public static void dfs(int start,int cost) {
		//갈수있는 노드이면 방문하고 cost값 추가
		for(Node node : graph.get(start)) {
			if(!visited[node.node]) {
				visited[node.node] = true;
				dfs(node.node,cost+node.cost);
				visited[node.node] = false;
			}
		}
		diameter = Math.max(diameter, cost);
	}
}
