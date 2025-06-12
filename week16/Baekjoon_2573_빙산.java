import java.io.*;
import java.util.*;
/*
 * 메모리: 233304KB
 * 시간:	576ms
 */
public class Baekjoon_2573_빙산 {
    static int N, M;                       
    static int[][] A;                      
    static List<int[]> ice = new ArrayList<>();  // 남아 있는 빙산 칸(높이 > 0) 목록
    static int[] dx = {-1, 1, 0, 0}; 
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        A = new int[N][M];

        // 입력받으면서 빙산만 ice 리스트에 추가
        for (int i = 0; i < N; i++) {
            s = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(s[j]);
                if (A[i][j] > 0) {
                    ice.add(new int[]{i, j});
                }
            }
        }

        int year = 0;
        while (true) {
            // 빙산이 모두 녹았으면 0 출력 후 종료
            if (ice.isEmpty()) {
                System.out.println(0);
                return;
            }
            // 분리되었으면 현재 연도 출력 후 종료
            if (isSplit()) {
                System.out.println(year);
                return;
            }
            // 녹이기
            melt();
            year++;
        }
    }

    // 빙산이 두 덩어리 이상으로 분리되었는지 확인
    static boolean isSplit() {
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> q = new LinkedList<>();
        int[] start = ice.get(0);
        q.add(start);
        visited[start[0]][start[1]] = true;
        int cnt = 1;  // 연결된 칸 개수

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
                if (!visited[nx][ny] && A[nx][ny] > 0) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    cnt++;
                }
            }
        }
        // 연결된 칸 < 전체 칸이면 분리
        return cnt < ice.size();
    }

    // ice 리스트만 순회하며 1년치 빙산 녹이기
    static void melt() {
        List<int[]> nextIce = new ArrayList<>(ice.size());
        int[][] dec = new int[N][M];  // 각 칸이 녹아야하는 양 저장

        // 바다와 닿은 면만큼 녹임
        for (int[] p : ice) {
            int x = p[0], y = p[1], sea = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d], ny = y + dy[d];
                // 경계 내에서 바다인 경우 카운트
                if (A[nx][ny] == 0) sea++;
            }
            dec[x][y] = sea;
        }

        // 높이가 0 미만으로 내려가지 않도록 Math.max 사용
        for (int[] p : ice) {
            int x = p[0], y = p[1];
            A[x][y] = Math.max(0, A[x][y] - dec[x][y]);
            // 녹고 남은 칸만 nextIce에 추가
            if (A[x][y] > 0) {
                nextIce.add(new int[]{x, y});
            }
        }

        // ice 리스트를 갱신하여 다음 년도 준비
        ice = nextIce;
    }
}
