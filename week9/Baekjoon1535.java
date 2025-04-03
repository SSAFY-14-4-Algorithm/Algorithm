import java.io.*;

public class Baekjoon1535 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    private static int[] health; // 각 사람과 인사할 때 소모되는 체력
    private static int[] joy;    // 각 사람과 인사할 때 얻는 기쁨 값
    private static int[][] dp;   // dp[i][j]: i번째 사람까지 고려했을 때, j만큼의 체력으로 얻을 수 있는 최대 기쁨
    private static int N;        // 사람의 수
    
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        health = new int[N + 1];
        joy = new int[N + 1];
        
        String[] tokens = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            health[i] = Integer.parseInt(tokens[i - 1]);
        }
        
        tokens = br.readLine().split(" ");
        for (int i = 1; i <= N; i++) {
            joy[i] = Integer.parseInt(tokens[i - 1]);
        }
        
        dp = new int[N + 1][101];
    
        for (int i = 1; i <= N; i++) { // i번째 사람을 고려할 때
            for (int j = 1; j <= 100; j++) { // 현재 체력 j로 가능한 최대 기쁨 계산
                if (j > health[i]) {
                    // 현재 체력이 i번째 사람과 인사할 체력보다 많으면
                    // 1. i번째 사람과 인사를 하지 않는 경우 (dp[i-1][j])
                    // 2. i번째 사람과 인사를 하는 경우 (dp[i-1][j-health[i]] + joy[i])
                    // 두 경우 중 최대값을 선택
                    dp[i][j] = Math.max(dp[i - 1][j], joy[i] + dp[i - 1][j - health[i]]);
                } else {
                    // 체력이 부족하면 i번째 사람과 인사를 할 수 없음
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        bw.write(dp[N][100]+"\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
