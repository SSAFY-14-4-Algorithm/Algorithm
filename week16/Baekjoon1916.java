
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

class Node implements Comparable<Node> {
	int to;
	int cost;

	public Node(int to, int cost) {
		this.to = to;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
}

//메모리 51140kb 시간 476ms
//다익스트라
public class Baekjoon1916 {
	static List<Node>[] graph;
	static int[] dist; //최단 거리 계산

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		StringTokenizer st;

		graph = new ArrayList[n+1];
		dist = new int[n+1];

		for (int i = 1; i <= n; i++) {
			graph[i] = new ArrayList<>();
			dist[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[from].add(new Node(to, cost));
		}

		st = new StringTokenizer(br.readLine());

		int startCity = Integer.parseInt(st.nextToken());
		int endCity = Integer.parseInt(st.nextToken());

		dijkstra(startCity);
		System.out.println(dist[endCity]);

	}

	static void dijkstra(int start) {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));
		dist[start] = 0;

		while(!q.isEmpty()) {
			Node now = q.poll();

			// 이미 더 짧은 거리로 방문된 노드는 무시
			if (dist[now.to] < now.cost) continue;

			for (Node next : graph[now.to]) {
				if (dist[next.to] > now.cost + next.cost) {
					dist[next.to] = now.cost + next.cost;
					q.add(new Node(next.to, dist[next.to]));
				}
			}

		}
	}

}
