package week10;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 345764
 * 시간 : 756
 * 
 */


public class Baekjoon15486{
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+2];
        int[][] arr = new int[N+1][2];
        for(int i = 1; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for(int i= N; i >= 1 ; i--) {
            int day = arr[i][0] + i;

            if(day <= N + 1) {
                dp[i] = Math.max(dp[day] + arr[i][1], dp[i + 1]);
            } else {
                dp[i] = dp[i + 1];
            }
        }
        System.out.println(dp[1]);
    }
}