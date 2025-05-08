package week14;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 18600
 * 시간 : 236
 *
 */


public class Baekjoon15919_사자는여행왕이야 {
	
	static int N, M;
	static int[] dp = new int[1001];
	static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[M][2];
        int result = 1000;
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr, (o1, o2)-> o1[0]-o2[0]);
        
        Arrays.fill(dp, -1);
        
        for(int i = 0; i < M ; i++) {
        	
        	result = Math.min(result, Math.max(arr[i][0] - 1, solve(i)));
        }
        
        System.out.println(result);
    }
    
    public static int solve(int here) {
    	if(here == M) return 0;
    	if(dp[here] != -1) return dp[here];
    	
    	int ret = 10000;
    	
        for (int i = here + 1; i < M; i++) {
            if (arr[i][0] <= arr[here][1]) continue;
            ret = Math.min(ret, Math.max(arr[i][0] - arr[here][1] - 1, solve(i)));
        }

        ret = Math.min(ret, N - arr[here][1]);

        return dp[here] = ret;
    }
}

