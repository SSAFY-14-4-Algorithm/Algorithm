/*
 * BOJ 11657번 : 타임머신
 * 메모리 : 17,280kb
 * 시간 : 220ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon11657 {
	static int N, M;
	static List<Node> edges;
	static class Node {
		int from, to, cost;
		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		edges = new ArrayList<>();
	
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges.add(new Node(from, to, cost));
		}
		
		BallmanFord(1); // start
		
		System.out.print(sb);
		br.close();
	}
	
	private static void BallmanFord(int start) {
		// 1. 최간 거리 테이블 초기화
		long[] dist = new long[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		
		// 2. 전체 간선 하나씩 확인 (N-1번)
		for(int i = 1; i < N; i++) {
			for(Node edge : edges) {
				// 출발 정점이 도달 가능한 경우,
				// 각 간선을 거쳐 다른 노르로 가는 비용을 계산해 최단 거리 테이블 갱신
				if(dist[edge.from] != Integer.MAX_VALUE 
					&& dist[edge.to] > dist[edge.from]+edge.cost) {
					dist[edge.to] = dist[edge.from]+edge.cost;
				}
			}
		}
		
		// 3. 음수 사이클 존재 여부 확인 (1번만)
		// 한번 더 돌려서 갱신되는 간선이 있으면 -> 음수 사이클!
		for(Node edge : edges) {
			if(dist[edge.from] != Integer.MAX_VALUE
				&& dist[edge.to] > dist[edge.from]+edge.cost) {
				sb.append(-1);
				return;
			}
		}
		
		// 4. 최종 결과 출력
		for(int n = 2; n <= N; n++) {
			sb.append(dist[n]==Integer.MAX_VALUE ? -1 : dist[n]).append("\n");
		}
	}
}