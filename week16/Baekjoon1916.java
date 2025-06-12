import java.io.*;
import java.util.*;
/* 최소비용 구하기
 * 다익스트라 풀이
 * 50200kb, 332ms
 */
public class Baekjoon1916 {
	public static class Node {
		int num;
		long dist;
		public Node(int num, long dist) {
			this.num = num;
			this.dist = dist;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		long[][] arr = new long[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
			arr[i][i] = 0;
		}
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			arr[s][e] = Math.min(arr[s][e], d);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		PriorityQueue<Node> q = new PriorityQueue<>((o1, o2) -> (int)(o1.dist-o2.dist));
		long[] dist = new long[N+1];
		for(int i = 0; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[start] = 0;
		q.add(new Node(start, 0));
		while(!q.isEmpty()) {
			Node cur = q.poll();
			if(cur.num == end) break;
			if(cur.dist > dist[cur.num]) continue;
			for(int i = 1; i <= N; i++) {
				long cost = cur.dist + arr[cur.num][i];
				if(cost < dist[i]) {
					dist[i] = cost;
					q.add(new Node(i, cost));
				}
			}
		}
		System.out.println(dist[end]);
	}
}