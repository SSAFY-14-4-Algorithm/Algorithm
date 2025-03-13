package week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//메모리 152124KB 실행시간 444ms
public class Baekjoon17070 {

    static int n;
    static int[][] li;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        li = new int[n][n];

        for (int i = 0; i < n; i++) {
            String[] tmp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                li[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        checkNum(1, 0, 1);
        System.out.println(answer);
    }

    public static void checkNum(int mode, int x, int y) {
        if (x == n - 1 && y == n - 1) {
            answer++;
            return;
        }

        int[] dxMode3 = {0, 1, 1};
        int[] dyMode3 = {1, 1, 0};

        if (mode == 1) {
            if (y + 1 < n && li[x][y + 1] == 0) {
                checkNum(1, x, y + 1);
            }

            if (check(x, y, dxMode3, dyMode3)) {
                checkNum(3, x + 1, y + 1);
            }
        } else if (mode == 2) {
            if (x + 1 < n && li[x + 1][y] == 0) {
                checkNum(2, x + 1, y);
            }

            if (check(x, y, dxMode3, dyMode3)) {
                checkNum(3, x + 1, y + 1);
            }
        } else if (mode == 3) {
            if (x + 1 < n && li[x + 1][y] == 0) {
                checkNum(2, x + 1, y);
            }

            if (y + 1 < n && li[x][y + 1] == 0) {
                checkNum(1, x, y + 1);
            }

            if (check(x, y, dxMode3, dyMode3)) {
                checkNum(3, x + 1, y + 1);
            }
        }
    }

    private static boolean check(int x, int y, int[] dx, int[] dy) {
        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= n || ny >= n || li[nx][ny] == 1) {
                return false;
            }
        }
        return true;
    }
}

