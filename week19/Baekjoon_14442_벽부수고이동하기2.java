import java.io.*;
import java.util.*;


/**
 * 시간: 298760ms
 * 메모리: 692KB
 */
public class Baekjoon_14442_벽부수고이동하기2 {
	// IO
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    // types
    // constants
    static final int[] dy = new int[]{0, 0, 1, -1};
    static final int[] dx = new int[]{1, -1, 0, 0};
    // variables
    static int N, M, K;
    static char[][] mat;
    static int[][] isVisited;

    static int solution(){
        Deque<int[]> q = new ArrayDeque<>();
        isVisited[0][0] = 0;
        q.offerLast(new int[]{0, 0, 1});

        while(!q.isEmpty()){
            int[] pos = q.pollFirst();
            int y = pos[1], x = pos[0], dist = pos[2];

            for(int d = 0; d < dy.length; ++d){
                int ny = pos[1] + dy[d];
                int nx = pos[0] + dx[d];

                if(0 <= ny && ny < N && 0 <= nx && nx < M) {
                    if (mat[ny][nx] == '1') {
                        // 이미 K번 벽을 부쉈다면 진행 불가
                        if(isVisited[y][x] >= K) continue;

                        // 이미 다른 노드에서 이 노드를 방문하였다면, 그 노드가 더 빠를 것이다.
                        // 하지만, 새로 방문하려는 노드가 벽을 부순 횟수가 앞선 노드 경로보다 더 적다면,
                        // 같거나 빠른 경로가 존재하므로 이때에만 재방문 한다.
                        if (isVisited[ny][nx] > isVisited[y][x] + 1) {
                            isVisited[ny][nx] = isVisited[y][x] + 1;
                            q.offerLast(new int[]{nx, ny, dist + 1});
                        }
                    } else {
                        if (isVisited[ny][nx] > isVisited[y][x]) {
                            isVisited[ny][nx] = isVisited[y][x];
                            q.offerLast(new int[]{nx, ny, dist + 1});

                            if(ny == N - 1 && nx == M - 1) return dist + 1;
                        }
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        if(N == 1 && M == 1) { 
            System.out.print(1);
            return;
        }
        K = readInt();

        mat = new char[N][M];
        for(int i = 0; i < N; ++i) {
            for(int j = 0; j < M; ++j) {
                mat[i][j] = (char)System.in.read();
            }
            System.in.read();
        }

        isVisited = new int[N][M];
        for(int i = 0; i < N; ++i) Arrays.fill(isVisited[i], Integer.MAX_VALUE);

        System.out.print(solution());
    }

    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) > 0x20) n = (n << 3) + (n << 1) + (c & 0x0F);
        if(c == '\r') System.in.read();
        return n;
    }
}