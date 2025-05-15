import java.io.*;
import java.util.*;
/*
 * 메모리: 12912
 * 시간: 132
 * 
 * 좋은 수(두 수의 합으로 표현 되는 수)의 개수 구하기 
 * 투포인터 
 * 
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr); 

        int count = 0;
        for (int i = 0; i < n; i++) {
            int l = 0;
            int r = n - 1;
            while (l < r) {
                if (l == i) {
                    l++;
                    continue;
                }
                if (r == i) {
                    r—;
                    continue;
                }
                long sum = arr[l] + arr[r];
                if (sum == arr[i]) {
                    count++;
                    break; 
                } else if (sum < arr[i]) {
                    l++;
                } else {
                    r—;
                }
            }
        }

        System.out.println(count);
    }
}
