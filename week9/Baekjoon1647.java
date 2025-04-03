import java.util.*;
import java.io.*;
/* Kruskal
 * 321592kb, 1116ms
 */
public class Baekjoon1647 {
	static class Road {
		int s, e, w;
		public Road(int s, int e, int w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
	}
	
	static int[] parent;
	public static int find(int x) {
		if(x != parent[x]) return parent[x] = find(parent[x]);
		return x;
	}
	
	public static void union(int a, int b) {
		if(a > b) parent[a] = b;
		else parent[b] = a;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2)-> o1.w - o2.w);
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			pq.add(new Road(a, b, w));
		}
		int cnt = 0;
		int max = 0;
		int answer = 0;
		while(!pq.isEmpty()) {
			Road cur = pq.poll();
			int a = find(cur.s);
			int b = find(cur.e);
			if(a != b) {
				union(a, b);
				max = Math.max(cur.w, max);
				answer += cur.w;
				cnt++;
			}
			if(cnt == N-1) {
				break;
			}
		}
		System.out.println(answer-max);
	}
}

/* Prim
 * 352748kb, 1572ms
 */
//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static class Road {
//		int nxt, w;
//		public Road(int nxt, int w) {
//			this.nxt = nxt;
//			this.w = w;
//		}
//	}
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		int N = Integer.parseInt(st.nextToken());
//		int M = Integer.parseInt(st.nextToken());
//		boolean[] visited = new boolean[N+1];
//		ArrayList<Road>[] arr = new ArrayList[N+1];
//		for(int i = 1; i <= N; i++) {
//			arr[i] = new ArrayList<>();
//		}
//		for(int i = 0; i < M; i++) {
//			st = new StringTokenizer(br.readLine());
//			int a = Integer.parseInt(st.nextToken());
//			int b = Integer.parseInt(st.nextToken());
//			int w = Integer.parseInt(st.nextToken());
//			arr[a].add(new Road(b, w));
//			arr[b].add(new Road(a, w));
//		}
//		PriorityQueue<Road> pq = new PriorityQueue<>((o1, o2)-> o1.w - o2.w);
//		visited[1] = true; //1 선택
//		for(Road road : arr[1]) {
//			pq.add(road);
//		}
//		int answer = 0;
//		int cnt = 1;
//		int max = 0;
//		while(!pq.isEmpty()) {
//			Road cur = pq.poll();
//			if(visited[cur.nxt]) continue;
//			answer += cur.w;
//			max = Math.max(max, cur.w);
//			cnt++;
//			if(cnt == N) break;
//			visited[cur.nxt] = true;
//			for(Road road : arr[cur.nxt]) {
//				if(!visited[road.nxt]) {
//					pq.add(road);
//				}
//			}
//		}
//		System.out.println(answer-max);
//	}
//}

