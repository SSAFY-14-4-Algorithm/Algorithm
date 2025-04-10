package week10;

import java.io.*;
import java.util.*;

/**
 * 
 * 
 * 메모리 : 11780
 * 시간 : 72
 * 
 */

public class Baekjoon9084 {
	
	static int N, K, result;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < testCase ; t++) {
        	
        	int N = Integer.parseInt(br.readLine());
        	
        	int[] arr = new int[N + 1];
        	st = new StringTokenizer(br.readLine());
        	for(int i = 1; i <= N ; i++) {
        		arr[i] = Integer.parseInt(st.nextToken());
        	}
        	
        	int M = Integer.parseInt(br.readLine());
        	int[] dp = new int[M + 1];
        	
        	for(int i = 1 ; i <= N ; i++) {
        		for(int j = 1 ; j <= M ; j++) {
        			
        			if(j - arr[i] > 0 ) {
        				dp[j] = dp[j] + dp[j - arr[i]];
        			}
        			else if(j - arr[i] == 0) {
        				dp[j]++;
        			}
        		}
        	}
        	
        	sb.append(dp[M]).append("\n");
        }
        System.out.println(sb);
    }
}