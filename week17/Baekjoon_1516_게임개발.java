/* 
 * BOJ 1516번 : 게임개발
 * 메모리 : 18,928kb
 * 시간 : 156ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1516_게임개발 {
    static int N;
    static List<Integer>[] preList;
    static int[] buildTime;
    static int[] dp;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        preList = new ArrayList[N+1];
        buildTime = new int[N+1];
        dp = new int[N+1];
        visited = new boolean[N+1];

        for(int n = 1; n <= N; n++) {
            preList[n] = new ArrayList<>();
        }

        for(int n = 1; n <= N; n++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[n] = Integer.parseInt(st.nextToken());

            while(true) {
                int pre = Integer.parseInt(st.nextToken());
                if(pre == -1) break;
                preList[n].add(pre); // n을 짓기 전에 pre를 지어야 함
            }
        }

        for(int n = 1; n <= N; n++) {
            sb.append(dfs(n)).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static int dfs(int n) {
        if(visited[n]) return dp[n]; // 이미 계산한 경우
        visited[n] = true;

        int maxPreTime = 0;
        for(int pre : preList[n]) {
            maxPreTime = Math.max(maxPreTime, dfs(pre));
        }

        dp[n] = maxPreTime + buildTime[n];
        return dp[n];
    }
}
