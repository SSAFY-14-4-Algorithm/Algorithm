import java.util.*;
import java.io.*;

/*
 * 	14588kb, 120ms
 */

//위상정렬을 이용한 풀이
//위상정렬을 이용해 불가능함도 체크 가능
public class Baekjoon2623 {
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
			int len = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			for(int j = 0; j < len-1; j++) {
				int cur = Integer.parseInt(st.nextToken());
				arr[prev].add(cur);
				degrees[cur] += 1;
				prev = cur;
			}
		}
		StringBuilder sb = new StringBuilder();
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= N; i++) {
			if(degrees[i] == 0) {
				q.add(i);
			}
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			sb.append(cur).append("\n");
			for(int nxt : arr[cur]) {
				degrees[nxt] -= 1;
				if(degrees[nxt] == 0) {
					q.add(nxt);
				}
			}
		}
		if(cnt == N) {
			System.out.print(sb);
		} else {
			System.out.println(0);
		}
	}
}
