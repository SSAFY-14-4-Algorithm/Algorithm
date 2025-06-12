import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 
 * 134116kb	436ms
 * 
 * 빙산의 높이 정보는 2차원 배열로 저장
 * 빙산의 높이는 바닷물에 많이 접해있는 부분에서 빨리 줄어듬
 * 빙산의 높이는 4방향으로 붙어있는 0이 저장된 칸의 갯수만큼 줄어듬
 * 단, 0이 최소
 *
 * 한 덩어리의 빙산이 두 덩어리 이상으로 분리되는 시간을 구하라
 *
 * 1. 함수가 한 덩어리인지 판별하는 함수 -> bfs -> 100,000,000 -> 백만
 * 2. 4방 탐색 후, 0의 갯수만큼 높이 차감하는 함수 -> 100,000,000 -> 백만
 *
 * 3 <= N,M <= 300
 *
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {

            boolean isAllMelted = true;
            A : for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(map[i][j] > 0) {
                        isAllMelted = false;
                        break A;
                    }
                }
            }

            if(isAllMelted) {
                System.out.println(0);
                break;
            }

            A : for (int i = 0; i < N; i++) {
                    for (int j = 0; j < M; j++) {
                        if(map[i][j] > 0) {
                            visited = new boolean[N][M];
                            melt = new int[N][M];
                            visited[i][j] = true;
                            dfs(i,j);

                            if(!chk()) {
                                System.out.println(year);
                                return;
                            }
                            break A;
                        }
                    }
                }
            reduceLand();
            year++;
        }
    }

    static int N,M;
    static int[][] map;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static boolean[][] visited;
    static int[][] melt;

    //높이를 차감시키는 함수
    static void reduceLand() {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] > 0) map[i][j] -= melt[i][j];
            }
        }
    }


    //함수가 한 덩어리인지 판별하는 함수, true면 한 덩어리
    static void dfs(int r, int c) {
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

            if(map[nr][nc] <= 0) { //4방에 바닷물 갯수 새기
                melt[r][c]++;
            }
            else if(!visited[nr][nc]) {
                visited[nr][nc] = true;
                dfs(nr,nc);
            }
        }
    }

    static boolean chk() {
        int landCnt = 0;
        int foundedLandCnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] > 0) landCnt++;
                if(visited[i][j]) foundedLandCnt++;
            }
        }

        if(landCnt == foundedLandCnt) return true;
        return false;
    }
}

