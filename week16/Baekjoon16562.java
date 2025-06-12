package week16;

import java.io.IOException;

public class Baekjoon16562 {
    private static int N;
    private static int M;
    private static long K;

    private static int[] parent;
    private static int[] minFFofGroup;
    private static long totalRequiredCost;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        parent = new int[N + 1];
        minFFofGroup = new int[N + 1];
        // ! 입력
        for (int student = 1; student <= N; student++) {
            parent[student] = student;
            int friendFee = readInt();
            minFFofGroup[student] = friendFee;
            totalRequiredCost += friendFee;
        }
        // ! 압축
        for (int i = 0; i < M; i++) {
            int v = readInt();
            int w = readInt();
            unionFriends(v, w);
        }

        System.out.println((totalRequiredCost <= K) ? totalRequiredCost : "Oh no");
    }

    // ! 그룹 대표 찾기 (경로 압축)
    private static int findRoot(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = findRoot(parent[x]);
    }

    // ! 두 그룹을 합치면서 비용 조정
    private static void unionFriends(int studentA, int studentB) {
        int rootA = findRoot(studentA);
        int rootB = findRoot(studentB);
        if (rootA == rootB)
            return;

        // ! 비용이 큰놈을 작은놈에 붙임, 그 비용만큼 totalRequiredCost에서 차감
        if (minFFofGroup[rootA] < minFFofGroup[rootB]) {
            // B 그룹이 더 비싸므로 B를 A에 합침
            parent[rootB] = rootA;
            totalRequiredCost -= minFFofGroup[rootB];
        } else {
            // 반대면 A를 B에 합침
            parent[rootA] = rootB;
            totalRequiredCost -= minFFofGroup[rootA];
        }
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), result = 0;
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            result = result * 10 + (c & 15);
            c = System.in.read();
        }
        return result;
    }
}
