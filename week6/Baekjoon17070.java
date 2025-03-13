package week6;

/**
 * <h1>BAEKJOON 17070번 파이프 옮기기</h1>
 * <p>
 * JAVA 8 : 메모리 KB, 시간 ms <br>
 * JAVA11 : 메모리 207,776KB, 시간 476ms
 * </p>
 * 
 * @author KIM MINGYU jun3021303@gmail.com
 * @since 2025-03-12
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

public class Baekjoon17070 {
    private static boolean[][] walls;
    private static int N;
    private static Deque<int[]> queue = new ArrayDeque<>();
    private static int count = 0;

    public static void main(String[] args) throws IOException {
        N = readInt();
        walls = new boolean[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                walls[i][j] = readInt() == 1;

        queue.add(new int[] { 0, 0, 1 });
        if (!(walls[N - 1][N - 1] || (walls[N - 2][N - 2] && walls[N - 2][N - 1] && walls[N - 1][N - 2])))
            bfs();
        System.out.println(count);
    }

    public static void bfs() {
        while (!queue.isEmpty()) {
            int[] pipe = queue.poll();
            int direction = pipe[0];
            int X = pipe[1];
            int Y = pipe[2];
            // System.out.println(direction + " | " + X + " " + Y);
            if ((X == N - 1) && (Y == N - 1)) {
                count++;
                continue;
            }
            // !가로
            if (direction == 0) {
                direction0(X, Y);
            }
            // !대각선
            if (direction == 1) {
                direction0(X, Y); // 가로
                direction2(X, Y); // 세로
            }
            // !세로
            if (direction == 2) {
                direction2(X, Y);
            }
            direction1(X, Y); // 대각선
        }
    }

    // !가로
    public static void direction0(int X, int Y) {
        if (Y + 1 < N) {
            if (!walls[X][Y + 1]) {
                queue.add(new int[] { 0, X, Y + 1 });
            }
        }
    }

    // !대각선
    public static void direction1(int X, int Y) {
        if ((X + 1 < N) && (Y + 1 < N)) {
            if (!walls[X + 1][Y] && !walls[X][Y + 1] && !walls[X + 1][Y + 1]) {
                queue.add(new int[] { 1, X + 1, Y + 1 });
            }
        }
    }

    // !세로
    public static void direction2(int X, int Y) {
        if (X + 1 < N) {
            if (!walls[X + 1][Y]) {
                queue.add(new int[] { 2, X + 1, Y });
            }
        }
    }

    private static int readInt() throws IOException {
        int c;
        int n = System.in.read() & 15;
        while ((c = System.in.read()) >= 48)
            n = (n * 10) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}