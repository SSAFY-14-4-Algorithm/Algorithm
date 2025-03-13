package week6;

/**
 * <h1>BAEKJOON 2623번 음악프로그램 GOLD III</h1>
 * <p>
 * JAVA 8 : 메모리 11,824KB, 시간 80ms <br>
 * JAVA11 : 메모리 14,152KB, 시간 108ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-03-12
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon2623 {
    protected static int[] inDegree;
    protected static List<Integer>[] graph;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        inDegree = new int[N + 1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int count = readInt();
            int before = readInt();
            for (int j = 1; j < count; j++) {
                int after = readInt();
                graph[before].add(after);
                inDegree[after]++;
                before = after;
            }
        }
        System.out.print(topologySort(N));
    }

    public static String topologySort(int N) {
        StringBuilder sb = new StringBuilder();
        Deque<Integer> queue = new ArrayDeque<>();
        int count = 0;
        for (int i = 1; i < N + 1; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" ");
            count++;
            for (int next : graph[now]) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }
        return (count != N) ? "0" : sb.toString();
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