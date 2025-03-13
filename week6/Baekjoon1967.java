import java.io.*;
import java.util.*;
/*
 * 메모리: 22640KB
 * 시간: 192ms
 */
public class Baekjoon1967{
	static int n;
	static List<Node>[] tree;
	static boolean[] visited;
	static int MDist = 0;
	static int LNode =0;
	
	static class Node{
		int to;
		int weight;
		Node(int to, int weight){
			this.to = to;
			this.weight = weight;
		}
		
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		if(n==1) {
			System.out.println(0);
			return;
		}
		
		tree = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i=0;i<n-1;i++) { 
			StringTokenizer st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			tree[parent].add(new Node(child,weight));
			tree[child].add(new Node(parent,weight));
		}
		
		visited = new boolean[n+1];
		dfs(1,0);
		
		visited = new boolean[n+1];
		MDist = 0;
		dfs(LNode,0);
		
		System.out.println(MDist);
		
	}
	
	static void dfs(int node, int dist) {
		if(dist>MDist) {
			MDist = dist;
			LNode = node;
		}
		visited[node] = true;
		for(Node next : tree[node]) {
			if(!visited[next.to]) {
				dfs(next.to,dist+next.weight);
			}
		}
	}

}
	
