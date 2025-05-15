import java.io.*;
import java.util.*;

public class Baekjoon2638_치즈 {
    static int N, M;
    static int[][] grid;
    // 상하좌우
    static final int[] dr = {-1,1,0,0};
    static final int[] dc = {0,0,-1,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] parts = br.readLine().split(" ");
        N = Integer.parseInt(parts[0]);
        M = Integer.parseInt(parts[1]);

        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            parts = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(parts[j]);
            }
        }

        int hours = 0;
        while (true) {
            boolean[][] outside = new boolean[N][M];
            int[][] touchCount = new int[N][M];
            // BFS 로 외부 공기 영역 표시 + 치즈 접촉 횟수 누적
            Queue<int[]> q = new ArrayDeque<>();
            outside[0][0] = true;
            q.add(new int[]{0,0});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0], c = cur[1];
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr<0||nr>=N||nc<0||nc>=M) continue;
                    if (outside[nr][nc]) continue;
                    if (grid[nr][nc] == 0) {
                        // 빈 칸이면 외부 공기로 확장
                        outside[nr][nc] = true;
                        q.add(new int[]{nr,nc});
                    } else {
                        // 치즈 칸이면 외부와의 접촉 횟수 증가
                        touchCount[nr][nc]++;
                    }
                }
            }

            // 녹일 칸 리스트
            List<int[]> melt = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (grid[i][j] == 1 && touchCount[i][j] >= 2) {
                        melt.add(new int[]{i,j});
                    }
                }
            }
            if (melt.isEmpty()) break;

            // 녹이기
            for (int[] cell : melt) {
                grid[cell[0]][cell[1]] = 0;
            }
            hours++;
        }

        System.out.println(hours);
    }
}
