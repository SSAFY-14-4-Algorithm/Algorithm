package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//메모리 14128KB 실행시간 100ms
public class Baekjoon13171 {
    static long[] dp;
    static long a,x;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = Long.parseLong(br.readLine());
        x = Long.parseLong(br.readLine());

        dp = new long[64];
        dp[0] = a % 1000000007;
        makeDp();
        long answer = 1;
        int cnt = 0;
        while(x > 0){
            if (x % 2 == 1){
                answer = (answer * dp[cnt]) % 1000000007;
            }
            cnt ++ ;
            x /= 2;
        }
        System.out.println(answer);
    }
    public static void makeDp() {
        for (int i = 1; i < 64; i++) {
            dp[i] = (dp[i - 1] * dp[i - 1]) % 1000000007;
        }
    }

}
