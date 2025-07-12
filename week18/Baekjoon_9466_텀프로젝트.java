/*
 * BOJ 9466번 : 텀 프로젝트
 * 메모리 : 302,760kb
 * 시간 : 1,104ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_9466_텀프로젝트 {
    static int T, N;
    static int[] students;
    static boolean[] visited, team;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            students = new int[N+1];
            visited = new boolean[N+1];
            team = new boolean[N+1];
            answer = N;

            for(int n = 1; n <= N; n++) {
                students[n] = Integer.parseInt(st.nextToken());
                if(students[n] == n) {
                    team[n] = true;
                    visited[n] = true;
                    answer--;
                }
            }

            for(int n = 1; n <= N; n++) {
                if(!team[n]) {
                    dfs(n);
                }
            }

            sb.append(answer).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int student) {
        if(team[student]) {
            return;
        }

        if(visited[student]) {
            answer--;
            team[student] = true;
        }

        visited[student] = true;
        dfs(students[student]);
        visited[student] = false;

        team[student] = true;
    }
}
