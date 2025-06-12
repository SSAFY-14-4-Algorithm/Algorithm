package week16;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Baekjoon2573 {
    private static int N, M;
    private static int[][] height;
    private static final int[] dx = { 1, -1, 0, 0 };
    private static final int[] dy = { 0, 0, 1, -1 };
    private static boolean[][] isBoundary;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        height = new int[N][M];
        isBoundary = new boolean[N][M];
        visited = new boolean[N][M];
        List<int[]> iceList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                height[i][j] = readInt();
                if (height[i][j] > 0) {
                    iceList.add(new int[] { i, j });
                }
            }
        }
        int year = 0;
        while (true) {
            year++;
            // ! 경계부분 얼음만 구하기
            List<int[]> toMeltIce = new ArrayList<>(iceList.size());
            for (int[] ice : iceList) {
                int x = ice[0];
                int y = ice[1];
                int nearWaterCount = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    if (height[nx][ny] == 0) {
                        nearWaterCount++;
                    }
                }
                if (nearWaterCount > 0) {
                    isBoundary[x][y] = true;
                    toMeltIce.add(new int[] { x, y, nearWaterCount });
                }
            }

            List<int[]> nextIce = new ArrayList<>();
            // ! 경계부분 녹이기
            for (int[] ice : toMeltIce) {
                int x = ice[0];
                int y = ice[1];
                int nearWaterCount = ice[2];
                height[x][y] = Math.max(height[x][y] - nearWaterCount, 0);
                if (height[x][y] > 0) {
                    nextIce.add(new int[] { x, y });
                }
            }
            // ! 안쪽부분 그대로
            for (int[] cell : iceList) {
                int x = cell[0];
                int y = cell[1];
                if (!isBoundary[x][y]) {
                    nextIce.add(new int[] { x, y });
                }
            }
            // ! 다 녹았음; print 0
            if (nextIce.isEmpty()) {
                year = 0;
                break;
            }
            // ! cc 검사
            if (countCC(nextIce) >= 2) {
                break;
            }
            // ! 초기화
            for (int[] ice : toMeltIce) {
                isBoundary[ice[0]][ice[1]] = false;
            }
            iceList = nextIce;
        }
        System.out.println(year);
    }

    private static int countCC(List<int[]> iceList) {
        List<int[]> visitedIce = new ArrayList<>(iceList.size());
        int count = 0;
        for (int[] cell : iceList) {
            int x = cell[0];
            int y = cell[1];
            if (!visited[x][y]) {
                count++;
                if (count == 2) {
                    return 2;
                }
                bfs(x, y, visitedIce);
            }
        }
        clearVisited(visitedIce);
        return count;
    }

    private static void bfs(int x, int y, List<int[]> visitedIce) {
        Deque<int[]> q = new ArrayDeque<>();
        visited[x][y] = true;
        visitedIce.add(new int[] { x, y });
        q.add(new int[] { x, y });
        while (!q.isEmpty()) {
            int[] n = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = n[0] + dx[d];
                int ny = n[1] + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;
                if (height[nx][ny] > 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    visitedIce.add(new int[] { nx, ny });
                    q.add(new int[] { nx, ny });
                }
            }
        }
    }

    private static void clearVisited(List<int[]> visitedIce) {
        for (int[] ice : visitedIce) {
            visited[ice[0]][ice[1]] = false;
        }
        visitedIce.clear();
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
