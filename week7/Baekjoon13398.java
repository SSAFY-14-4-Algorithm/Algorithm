import java.io.*;
import java.util.*;

public class Baekjoon13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i][0]: i번째 요소까지의 최대 연속 합 (삭제 없음)
        // dp[i][1]: i번째 요소까지의 최대 연속 합 (하나 삭제)
        int[][] dp = new int[n][2];
        dp[0][0] = arr[0];
        dp[0][1] = 0; // 첫 번째 요소는 삭제하면 아무것도 남지 않음

        int maxSum = arr[0];

        for (int i = 1; i < n; i++) {
            // 삭제하지 않은 경우 (기존 연속합 + 현재 값) vs (현재 값 단독)
            dp[i][0] = Math.max(dp[i - 1][0] + arr[i], arr[i]);

            // 삭제한 경우 (이전 삭제 안한 값에서 현재 값 제거) vs (이전 삭제한 값에서 이어가기)
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1] + arr[i]);

            // 최대값 갱신
            maxSum = Math.max(maxSum, Math.max(dp[i][0], dp[i][1]));
        }

        System.out.println(maxSum);
    }
}