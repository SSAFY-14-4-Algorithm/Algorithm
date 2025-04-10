package BAEKJOON.Gold.Gold_V.P15486번_퇴사_2;

import java.io.IOException;

public class Baekjoon15486 {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] T = new int[N + 1];
        int[] P = new int[N + 1];
        int[] dp = new int[N + 2];
        for (int i = 1; i <= N; i++) {
            T[i] = readInt();
            P[i] = readInt();
        }
        for (int i = N; i >= 1; i--) {
            if (i + T[i] <= N + 1) {
                dp[i] = Math.max(dp[i + 1], dp[i + T[i]] + P[i]);
            } else {
                dp[i] = dp[i + 1];
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
