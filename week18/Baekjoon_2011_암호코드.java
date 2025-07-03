import java.io.*;
/*
 * 메모리:11524KB
 * 시간:	64ms
 */
public class Baekjoon_2011_암호코드 {
    static final int MOD = 1000000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String s = br.readLine();
        int n = s.length();

        // 잘못된 암호: 0으로 시작하면 해석 불가능
        if(s.charAt(0) == '0') {
            System.out.println(0);
            return;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1; // 빈 문자열 해석 방법 1개
        dp[1] = 1; // 첫 문자 1개 해석 가능

        for(int i=2;i<=n;i++) {
            char prev = s.charAt(i-2); // i-1번째 문자
            char curr = s.charAt(i-1); // i번째 문자

            // 1자리 숫자 (1~9)
            if(curr >= '1' && curr <= '9') {
                dp[i] += dp[i-1];
                dp[i] %= MOD;
            }

            // 2자리 숫자 (10~26)
            int twoDigit = (prev - '0') * 10 + (curr - '0');
            if(twoDigit >= 10 && twoDigit <= 26) {
                dp[i] += dp[i-2];
                dp[i] %= MOD;
            }
        }

        System.out.println(dp[n]);
    }
}
