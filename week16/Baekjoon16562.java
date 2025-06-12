import java.io.*;
import java.util.*;
/* 친구비
 * BFS풀이
 * 20456kb, 204ms
 */

public class Baekjoon16562 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		ArrayList<Integer>[] arr = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		int[] price = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			price[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			arr[v].add(w);
			arr[w].add(v);
		}
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		int ans = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				q.clear();
				q.add(i);
				visited[i] = true;
				int minPrice = price[i];
				while(!q.isEmpty()) {
					int cur = q.poll();
					for(int nxt : arr[cur]) {
						if(!visited[nxt]) {
							visited[nxt] = true;
							minPrice = Math.min(minPrice, price[nxt]);
							q.add(nxt);
						}
					}
				}
				ans += minPrice;
			}
			if(ans > k) {
				break;
			}
		}
		if(ans > k) {
			System.out.println("Oh no");
		} else {
			System.out.println(ans);
		}
	}
}