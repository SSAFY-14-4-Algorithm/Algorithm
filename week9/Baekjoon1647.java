/*
 * BOJ 1647번 : 도시 분할 계획
 * 메모리 : 351,992kb
 * 시간 : 1,792ms
 *
 * MST에서 가장 가중치가 높은 간선을 지워주면,
 * 2개의 마을을 최소로 나눌 수 있다.
 */

import java.io.*;
import java.util.*;

public class BOJ1647_도시분할계획 {
	static int N, M;
	static List<Node>[] list;
	static boolean[] visited;
	static int minSum;
	static class Node {
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		visited = new boolean[N+1];
		for(int n = 1; n <= N; n++) {
			list[n] = new ArrayList<Node>();
		}
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, cost));
			list[to].add(new Node(from, cost));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost-o2.cost);
		pq.add(new Node(1,0));

		minSum = 0;
		int maxCost = 0;
		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(!visited[curr.to]) {
				visited[curr.to] = true;
				minSum += curr.cost;
				maxCost = Math.max(maxCost, curr.cost);

				for(Node next : list[curr.to]) {
					if(!visited[next.to]) {
						pq.add(new Node(next.to, next.cost));
					}
				}
			}
		}

		sb.append(minSum-maxCost);
		System.out.print(sb);
		br.close();
	}
}