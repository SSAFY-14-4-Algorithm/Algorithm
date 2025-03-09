package week5;

import java.util.Scanner;

public class Baekjoon9663 {
    static int N;
    static int count = 0;
    static boolean[] colCheck;    // 같은 열(col)에 퀸이 있는지 체크
    static boolean[] diag1;       // '/' 방향 대각선 (r + c)
    static boolean[] diag2;       // '\' 방향 대각선 (r - c + (N-1))

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        colCheck = new boolean[N];
        diag1 = new boolean[2 * N - 1];
        diag2 = new boolean[2 * N - 1];

        dfs(0);  // 첫 번째 행(row = 0)부터 탐색 시작
        System.out.println(count);
        
        sc.close();
    }

    static void dfs(int row) {
        if (row == N) {  // N개의 퀸을 모두 배치한 경우
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (colCheck[col] || diag1[row + col] || diag2[row - col + (N - 1)]) {
                continue; // 충돌하면 스킵
            }

            // 퀸 배치
            colCheck[col] = true;
            diag1[row + col] = true;
            diag2[row - col + (N - 1)] = true;

            dfs(row + 1); // 다음 행으로 이동

            // 백트래킹 (이전 상태로 복구)
            colCheck[col] = false;
            diag1[row + col] = false;
            diag2[row - col + (N - 1)] = false;
        }
    }
}
