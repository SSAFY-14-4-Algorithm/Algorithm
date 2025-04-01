import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon14500 {
	static int N, M, answer;
    static int[] dx = { -1, 0, 1,  0 };
    static int[] dy = {  0, 1, 0, -1 };
    static int[][] map;
    static boolean[][] visited;

    
    //메모리 35260kb 시간 692ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                DFS(i, j, map[i][j], 0);
                visited[i][j] = false;
            }
        }

        System.out.println(answer);
    }
    
    static void DFS(int x, int y, int sum, int L) {
        if (L == 3) {
            answer = Math.max(answer, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextX > N - 1 || nextY < 0 || nextY > M - 1) continue;

            if (!visited[nextX][nextY]) {
                if (L == 1) {
                    visited[nextX][nextY] = true;
                    DFS(x, y, sum + map[nextX][nextY], L + 1);
                    visited[nextX][nextY] = false;
                }
                
                visited[nextX][nextY] = true;
                DFS(nextX, nextY, sum + map[nextX][nextY], L + 1);
                visited[nextX][nextY] = false;
            }
        }
    }
}