/*
 * BOJ 2812번 : 크게 만들기
 * 메모리 : 19,640kb
 * 시간 : 176ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_2812_크게만들기 {
    static int N, K;
    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new char[N];

        String str = br.readLine();
        int top = -1;
        int count = K;

        for(int n = 0; n < N; n++) {
            char curr = str.charAt(n);
            while(top >= 0 && count > 0 && arr[top] < curr) {
                top--;
                count--;
            }
            arr[++top] = curr;
        }

        for(int i = 0; i <= top-count; i++) {
            sb.append(arr[i]);
        }

        System.out.println(sb);
        br.close();
    }
}
