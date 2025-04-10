import java.io.*;
import java.util.*;
/*
 * 메모리: 313976KB
 * 시간: 644ms
 * 
 * 
 */
 

public class Main {
	static int N;
	static int[] T;
	static int[] P;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	N = Integer.parseInt(br.readLine());
	
	T = new int[N];
	P = new int[N];
	dp = new int[N+1];
	Arrays.fill(dp, 0);
	
	for(int i=0;i<N;i++) {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T[i] = Integer.parseInt(st.nextToken());
		P[i] = Integer.parseInt(st.nextToken());
	}
	
	for(int i=0;i<N;i++) {
		if(i+T[i]<=N) {
			dp[i+T[i]] = Math.max(dp[i+T[i]],dp[i]+P[i]);
		}
		dp[i+1] = Math.max(dp[i+1],dp[i]);
	}
	
	int res = 0;
	for(int i=0;i<=N;i++) {
		res = Math.max(res, dp[i]);
	}
	
	System.out.println(res);
	}
	
}
