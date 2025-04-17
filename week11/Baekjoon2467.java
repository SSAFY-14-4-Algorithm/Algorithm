package week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] input = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        int l = 0;
        int r = N - 1;
        int minL = -1;
        int minR = -1;
        long min = Long.MAX_VALUE;
        while (l < r) {
            long sum = input[l] + input[r];
            if (min > Math.abs(sum)) {
                min = Math.abs(sum);
                minL = l;
                minR = r;
            }
            if (sum >= 0) {
                r--;
            } else {
                l++;
            }
        }
        System.out.println(new StringBuilder().append(input[minL]).append(' ').append(input[minR]).toString());
    }
}