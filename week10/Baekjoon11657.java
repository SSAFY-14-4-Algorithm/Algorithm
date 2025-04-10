package BAEKJOON.Gold.Gold_IV.P11657번_타임머신;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon11657 {
    private static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static List<Edge> edges;
    private static long[] distance;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        edges = new ArrayList<>();
        distance = new long[N + 1];
        for (int i = 1; i <= N; i++)
            distance[i] = Long.MAX_VALUE;
        distance[1] = 0;
        for (int i = 0; i < M; i++) {
            int u = readInt();
            int v = readInt();
            int w = readInt();
            edges.add(new Edge(u, v, w));
        }
        if (bellmanFord()) {
            System.out.println(-1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            sb.append(distance[i] == Long.MAX_VALUE ? -1 : distance[i]).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static boolean bellmanFord() {
        boolean updated;
        // 최단 경로 확정을 위해 N-1번 반복 (조기 종료 적용)
        for (int i = 1; i < N; i++) {
            updated = false;
            for (Edge edge : edges) {
                if (distance[edge.from] != Long.MAX_VALUE && distance[edge.to] > distance[edge.from] + edge.weight) {
                    distance[edge.to] = distance[edge.from] + edge.weight;
                    updated = true;
                }
            }
            if (!updated) { // 이번 반복에서 갱신이 없으면 종료
                break;
            }
        }
        // 음수 사이클 검출
        for (Edge edge : edges) {
            if (distance[edge.from] != Long.MAX_VALUE && distance[edge.to] > distance[edge.from] + edge.weight) {
                return true;
            }
        }
        return false;
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        c = System.in.read();
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
