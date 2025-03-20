package dat0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// 메모리 29664KB 실행시간 212ms
public class P13398 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n][2];

        String[] input = br.readLine().split(" ");
        int first = Integer.parseInt(input[0]);

        dp[0][0] = first;
        dp[0][1] = first;
        int answer = first;
        // 점화식 dp[][1]은 더하는게 더 작아지면 dp[0][0]값 쓰기
        // 항상 dp[i][1]이 더 크다고생각했는데 음수 양수 나오는경우 그렇지 않더라
        for (int i = 1 ; i < n ; i ++ ){
            int tmp = Integer.parseInt(input[i]);

            dp[i][0] = Math.max(tmp, dp[i-1][0] + tmp);
            dp[i][1] = Math.max(dp[i - 1][0] ,dp[i - 1][1] + tmp);
            answer = Math.max(answer,Math.max(dp[i][0],dp[i][1]));
        }

        System.out.println(answer);

    }
}
