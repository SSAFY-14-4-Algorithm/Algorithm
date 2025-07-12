/*
 * BOJ 17182번 : 우주 탐사선
 * 메모리 : 12,084kb
 * 시간 : 100ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_17182_우주탐사선 {
    static int N, K;
    static int[][] time;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        time = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                time[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                for(int k = 0; k < N; k++) {
                    time[i][j] = Math.min(time[i][j], time[i][k] + time[k][j]);
                }
            }
        }

        visited = new boolean[N];
        visited[K] = true;
        answer = Integer.MAX_VALUE;
        dfs(K, 1, 0); // start, count, totalTime

        sb.append(answer);
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int curr, int count, int totalTime) {
        if(count == N) {
            answer = Math.min(answer, totalTime);
            return;
        }

        for(int next = 0; next < N; next++) {
            if(!visited[next]) {
                visited[next] = true;
                dfs(next, count+1, totalTime+time[curr][next]);
                visited[next] = false;
            }
        }
    }
}
