import java.io.*;
import java.util.*;
/*
 * 메모리:345312KB
 * 시간:	2548ms
 */
public class Baekjoon_14442_벽부수고이동하기2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static boolean[][][] visited;
    static int n;
    static int m;
    static int K;               
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        n = Integer.parseInt(s[0]);
        m = Integer.parseInt(s[1]);
        K = Integer.parseInt(s[2]);         

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] c = br.readLine().split("");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(c[j]);
            }
        }
        visited = new boolean[n][m][K + 1];
        int result = bfs(0, 0);
        System.out.println(result);
    }

    public static boolean isRange(int x, int y) {
        return x>=0 && y>=0 && x<n && y<m;
    }

    public static int bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x, y, 0, 1});
        visited[x][y][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], broken = cur[2], dist = cur[3];

            if (r == n-1 && c == m-1) {
                return dist;
            }

            for (int i=0; i<4; i++) {
                int nr = r + dx[i], nc = c + dy[i];
                if (!isRange(nr, nc)) continue;

                // 빈 칸 이동
                if (map[nr][nc] == 0 && !visited[nr][nc][broken]) {
                    visited[nr][nc][broken] = true;
                    q.offer(new int[] { nr, nc, broken, dist + 1 });
                }
                // 벽이고, 아직 부술 여력이 있으면
                if (map[nr][nc] == 1 && broken < K && !visited[nr][nc][broken + 1]) {
                    visited[nr][nc][broken + 1] = true;
                    q.offer(new int[] { nr, nc, broken + 1, dist + 1 });
                }
            }
        }
        return -1;
    }
}
