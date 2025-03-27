package week8;

import java.io.IOException;

public class Baekjoon6603 {
    private static int K;
    private static int[] arr;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while ((K = readInt()) != 0) {
            arr = new int[K];
            visited = new boolean[K];
            for (int i = 0; i < K; i++)
                arr[i] = readInt();
            DFS(0, 0);
            sb.append("\n");
        }
        System.out.print(sb.toString().trim());
    }

    private static void DFS(int start, int depth) {
        if (depth == 6) {
            for (int i = 0; i < K; i++)
                if (visited[i])
                    sb.append(arr[i]).append(" ");
            sb.append("\n");
        }
        for (int i = start; i < K; i++) {
            visited[i] = true;
            DFS(i + 1, depth + 1);
            visited[i] = false;
        }
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        // if (c == 13)
        // System.in.read();
        return n;
    }
}