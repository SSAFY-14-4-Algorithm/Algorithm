package week12;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Baekjoon2589 {
    private static int N, M, bfsCount = 1;
    private static boolean[][] isLand;
    private static int[][] visited;
    private static int[] dx = { -1, 0, 1, 0 };
    private static int[] dy = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        isLand = new boolean[N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                isLand[x][y] = System.in.read() == 'L';
            }
            // System.in.read();
            System.in.read();
        }

        visited = new int[N][M];
        List<int[]> starts = new ArrayList<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (!isLand[x][y])
                    continue;
                for (int k = 0; k < 4; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || !isLand[nx][ny]) {
                        starts.add(new int[] { x, y });
                        break;
                    }
                }
            }
        }

        int answer = 0;

        for (int[] st : starts) {
            answer = Math.max(answer, bfs(st[0], st[1]));
            bfsCount++;
        }
        System.out.println(answer);
    }

    private static int bfs(int cx, int cy) {
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { cx, cy, 0 });
        visited[cx][cy] = bfsCount;
        int maxDistance = 0;
        while (!q.isEmpty()) {
            int[] cur = q.pollFirst();
            int x = cur[0], y = cur[1], d = cur[2];
            maxDistance = Math.max(maxDistance, d);
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (visited[nx][ny] == bfsCount || !isLand[nx][ny])
                    continue;
                visited[nx][ny] = bfsCount;
                q.add(new int[] { nx, ny, d + 1 });
            }
        }
        return maxDistance;
    }

    private static int readInt() throws IOException {
        int c = System.in.read(), n = 0;
        while (c <= ' ')
            c = System.in.read();
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
