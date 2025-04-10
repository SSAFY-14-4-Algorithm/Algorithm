import java.io.*;
import java.util.*;
/*
 * 메모리: 15028KB
 * 시간: 124ms
 * 
 * 수열 A(1<=N<=1,000)
 * 증가하는 부분 수열 중 합이 가장 큰 것 
 *
 */

public class Main {
	static int N;
	static int[] A;
	static int[] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		
		dp = new int[N];
		for(int i=0;i<N;i++) {
			dp[i]=-1;
		}
		
		int ans =0;
		for(int i=0;i<N;i++) {
			ans = Math.max(ans,dfs(i));
		}
		
		System.out.println(ans);
		
	}
	
	static int dfs(int i) {
		if(dp[i]!=-1) {
			return dp[i];
		}
		
		dp[i] = A[i];
		for(int j=0;j<i;j++) {
			if(A[j]<A[i]) {
				dp[i] = Math.max(dp[i], dfs(j)+A[i]);
			}
		}
		return dp[i];
	}
	
}
