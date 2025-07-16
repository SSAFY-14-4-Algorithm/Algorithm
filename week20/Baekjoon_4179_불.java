/*
 * BOJ 4179번 : 불
 * 메모리 : 55,572kb
 * 시간 : 436ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_4179_불 {
    static int R, C;
    static char[][] map;
    static int[][] fire;
    static int[][] jihun;
    static boolean[][] fireVisited;
    static boolean[][] jihunVisited;
    static int minTime = Integer.MAX_VALUE;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { 1, -1, 0, 0 };
    static Queue<int[]> fireQueue = new ArrayDeque<>();
    static Queue<int[]> jihunQueue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        fire = new int[R][C];
        jihun = new int[R][C];
        fireVisited = new boolean[R][C];
        jihunVisited = new boolean[R][C];
        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);

                if(map[r][c] == 'F') {
                    fireQueue.offer(new int[] { r, c });
                    fireVisited[r][c] = true;
                }

                if(map[r][c] == 'J') {
                    jihunQueue.offer(new int[] { r, c });
                    jihunVisited[r][c] = true;
                }
            }
        }

        fireBfs();
        jihunBfs();

        if(minTime == Integer.MAX_VALUE) {
            sb.append("IMPOSSIBLE");
        } else {
            sb.append(minTime);
        }
        System.out.println(sb);
        br.close();
    }

    public static void fireBfs() {
        while(!fireQueue.isEmpty()) {
            int[] curr = fireQueue.poll();

            for(int d = 0; d < 4; d++) {
                int nr = curr[0] + dx[d];
                int nc = curr[1] + dy[d];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '#' || fireVisited[nr][nc]) continue;

                fireVisited[nr][nc] = true;
                fire[nr][nc] = fire[curr[0]][curr[1]]+1;
                fireQueue.offer(new int[] { nr, nc });
            }
        }
    }

    public static void jihunBfs() {
        while(!jihunQueue.isEmpty()) {
            int[] curr = jihunQueue.poll();

            if(curr[0] == 0 || curr[1] == 0 || curr[0] == R-1 || curr[1] == C-1) {
                minTime = Math.min(minTime, jihun[curr[0]][curr[1]]+1);
                return;
            }

            for(int d = 0; d < 4; d++) {
                int nr = curr[0] + dx[d];
                int nc = curr[1] + dy[d];

                if(nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '#' || jihunVisited[nr][nc]) continue;

                // 불이 이미 온 경우 막기
                if(fire[nr][nc] <= jihun[curr[0]][curr[1]]+1 && fireVisited[nr][nc]) continue;

                jihunVisited[nr][nc] = true;
                jihun[nr][nc] = jihun[curr[0]][curr[1]]+1;
                jihunQueue.offer(new int[] { nr, nc });
            }
        }
    }
}
