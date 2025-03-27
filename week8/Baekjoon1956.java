package week8;

/**
 * <h1>BAEKJOON 1967번 운동 GOLD IV</h1>
 * <p>
 * JAVA11 : 메모리 17,692 KB 시간 320ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 */

import java.io.IOException;

public class Baekjoon1956 {
    private static int[][] graph;
    private static int V;
    private static final int MAX_VALUE = 1_000_000_001;

    public static void main(String[] args) throws IOException {
        V = readInt();
        int E = readInt();
        graph = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++)
                graph[i][j] = MAX_VALUE;
            // 제자리 가중치 0
            graph[i][i] = 0;
        }
        while (E-- > 0) {
            // a번 마을에서 b번 마을로 가는 거리가 c인 도로
            int a = readInt();
            int b = readInt();
            int c = readInt();
            graph[a][b] = c;
        }
        int minCycleLength = floydWarshall();
        System.out.print(minCycleLength);
    }

    private static int floydWarshall() {
        // 플로이드워셜
        for (int k = 1; k <= V; k++)
            for (int i = 1; i <= V; i++)
                for (int j = 1; j <= V; j++)
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);

        // 시작지점 -> 또다른지점 -> 시작지점(사이클) 가능한 최소 길이
        int minCycleLength = Integer.MAX_VALUE;
        for (int from = 1; from <= V; from++) {
            for (int to = 1; to <= V; to++) {
                if (from != to) {
                    if (graph[from][to] != MAX_VALUE && graph[to][from] != MAX_VALUE) {
                        int tempLength = graph[from][to] + graph[to][from];
                        minCycleLength = Math.min(minCycleLength, tempLength);
                    }
                }
            }
        }

        // 사이클이 없으면 -1
        return minCycleLength == Integer.MAX_VALUE ? -1 : minCycleLength;
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
