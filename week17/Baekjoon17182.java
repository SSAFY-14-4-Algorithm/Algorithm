import java.io.*;
import java.util.*;

/**
 * 모든 행성을 탐사하는데 걸리는 최소 시간
 * 입력은 행성 간 이동하는 데 걸리는 시간을 2차원 행렬
 *
 * arr[i][j] = i번째 행성에서 j 번째 행성에 도달하는 데 걸리는 시간
 *
 * 입력
 * 행성의 갯수 N
 * ana 호가 발사되는 행성의 위치 K
 *
 * 탐사 후 다시 시작행성으로 돌아올 필요는 없음, 이미 방문한 행성도 중복해서 방문 가능
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][N];
        dp = new int[N][N];
        permutation = new int[N];
        visited = new boolean[N];
        minTime=Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = arr[i][j];
            }
        }

        //j->i->k 로 경유지를 거쳐가는 최단 경로 저장
        for (int i = 0; i < N; i++) { //경유지
            for (int j = 0; j < N; j++) { //출발지
                for (int k = 0; k < N; k++) { //도착지
                    dp[j][k] = Math.min(dp[j][k], dp[j][i] + dp[i][k]);
                }
            }
        }
        permutation[0] = K;
        visited[K] = true; //시작점 고정
        dfs(1);
        System.out.print(minTime);
    }

    static int N,K,minTime;
    static int[][] dp;
    static int[] permutation;
    static boolean[] visited;


    //idx는 뽑을 인덱스
    static void dfs(int idx) {

        if (idx==N) {
            int time = 0;
            for (int i = 1; i < N; i++) {
                time += dp[permutation[i-1]][permutation[i]];
            }
            minTime = Math.min(minTime,time);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            permutation[idx] = i;
            dfs(idx+1);
            visited[i] = false;
        }
    }
}
