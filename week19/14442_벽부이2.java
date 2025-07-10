import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/**
 *
 * 	338292	1324ms
 * 0은 이동 가능, 1은 벽
 * 최단경로로 N,M 까지 이동
 * 벽을 부수고 이동 가능
 *
 * 주의점
 * 각 칸에 대해서 벽을 부수고 온 경우와, 벽을 부수지 않고 온 경우를 관리 해야 함
 *
 * + 벽을 몇개 부쉈는지도 중요
 *
 * visited[i][j][k] 로 i,j 에 K개의 벽을 부순 노드의 방문 여부를 체크
 *
 * 주의점
 * 큐에 offer 할 때, 현재 부숴진 상태로 넣는 노드가 이미 방문했는지 한번 더 체크해야함 or 시간초과
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K+1];

        for (int i = 0; i < N; i++) {
            String input = reader.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j)-'0';
            }
        }
        System.out.println(bfs());
    }

    static int[][] map;
    static boolean[][][] visited;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int N,M,K;

    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0,0,1,0));
        visited[0][0][0] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if(node.r == N-1 && node.c == M-1) return node.distance;

            for (int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];

                if(nr < 0 || nc < 0 || nr >= N || nc >= M) continue;

                if(map[nr][nc] == 1 && node.brokenWall < K && !visited[nr][nc][node.brokenWall+1]) {
                    visited[nr][nc][node.brokenWall+1] = true;
                    q.offer(new Node(nr, nc, node.distance+1,node.brokenWall + 1));
                }
                else if(map[nr][nc] == 0 && !visited[nr][nc][node.brokenWall]) {
                    visited[nr][nc][node.brokenWall] = true;
                    q.offer(new Node(nr,nc, node.distance+1,node.brokenWall));
                }
            }
        }
        return -1;
    }

    static class Node {
        int r;
        int c;
        int distance;
        int brokenWall;

        public Node(int r, int c, int distance, int brokenWall) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.brokenWall = brokenWall;
        }

    }
}