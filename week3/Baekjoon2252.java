import java.util.*;

import java.io.*;
public class Baekjoon2252 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] degrees = new int[N+1];
		ArrayList<Integer>[] arr = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			degrees[a] += 1;
			arr[b].add(a);
		}
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(degrees[i] == 0) {
				q.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		int[] ans = new int[N+1];
		int index = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			ans[index++] = cur;
			for(int nxt : arr[cur]) {
				degrees[nxt] -= 1;
				if(degrees[nxt] == 0) {
					q.add(nxt);
				}
			}
		}
		for(int i = N; i>= 1; i--) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}

}
