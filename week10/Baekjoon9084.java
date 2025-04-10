package BAEKJOON.Gold.Gold_V.P9084번_동전;

import java.io.IOException;

public class Baekjoon9084 {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = readInt();
        while (T-- > 0) {
            int N = readInt();
            int[] coins = new int[N + 1];
            for (int i = 1; i <= N; i++)
                coins[i] = readInt();
            int M = readInt();
            int[] dp = new int[M + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (j - coins[i] > 0) {
                        dp[j] += dp[j - coins[i]];
                    } else if (j - coins[i] == 0) {
                        dp[j]++;
                    }
                }
            }
            sb.append(dp[M]).append('\n');
        }
        System.out.print(sb);
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