import java.io.*;
import java.util.*;

/**
 * 194916kb, 560ms
 */
public class Baekjoon2573 {
	static int N, M;
    static int[][] grid;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        grid = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            int pieceCount = countIcebergPieces();

            // 덩어리가 2개 이상으로 분리된 경우
            if (pieceCount >= 2) {
                System.out.println(year);
                break;
            }
            // 덩어리가 1개도 없는 경우 (다 녹은 경우)
            else if (pieceCount == 0) {
                System.out.println(0);
                break;
            }

            // 1년이 흐르고 빙산이 녹음
            meltIceberg();
            year++;
        }
    }

    // 빙산 덩어리의 개수를 세는 함수
    static int countIcebergPieces() {
        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] > 0 && !visited[i][j]) {
                    count++;
                    bfs(i, j);
                }
            }
        }
        return count;
    }

    //연결된 덩어리를 찾음
    static void bfs(int startX, int startY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (grid[nx][ny] > 0 && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    // 빙산을 녹이는 함수
    static void meltIceberg() {
        int[][] meltAmount = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] > 0) {
                    int waterCount = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        // 경계 체크는 필요하지만, 문제 조건상 가장자리는 항상 0이므로
                        // 그리드 범위 내에서만 0인지 확인하면 됨
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && grid[nx][ny] == 0) {
                            waterCount++;
                        }
                    }
                    meltAmount[i][j] = waterCount;
                }
            }
        }

        // 계산된 양만큼 한 번에 녹임
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] -= meltAmount[i][j];
                if (grid[i][j] < 0) {
                    grid[i][j] = 0;
                }
            }
        }
    }
}