package week17;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Baekjoon1516 {
    private static int N;// 건물 종류 수
    private static int[] buildTime;// 각 건물의 건축 소요 시간
    private static int[] dp;// dp[i] = 건물 i가 완성되는 최소 시간
    private static List<Integer>[] adj;// adj[u] = u를 선행으로 하는 건물들의 리스트
    private static int[] indegree;// indegree[i] = 건물 i가 지어지기 전에 지어야 할 건물 개수

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        N = readInt();
        buildTime = new int[N + 1];
        dp = new int[N + 1];
        adj = new ArrayList[N + 1];
        indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            dp[i] = buildTime[i] = readInt(); // 선행이 없을 경우, 최소 시간은 자기 시간
            while (true) {
                int buildAfter = readInt();
                if (buildAfter == -1)
                    break;
                // buildAfter → i 선행 관계 추가
                adj[buildAfter].add(i);
                indegree[i]++; // i가 지어지기 전 선행 건물 개수 증가
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        // 진입차수(선행 건물 수)가 0인 건물들을 큐에 넣음
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj[u]) {
                // u가 완성된 시점(dp[u]) 이후에야 건물 v를 지을 수 있으므로
                // 기존 dp[v]와 비교하여 더 늦은(=더 큰) 값을 dp에 반영
                dp[v] = Math.max(dp[v], dp[u] + buildTime[v]);

                // 선행으로부터 하나씩 처리할 때마다 indegree[v]--
                if (--indegree[v] == 0) {
                    // 모든 선행이 다 처리되었으면 큐에 추가
                    q.add(v);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(dp[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        boolean isNegative = false;
        while (c <= ' ') {
            c = System.in.read();
        }
        if (c == '-') {
            isNegative = true;
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return isNegative ? -n : n;
    }
}
