import java.io.*;
import java.util.*;
/*
 * 메모리:22092KB
 * 시간:136ms
 */
public class Baekjoon13549 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int minTime = Integer.MAX_VALUE;
	static int[] dp;
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int k = Integer.parseInt(s[1]);
		dp = new int[200001];
		Arrays.fill(dp, (int)1e9);
		bfs(n,k,0);
		System.out.println(dp[k]);
	}
	public static void bfs(int n, int k, int time) {
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(n);
		
		while(!q.isEmpty()) {
			int x = q.poll();
			if(x*2<=200000) {
				if(dp[x*2] > dp[x]) {
					dp[x*2] = dp[x];
					q.offer(x*2);
				}
			}
			if(x+1<=100000) {
				if(dp[x+1] > dp[x]+1) {
					dp[x+1] = dp[x]+1;
					q.offer(x+1);
				}
			}
			
			if(x-1>=0) {
				if(dp[x-1] > dp[x]+1) {
					dp[x-1] = dp[x]+1;
					q.offer(x-1);
				}
			}
		}
	}
}
