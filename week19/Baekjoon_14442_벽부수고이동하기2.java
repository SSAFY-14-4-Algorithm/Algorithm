/*
 * BOJ 14442번 : 벽 부수고 이동하기 2
 * 메모리 : 336,504kb
 * 시간 : 1,760ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_14442_벽부수고이동하기2 {
    static int N, M, K;
    static int[][] map;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };
    static boolean[][][] visited;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];
        visited = new boolean[N+1][M+1][K+1];
        for(int n = 1; n <= N; n++) {
            String line = br.readLine();
            for(int m = 1; m <= M; m++) {
                map[n][m] = line.charAt(m-1)-'0';
            }
        }

        bfs();

        if(answer == Integer.MAX_VALUE) {
            sb.append(-1);
        } else {
            sb.append(answer);
        }

        System.out.println(sb);
        br.close();
    }

    public static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] { 1, 1, 0 }); // x, y, 부순 벽 수
        visited[1][1][0] = true;
        int distance = 1;

        while(!q.isEmpty()) {
            int size = q.size();
            for(int s = 0; s < size; s++) {
                int[] curr = q.poll();

                if(curr[0] == N && curr[1] == M) {
                    answer = distance;
                    return;
                }

                for(int d = 0; d < 4; d++) {
                    int nx = curr[0]+dx[d];
                    int ny = curr[1]+dy[d];

                    if(nx < 1 || ny < 1 || nx > N || ny > M) continue;

                    if(map[nx][ny] == 0 && !visited[nx][ny][curr[2]]) {
                        visited[nx][ny][curr[2]] = true;
                        q.add(new int[] { nx, ny, curr[2] });
                    }

                    if(curr[2] < K && map[nx][ny] == 1 && !visited[nx][ny][curr[2]+1]){
                        visited[nx][ny][curr[2]+1] = true;
                        q.add(new int[] { nx, ny, curr[2]+1 });
                    }
                }
            }
            distance++;
        }
    }
}
