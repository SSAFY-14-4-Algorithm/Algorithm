/*
 * BOJ 1238번 : 파티
 * 메모리 : 211,144kb
 * 시간 : 280ms
 * 
 * 출발 + 도착 거리가 가장 오래 걸리는 학생의 소요시간 출력
 */

import java.io.*;
import java.util.*;

public class Baekjoon1238 {
	static int N, M, X;
	static List<Node>[] edges;
	static List<Node>[] edgesReverse;
	static class Node {
		int to, cost;
		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
	static int[] dist;
	static int[] distReverse;
	static int maxTime;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList[N+1];
		for(int n = 0; n <= N; n++) {
			edges[n] = new ArrayList<Node>();
		}
		edgesReverse = new ArrayList[N+1];
		for(int n = 0; n <= N; n++) {
			edgesReverse[n] = new ArrayList<Node>();
		}
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[from].add(new Node(to, cost));
			edgesReverse[to].add(new Node(from, cost));
		}
		
		maxTime = Integer.MIN_VALUE;
		
		// 각자 집에서 -> M번 마을로 갈 때
		DijkstraReverse();
		
		// M번 마을에서 -> 각자 집으로 돌아갈 때
		Dijkstra();
		
		for(int n = 1; n <= N; n++) {
			maxTime = Math.max(maxTime, distReverse[n]+dist[n]);
		}
		
		sb.append(maxTime);
		System.out.print(sb);
		br.close();
	}
	
	private static void DijkstraReverse() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost-o2.cost);
		pq.offer(new Node(X, 0)); // to, cost
		
		distReverse = new int[N+1];
		Arrays.fill(distReverse, Integer.MAX_VALUE);
		distReverse[X] = 0;
		
		while(!pq.isEmpty()) {
			Node currNode = pq.poll();
			
			for(int i = 0; i < edgesReverse[currNode.to].size(); i++) {
				Node nextNode = edgesReverse[currNode.to].get(i);
				
				if(distReverse[nextNode.to] > currNode.cost+nextNode.cost) {
					distReverse[nextNode.to] = currNode.cost+nextNode.cost;
					pq.offer(new Node(nextNode.to, distReverse[nextNode.to]));
				}
			}
		}
	}
	
	private static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> o1.cost-o2.cost);
		pq.offer(new Node(X, 0)); // to, cost
		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;
		
		while(!pq.isEmpty()) {
			Node currNode = pq.poll();
			
			for(int i = 0; i < edges[currNode.to].size(); i++) {
				Node nextNode = edges[currNode.to].get(i);
				
				if(dist[nextNode.to] > currNode.cost+nextNode.cost) {
					dist[nextNode.to] = currNode.cost+nextNode.cost;
					pq.offer(new Node(nextNode.to, dist[nextNode.to]));
				}
			}
		}
	}
}
