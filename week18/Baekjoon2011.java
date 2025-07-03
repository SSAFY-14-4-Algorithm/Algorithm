import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon2011 {

    //메모리 14480kb 시간 112ms
    //dp
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pwd = br.readLine();

        int n = pwd.length();
        int[] dp = new int[n+1];
        dp[0] = 1;

        if (pwd.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        dp[1] = 1;

        for (int i = 2 ; i <= n; i++) {
            char one = pwd.charAt(i - 1);
            char two = pwd.charAt(i - 2);
            int twoDigit = Integer.parseInt(pwd.substring(i - 2, i));

            if (one != '0') {
                dp[i] += dp[i-1];
                dp[i] %= 1000000;
            }
            if (two != '0' && twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= 1000000;
            }
        }

        System.out.println(dp[n]);
    }
}
