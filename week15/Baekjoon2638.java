import java.io.*;
import java.util.*;
/*
 * 메모리: 28704
 * 시간: 208
 * 
 * 모든 치즈가 녹는데 걸리는 시간 
 * bfs
 * 
 */


public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int time = 0;
        while (true) {
            visited = new boolean[N][M];
            outsideAir();
            List<int[]> toMelt = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1 && melt(i, j)) {
                        toMelt.add(new int[]{i, j});
                    }
                }
            }
            if (toMelt.isEmpty()) {
                System.out.println(time);
                return;
            }
            for (int[] pos : toMelt) {
                map[pos[0]][pos[1]] = 0;
            }
            time++;
        }
    }
    static void outsideAir() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            int x = p[0];
            int y = p[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
    static boolean melt(int x, int y) {
        int airCnt = 0;
        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (visited[nx][ny] && map[nx][ny] == 0) {
                    airCnt++;
                }
            }
        }
        return airCnt >= 2;
    }
}
