package day0320;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
// 메모리 18604KB 실행시간 208ms
public class P11053 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] list = new int[n];
        String[] input = br.readLine().split(" ");
        for (int i = 0 ; i < n ; i ++ ) {
            list[i] = Integer.parseInt(input[i]);
        }

        int[] dp = new int[n];

        dp[0] = 1;
        for (int i = 1 ; i < n ; i ++ ) {
            int num = 0;
            for (int j = 0 ; j < i ; j ++ ) {
                if (list[j] < list[i]) {
                    num = Math.max(num, dp[j] + 1);
                }
                else {
                    num = Math.max(num, 1);
                }
            }
            dp[i] = num ;
        }

        System.out.println(Arrays.stream(dp).max().orElse(0));
    }

}

