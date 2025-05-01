package week13;

import java.io.*;
import java.util.*;

/**
 *  인접비교
 * 
 *  메모리 : 15764
 *  시간 : 184
 */

public class Baekjoon5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];

            for (int i = 0; i < N; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            boolean isConsistent = true;

            for (int i = 0; i < N - 1; i++) {
                if (arr[i + 1].startsWith(arr[i])) {
                    isConsistent = false;
                    break;
                }
            }

            sb.append(isConsistent ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }
}