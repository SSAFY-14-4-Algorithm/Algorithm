/*
 * BOJ 13418번 : 학교 탐방 하기
 * 메모리 : 224,996kb
 * 시간 : 1,056ms
 *
 * 오르막길 내리막길 헷갈리지 말자!
 */


import java.io.*;
import java.util.*;

public class Baekjoon13418 {
	static int N, M;
	static List<Node>[] list;
	static boolean[] visited;
	static int maxRoad, minRoad;
	static class Node {
		int to, road;
		public Node (int to, int road) {
			this.to = to;
			this.road = road;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N+1];
		for(int n = 0; n <= N; n++) {
			list[n] = new ArrayList<Node>();
		}
		for(int m = 0; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int road = Integer.parseInt(st.nextToken());
			list[from].add(new Node(to, road));
			list[to].add(new Node(from, road));
		}

		// 오르막길(0)
		PriorityQueue<Node> minPQ = new PriorityQueue<>((o1,o2) -> o1.road-o2.road);
		maxRoad = (int) Math.pow(prim(minPQ), 2);

		// 내리막길(1)
		PriorityQueue<Node> maxPQ = new PriorityQueue<>((o1,o2) -> o2.road-o1.road);
		minRoad = (int) Math.pow(prim(maxPQ), 2);

		sb.append(maxRoad-minRoad);
		System.out.print(sb);
		br.close();
	}

	private static int prim(PriorityQueue<Node> pq) {
		int sum = 0;
		visited = new boolean[N+1];
		visited[0] = true;
		for(Node node : list[0]) {
			pq.offer(new Node(node.to, node.road));
		}

		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			if(!visited[curr.to]) {
				visited[curr.to] = true;
				sum += (curr.road==0) ? 1 : 0; // 오르막이면 피로도 증가

				for(Node next : list[curr.to]) {
					if(!visited[next.to]) {
						pq.offer(new Node(next.to, next.road));
					}
				}
			}
		}

		return sum;
	}
}