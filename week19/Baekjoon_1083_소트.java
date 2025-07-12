/*
 * BOJ 1083번 : 소트
 * 메모리 : 11596kb
 * 시간 : 68ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1083_소트 {
    static int N, S;
    static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++) {
            A[n] = Integer.parseInt(st.nextToken());
        }
        S =  Integer.parseInt(br.readLine());

        // 정렬
        for(int i = 0; i < N && S > 0; i++) {
            // 최대 교환 가능 범위 내에서 가장 큰 값 찾기
            int maxIdx = i;
            for(int j = i+1; j < N && j <= i+S; j++) {
                if(A[maxIdx] < A[j]) {
                    maxIdx = j; // 가장 큰 수의 인덱스
                }
            }

            // 큰 수를 앞으로 땡겨오는데 필요한 swap 횟수만큼 빼기
            S -= (maxIdx-i);

            // 가장 큰 값이 현재 위치에 오도록 swap
            // 오른쪽에서 왼쪽으로 swap
            for(int k = maxIdx; k > i; k--) {
                swap(A, k, k-1);
            }
        }

        for(int n = 0; n < N; n++) {
            sb.append(A[n]).append(" ");
        }

        System.out.println(sb);
        br.close();
    }

    public static void swap(int[] A, int x, int y) {
        int temp = A[x];
        A[x] = A[y];
        A[y] = temp;
    }
}
