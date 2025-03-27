package week8;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Baekjoon16234 {
    private static int N, L, R;
    private static int[][] map;
    private static boolean[][] visited;
    private static int days = 0;
    private static final int[] dx = { -1, 1, 0, 0 };
    private static final int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        N = readInt();
        L = readInt();
        R = readInt();
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = readInt();
            }
        }
        boolean moved = true;
        while (moved) {
            moved = false;
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && bfs(i, j) > 1) {
                        moved = true;
                    }
                }
            }
            if (moved) {
                days++;
            }
        }
        System.out.println(days);
    }

    private static int bfs(int x, int y) {
        int currentIndex = 0;
        int sum = 0;
        List<int[]> list = new ArrayList<>();
        list.add(new int[] { x, y });
        visited[x][y] = true;
        sum += map[x][y];
        while (currentIndex < list.size()) {
            int cx = list.get(currentIndex)[0];
            int cy = list.get(currentIndex)[1];
            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]
                        && Math.abs(map[cx][cy] - map[nx][ny]) >= L && Math.abs(map[cx][cy] - map[nx][ny]) <= R) {
                    visited[nx][ny] = true;
                    sum += map[nx][ny];
                    list.add(new int[] { nx, ny });
                }
            }
            currentIndex++;
        }
        if (list.size() > 1) {
            int average = sum / list.size();
            for (int[] is : list) {
                map[is[0]][is[1]] = average;
            }
        }
        return list.size();
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
