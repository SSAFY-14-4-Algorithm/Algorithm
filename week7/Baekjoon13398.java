import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 26192
 * 시간 : 196
 * 
 *
 */

public class Baekjoon13398 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] dp = new int[N];
		int[] remove = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = arr[0];
		remove[0] = arr[0];
		
		int maxSum = arr[0];
		
		for(int i = 1 ; i < N ; i++) {
			dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
			remove[i] = Math.max(dp[i - 1], remove[i - 1] + arr[i]);
			
			maxSum = Math.max(maxSum, Math.max(dp[i], remove[i]));
		}
		
		System.out.println(maxSum);
	}
}