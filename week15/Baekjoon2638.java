package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon2638 {
    private static Deque<int[]> cheeseQueue;
    private static Deque<int[]> toBeAir;
    private static int[] moveX = { 1, -1, 0, 0 };
    private static int[] moveY = { 0, 0, -1, 1 };
    private static int[][] airNothingCheese;
    private static int N;
    private static int M;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        airNothingCheese = new int[N][M];
        cheeseQueue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    airNothingCheese[i][j] = 1;
                    cheeseQueue.add(new int[] { i, j });
                }
            }
        }
        findAir();
        findTime();
        System.out.println(time);
    }

    public static void findTime() {
        Deque<int[]> temp;
        toBeAir = new ArrayDeque<>();
        while (!cheeseQueue.isEmpty()) {
            time++;
            temp = new ArrayDeque<>();
            while (!cheeseQueue.isEmpty()) {
                int[] cheese = cheeseQueue.poll();
                if (searchNearAir(cheese[0], cheese[1]) >= 2)
                    toBeAir.add(cheese);
                else
                    temp.add(cheese);
            }
            if (!toBeAir.isEmpty())
                beAir();
            if (!temp.isEmpty())
                cheeseQueue = temp;
        }
    }

    public static void beAir() {
        boolean[][] visited = new boolean[N][M];
        int[] pos = toBeAir.peek();
        visited[pos[0]][pos[1]] = true;
        while (!toBeAir.isEmpty()) {
            pos = toBeAir.poll();
            airNothingCheese[pos[0]][pos[1]] = -1;
            for (int i = 0; i < 4; i++) {
                int nextX = pos[0] + moveX[i];
                int nextY = pos[1] + moveY[i];
                if (0 <= nextX && 0 <= nextY && nextX < N && nextY < M) {
                    if (!visited[nextX][nextY] && airNothingCheese[nextX][nextY] == 0) {
                        visited[nextX][nextY] = true;
                        toBeAir.add(new int[] { nextX, nextY });
                    }
                }
            }
        }
    }

    public static int searchNearAir(int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + moveX[i];
            int nextY = y + moveY[i];
            if (0 <= nextX && 0 <= nextY && nextX < N && nextY < M) {
                if (airNothingCheese[nextX][nextY] == -1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void findAir() {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        q.add(new int[] { 0, 0 });
        while (!q.isEmpty()) {
            int[] pos = q.poll();
            airNothingCheese[pos[0]][pos[1]] = -1;
            for (int i = 0; i < 4; i++) {
                int nextX = pos[0] + moveX[i];
                int nextY = pos[1] + moveY[i];
                if (0 <= nextX && 0 <= nextY && nextX < N && nextY < M) {
                    if (!visited[nextX][nextY] && airNothingCheese[nextX][nextY] != 1) {
                        visited[nextX][nextY] = true;
                        q.add(new int[] { nextX, nextY });
                    }
                }
            }
        }
    }
}