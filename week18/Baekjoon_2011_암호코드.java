/*
 * BOJ 2011번 : 암호코드
 * 메모리 : 12,600kb
 * 시간 : 68ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_2011_암호코드 {
    static String password;
    static int count;
    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        password = br.readLine();
        memo = new int[password.length()];
        Arrays.fill(memo, -1);

        if(password.charAt(0) == '0') {
            sb.append(0);
            System.out.print(sb);
            br.close();
            return;
        }

        count = dfs(0);
        sb.append(count);
        System.out.print(sb);
        br.close();
    }

    public static int dfs(int index) {
        if(index == password.length()) {
            return 1;
        }

        if(memo[index] != -1) {
            return memo[index];
        }

        int count = 0;

        // 1. 한자리 숫자 암호
        int one = password.charAt(index) - '0';
        if(one >= 1 && one <= 9) {
            count += dfs(index+1);
            count %= 1000000;
        }

        // 2. 두자리 숫자 암호
        if(index+1 < password.length()) {
            int two = Integer.parseInt(password.substring(index, index+2));
            if(two >= 10 && two <= 26) {
                count += dfs(index+2);
                count %= 1000000;
            }
        }

        memo[index] = count;
        return count;
    }
}
