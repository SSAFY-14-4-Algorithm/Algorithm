import java.io.*;
import java.util.stream.Stream;
/**
 * 11520 KB, 68 ms
 */

public class Main {

    public static void main(String[] args) throws IOException {

        String num = br.readLine();
        System.out.print(solution(num));
    }

    private static final int MOD = 1_000_000;
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int solution(String num){
        int n = num.length();

        if (num.charAt(0)=='0') return 0; // 해석 불가

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        // dp[a] = b : a번째 글자까지 해석할 수 있는 가짓수 = b가지
        // dp[0]: 빈 문자열 1가지, dp[1]: 첫 글자가 0이 아닌 경우 1가지

        for (int i=2;i<=n;i++){
            // 한 자리
            int one = num.charAt(i-1) - '0';
            if (one>0) dp[i] = (dp[i] + dp[i-1]) % MOD;

            // 두자리
            int ten = (num.charAt(i-2) - '0') * 10 + one;
            if (10<=ten && ten<=26) dp[i] = (dp[i] + dp[i-2]) % MOD;
        }

        return dp[n];
    }
}
