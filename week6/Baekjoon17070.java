package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 11804
 * 시간: 68
 *
 */


public class Baekjoon17070 {
	 static int N;
	    static int[][][] dp;
	    static int[][] map;

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        N = Integer.parseInt(br.readLine());

	        map = new int[N][N];        
	        for (int i = 0; i < N; i++) {
	            StringTokenizer st = new StringTokenizer(br.readLine());
	            for (int j = 0; j < N; j++) {
	                map[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }

	        // 0: 가로, 1: 세로, 2: 대각선
	        dp = new int[N][N][3];
	        dp[0][1][0] = 1;
	        for (int i = 0; i < N; i++) {
	            for (int j = 2; j < N; j++) {

	            	
	            	// 가로로 놓일려면
	                if (map[i][j] == 0) {
	                    dp[i][j][0] = dp[i][j-1][0] + dp[i][j-1][2];
	                    
	                    // 세로로 놓일려면
	                    if (i-1 >= 0) {
	                        dp[i][j][1] = dp[i-1][j][1] + dp[i-1][j][2];
	                        
	                        // 대각선으로 놓일려면
	                        if (map[i-1][j] == 0 && map[i][j-1] == 0) {
	                            dp[i][j][2] = dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
	                        }
	                    }
	                }
	            }
	        }
	        System.out.println(dp[N-1][N-1][0] + dp[N-1][N-1][1] + dp[N-1][N-1][2]);
	    }
	}
