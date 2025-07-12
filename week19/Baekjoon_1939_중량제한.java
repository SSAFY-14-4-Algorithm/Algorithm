/*
 * BOJ 1939번 : 중량제한
 * 메모리 : 59,436kb
 * 시간 : 456ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1939_중량제한 {
    static int N, M, A, B, C;
    static int start, end;
    static boolean[] visited;
    static List<Edge>[] graph;
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int n = 1; n <= N; n++) {
            graph[n] = new ArrayList<>();
        }

        int maxWeight = 0;

        for(int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            graph[A].add(new Edge(B, C));
            graph[B].add(new Edge(A, C));

            maxWeight = Math.max(maxWeight, C);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = maxWeight;
        int answer = 0;

        while(left <= right) {
            int mid = (left+right)/2;
            visited = new boolean[N+1];
            if(bfs(mid)) {
                answer = mid;
                left = mid+1;
            } else {
                right = mid-1;
            }
        }

        sb.append(answer);
        System.out.println(sb);
    }

    // 현재 중량으로 이동 가능한지 확인
    public static boolean bfs(int weight) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int curr = q.poll();

            if(curr==end) {
                return true;
            }

            for(Edge next : graph[curr]) {
                if(!visited[next.to] && next.weight >= weight) {
                    visited[next.to] = true;
                    q.add(next.to);
                }
            }
        }

        return false;
    }
}
