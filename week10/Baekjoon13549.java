import java.util.*;
import java.io.*;
/* DP 풀이
 * 14484kb, 104ms
 */
public class Baekjoon13549 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[K+1];
		for(int i = 0; i <= K; i++) {
			if(i <= N) {
				dp[i] = N - i;
			} else {
				dp[i] = dp[i-1] + 1;
				if(i % 2 == 0) {
					dp[i] = Math.min(dp[i], dp[i/2]);
				} else {
					dp[i] = Math.min(dp[i], Math.min(dp[(i-1)/2]+1, dp[(i+1)/2]+1));
				}
			}
		}
		System.out.println(dp[K]);
	}
}

/* 0-1 BFS 풀이
 * 16180kb, 124ms
 */

/*
 import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		Deque<Integer> q = new ArrayDeque<>();
		q.add(A);
		int[] visited = new int[Math.max(B+2, A+1)];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[A] = 1;
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == B) break;
			int cost = visited[cur];
			
			int pos1 = cur*2;
			if(pos1 <= B+1 && visited[pos1] > cost) {
				visited[pos1] = cost;
				q.addFirst(pos1);
			}
			
			int pos2 = cur + 1;
			if(pos2 <= B && visited[pos2] > cost + 1) {
				visited[pos2] = cost + 1;
				q.add(pos2);
			}
			
			int pos3 = cur - 1;
			if(pos3 >= 0 && visited[pos3] > cost + 1) {
				visited[pos3] = cost + 1;
				q.add(pos3);
			}
		}
		System.out.println(visited[B]-1);
	}
}
 */




