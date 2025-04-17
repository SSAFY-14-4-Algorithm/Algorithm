import java.io.*;
import java.util.*;
/*
 * 메모리: 31608KB
 * 시간: 228ms
 * 
 * 투포인터, 정렬된 배열 
 * 
 * 
 */
public class Baekjoon2467 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        int l = 0;
        int r = N - 1;
        int minSum = Integer.MAX_VALUE;
        int ansL = 0, ansR = 0;

        while (l < r) {
            int sum = arr[l] + arr[r];
            int absSum = Math.abs(sum);

            if (absSum < minSum) {
                minSum = absSum;
                ansL = arr[l];
                ansR = arr[r];
            }

            if (sum > 0) {
                r—;
            } else {
                l++;
            }
        }

        System.out.println(ansL + " " + ansR);
    }
}
