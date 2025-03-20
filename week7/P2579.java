package day0317;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//메모리 11528KB 실행시간 68ms
public class P2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] list = new int[n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            list[i] = Integer.parseInt(br.readLine());
        }

        if (n == 1) {
            System.out.println(list[0]);
            return;
        } else if (n == 2) {
            System.out.println(list[0] + list[1]);
            return;
        }

        dp[0] = list[0];
        dp[1] = list[0] + list[1];
        dp[2] = Math.max(list[0] + list[2], list[1] + list[2]);

        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 3] + list[i - 1], dp[i - 2]) + list[i];
        }

        System.out.println(dp[n - 1]);
    }
}
