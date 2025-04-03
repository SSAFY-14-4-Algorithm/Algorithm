
import java.io.*;
import java.util.*;
/*
 * 메모리: 36456KB
 * 시간: 580ms
 * 
 * dfs로 ㅗ 모양(선형이 아닌 것)을 처리할 수 없음
 * dfs+완
 * 
 */

public class BaekJoon14500 {
    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int maxSum = 0;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, board[i][j], 1);
                visited[i][j] = false;
                checkTShape(i, j); // 'ㅗ' 모양 처리
            }
        }
        System.out.print(maxSum);
    
    }
    
    static void dfs(int x, int y, int sum, int depth) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny]) continue;
            visited[nx][ny] = true;
            dfs(nx, ny, sum + board[nx][ny], depth + 1);
            visited[nx][ny] = false;
        }
    }
    
    static void checkTShape(int x, int y) {
        int total = board[x][y];
        int wings = 0;
        int minWing = Integer.MAX_VALUE;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            total += board[nx][ny];
            minWing = Math.min(minWing, board[nx][ny]);
            wings++;
        }

        if (wings < 3) return;
        if (wings == 4) total -= minWing;

        maxSum = Math.max(maxSum, total);
    }
    
}
