/*
 * BOJ 20159번 : 동작 그만. 밑장 빼기냐?
 * 메모리 : 23,040kb
 * 시간 : 212ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_20159_동작그만밑장빼기냐 {
    static int N;
    static int[] player1;
    static int[] player2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        player1 = new int[N/2+1];
        player2 = new int[N/2+1];

        // 카드 입력 누적함
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int n = 1; n <= N/2; n++) {
            player1[n] = player1[n-1] + Integer.parseInt(st.nextToken());
            player2[n] = player2[n-1] + Integer.parseInt(st.nextToken());
        }

        int answer = player1[N/2];
        int sum = 0;
        for(int n = 0; n < N; n++) {
            // 정훈이 차례에서 밑장 빼기
            if (n % 2 == 0) {
                sum = player1[n / 2] + (player2[N / 2] - player2[n / 2]);
            }
            // 상대방 차례에서 밑장 빼기
            else {
                sum = player1[n / 2 + 1] + (player2[N / 2 - 1] - player2[n / 2]);
            }
            answer = Math.max(answer, sum);
        }

        sb.append(answer);
        System.out.print(sb);
        br.close();
    }
}
