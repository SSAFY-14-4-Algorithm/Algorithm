/*
 * BOJ 16562번 : 친구비
 * 메모리 : 19,800kb
 * 시간 : 180ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_16562_친구비 {
    static int N, M, k;
    static int[] friendsCost;
    static int minCost;
    static List<Integer>[] friends;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        friendsCost = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++) {
            friendsCost[n] = Integer.parseInt(st.nextToken());
        }
        friends = new ArrayList[N+1];
        for(int n = 1; n <= N; n++) {
            friends[n] = new ArrayList<>();
        }
        for(int m = 1; m <= M; m++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            friends[v].add(w);
            friends[w].add(v);
        }

        minCost = 0;
        visited = new boolean[N+1];

        for(int n = 1; n <= N; n++) {
            if(!visited[n]) {
                minCost += bfs(n);
            }
        }

        if(minCost > k) sb.append("Oh no");
        else sb.append(minCost);
        System.out.println(sb);
        br.close();
    }

    public static int bfs(int n) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(n);
        visited[n] = true;
        int cost = friendsCost[n];

        while(!q.isEmpty()) {
            int curr = q.poll();
            for(int next : friends[curr]) {
                if(!visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                    cost = Math.min(cost, friendsCost[next]);
                }
            }
        }

        return cost;
    }
}
