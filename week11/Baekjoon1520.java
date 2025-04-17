import java.io.*;
import java.util.*;
/*
 * 메모리: 38632KB
 * 시간: 272ms
 * 
 * dp,dfs 
 * 
 * 
 * 
 */

public class Baekjoon1520 {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dx = {0, 0, -1, 1}; 
    static int[] dy = {-1, 1, 0, 0}; 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        dp = new int[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        System.out.println(dfs(0, 0));

    }

    static int dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) { // 도착하면 1반환(경로+1)
            return 1;
        }
        
        if (dp[x][y] != -1) {
        	return dp[x][y];
        }
        
        dp[x][y] = 0; 
        for (int i = 0; i < 4; i++) {
            int nx = x + dy[i];
            int ny = y + dx[i];
            if (nx >=0  && nx <M && ny>=0 && ny< N && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny);
            }
        }
        return dp[x][y];
    }
}
