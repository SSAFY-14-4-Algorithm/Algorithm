import java.util.*;
import java.io.*;
/*
 * 14204Kb, 104ms
 */
//Bottom-up
class Baekjoon2579
{	
	public static void main(String args[]) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int[][] dp = new int[N+1][2];
		int num = Integer.parseInt(br.readLine());
		dp[1][0] = num;
		dp[1][1] = num;
		
		for(int i = 2; i < N+1; i++) {
			num = Integer.parseInt(br.readLine());
			dp[i][0] = dp[i-1][1] + num;
			dp[i][1] = Math.max(dp[i-2][0], dp[i-2][1]) + num;
		}
		System.out.println(Math.max(dp[N][0], dp[N][1]));
	}
}
/*
 * 14252kb, 104ms
 */
//top-down
//import java.util.*;
//import java.io.*;
//
//public class Main {
//	static int[][] dp;
//	static int N;
//	static int[] arr;
//	
//	public static int topDown(int idx, int prev) {
//		if(idx < 0) return 0;
//		
//		if(dp[idx][prev] != 0) return dp[idx][prev];
//		
//		if(prev == 0) {
//			dp[idx][prev] = topDown(idx-1, 1) + arr[idx];
//		} else {
//			dp[idx][prev] = Math.max(topDown(idx-2, 0), topDown(idx-2, 1)) + arr[idx];
//		}
//		return dp[idx][prev];
//	}
//	
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		 N = Integer.parseInt(br.readLine());
//		arr = new int[N];
//		for(int i = 0; i < N; i++) {
//			arr[i] = Integer.parseInt(br.readLine());
//		}
//		dp = new int[N][2];
//		System.out.println(Math.max(topDown(N-1, 0), topDown(N-1, 1)));
//	}
//}