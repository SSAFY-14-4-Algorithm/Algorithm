package dat0319;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
// 메모리 11720KB  시간 72ms
public class P1446 {
    static class Shortcut {
        int start, end, cost;
        public Shortcut(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] first = br.readLine().split(" ");
        int n = Integer.parseInt(first[0]);
        int d = Integer.parseInt(first[1]);

        List<Shortcut>[] shortcuts = new ArrayList[d + 1];
        for (int i = 0; i <= d; i++) {
            shortcuts[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" ");
            int start = Integer.parseInt(s[0]);
            int end = Integer.parseInt(s[1]);
            int cost = Integer.parseInt(s[2]);
            if (end > d || end <= start) continue;
            shortcuts[start].add(new Shortcut(start, end, cost));
        }

        int[] dp = new int[d + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i <= d; i++) {
            if (i > 0) dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            for (Shortcut sc : shortcuts[i]) {
                if (dp[sc.end] > dp[i] + sc.cost) {
                    dp[sc.end] = dp[i] + sc.cost;
                }
            }
        }

        System.out.println(dp[d]);
    }
}
