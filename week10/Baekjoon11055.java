package AlgorithmStudy.week10;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 12,048 kb
 * 실행 시간 : 84 ms
 */

public class BJ_11055_가장큰증가하는부분수열 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static int N, result;
	static int[] seq, dp;
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		
		seq = new int[N+1];
		dp = new int[N+1];
		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			seq[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=1;i<=N;i++) {
			dp[i] = seq[i];
			for(int j=0;j<i;j++) {
				if(seq[i] > seq[j]) {
					dp[i] = Math.max(dp[i], dp[j]+seq[i]);
				}
			}
			
			result = Math.max(result, dp[i]);
		}
		
		System.out.println(result);

	}

}
