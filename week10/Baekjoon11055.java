package week10;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 	12228 KB
 * 시간 : 88 ms
 */

public class Baekjoon11055 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int result = 0;
        
        int[] dp = new int[N];
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N ; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dp[0] = arr[0];
        for(int i = 1 ; i < N; i++) {
        	dp[i] = arr[i];
        	for(int j = i - 1 ; j >= 0; j--) {
        		
        		if(arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
        			dp[i] = dp[j] + arr[i];
        		}
        	}
        }
        
        for(int i = 0; i < N ; i++) {
        	result = Math.max(result, dp[i]);
        }
        
        System.out.println(result);
    }
}