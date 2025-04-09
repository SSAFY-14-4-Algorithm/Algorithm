
import java.io.*;
import java.util.*;

class Node {
	int end;
	int weight;
	
	public Node(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Baekjoon1238 {
	static int n, m, x;
	static ArrayList<Node>[] arr; //정방향
	static ArrayList<Node>[] reverseArr; //역방향
	static int ans;
	
	//메모리 18404kb 시간 176ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		ans = 0;
		
		arr = new ArrayList[n+1];
		reverseArr = new ArrayList[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<>();
			reverseArr[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			arr[f].add(new Node(t, w));
			reverseArr[t].add(new Node(f, w));
		}
		
		int[] toX = dijkstra(reverseArr, x);
		int[] fromX = dijkstra(arr, x); 
		
		int max = 0;
		for (int i = 1; i <= n ; i++) {
			ans = Math.max(ans, toX[i] + fromX[i]);
		}
		System.out.println(ans);

}

static int[] dijkstra(ArrayList<Node>[] graph, int start) {
	
		int[] dist = new int[n+1]; //최단거리
		boolean[] visit = new boolean[n+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[start] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		pq.add(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			if (visit[now.end]) continue;
			visit[now.end] = true;
			
			for (int i = 0; i < graph[now.end].size(); i++) {
				Node next = graph[now.end].get(i);
				if (!visit[next.end] && now.weight + next.weight < dist[next.end]) {
					dist[next.end] = now.weight + next.weight;
				    pq.add(new Node(next.end, dist[next.end]));
				}
			}
		}
		
		return dist;
	
	}
}
