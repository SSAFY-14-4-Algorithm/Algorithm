/*
 * BOJ2096번 : 내려가기
 * 메모리 : 54,320kb
 * 시간 : 328ms
 */

import java.io.*;
import java.util.*;

public class BOJ2096_내려가기 {
    static int N;
    static int[][] arr;
    static int[][] maxDp;
    static int[][] minDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];

        for(int n = 0; n < N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < 3; i++) {
                arr[n][i] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < 3; i++) {
            maxDp[0][i] = arr[0][i];
            minDp[0][i] = arr[0][i];
        }

        for(int n = 1; n < N; n++) {
            maxDp[n][0] = Math.max(maxDp[n-1][0], maxDp[n-1][1]) + arr[n][0];
            maxDp[n][1] = Math.max(maxDp[n-1][0], Math.max(maxDp[n-1][1], maxDp[n-1][2])) + arr[n][1];
            maxDp[n][2] = Math.max(maxDp[n-1][1], maxDp[n-1][2]) + arr[n][2];

            minDp[n][0] = Math.min(minDp[n-1][0], minDp[n-1][1]) + arr[n][0];
            minDp[n][1] = Math.min(minDp[n-1][0], Math.min(minDp[n-1][1], minDp[n-1][2])) + arr[n][1];
            minDp[n][2] = Math.min(minDp[n-1][1], minDp[n-1][2]) + arr[n][2];
        }

        sb.append(Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]))).append(" ");
        sb.append(Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2])));

        System.out.println(sb);
        br.close();
    }
}
