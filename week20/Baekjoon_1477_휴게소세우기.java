/*
 * BOJ 1477번 : 휴게소 세우기
 * 메모리 : 11,624kb
 * 시간 : 80ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1477_휴게소세우기 {
    static int N, M, L;
    static int[] arr;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N; n++) {
            arr[n] =  Integer.parseInt(st.nextToken());
        }
        arr[N+1] = L;

        Arrays.sort(arr);

        int left = 1;
        int right = L;
        while(left <= right) {
            int mid = (left+right)/2;
            int count = 0;

            for(int n = 1; n <= N+1; n++){
                int distance = arr[n] - arr[n-1];
                count += (distance-1)/mid;
            }

            if(count > M) {
                left = mid+1;
            } else {
                answer = mid;
                right = mid-1;
            }
        }

        sb.append(answer);
        System.out.println(sb);
        br.close();
    }
}
