import java.io.*;
import java.util.*;
/*
 * 메모리 61824KB
 * 시간 628ms
 */
public class Baekjoon1238 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] go;
	static int[] back;
	static int[] dist;
	static boolean[] visited;
	static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
	static class Node{
		int v;
		int cost;
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int x = Integer.parseInt(s[2]);
		
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<m;i++) {
			String[] s2 = br.readLine().split(" ");
			graph.get(Integer.parseInt(s2[0])).add(new Node(Integer.parseInt(s2[1]),Integer.parseInt(s2[2])));
		}
		
		go = new int[n+1];
		//집에서 X 가는 경우 최단경로 체크
		for(int i=1;i<=n;i++) {
			dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			dist[i] = 0;
			visited = new boolean[n+1];
			dijkstra(i);
			//i에서 X까지 가는 거리 go[i]에 저장
			go[i] = dist[x];
		}
		
		//X에서 각 학생집으로 오는 경우 최단경로 체크
		dist = new int[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[x] = 0;
		visited = new boolean[n+1];
		dijkstra(x);
		
		//X에서 i로 돌아오는 거리 back[i]에 저장
		int[] back = new int[n+1];		
		for(int i=1;i<=n;i++) {
			back[i] = dist[i];
		}
		
		int result=0;
		for(int i=1;i<=n;i++) {
			result = Math.max(result, go[i]+back[i]);
		}
		System.out.println(result);
	}
	
	public static void dijkstra(int start) {
		PriorityQueue<Node> pq = new PriorityQueue<>((a,b)->a.cost-b.cost);
		pq.offer(new Node(start,0));
		
		while(!pq.isEmpty()) {
			Node tmp = pq.poll();
			
			if(visited[tmp.v]) continue;
			visited[tmp.v] = true;
			
			for(Node node : graph.get(tmp.v)) {
				if(!visited[node.v] && dist[node.v] > dist[tmp.v]+ node.cost) {
					dist[node.v] = dist[tmp.v] + node.cost;
					pq.offer(new Node(node.v,dist[node.v]));
				}
			}
		}
	}
}
