import java.io.*;
import java.util.*;

public class Baekjoon2206 {
    static int N, M;
    static int[][] map;
    static int[][][] visited; // [x][y][벽을 부쉈는지 여부]
    static int[] dx = {-1, 0, 1, 0}; // 상, 우, 하, 좌
    static int[] dy = {0, 1, 0, -1};
    
    static class Point {
        int x, y, distance;
        boolean brokeWall;
        
        public Point(int x, int y, int distance, boolean brokeWall) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.brokeWall = brokeWall;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        visited = new int[N][M][2]; // [x][y][0: 벽을 부수지 않은 상태, 1: 벽을 부순 상태]
        
        // 방문 배열을 -1로 초기화 (방문하지 않음)
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }
        
        // 맵 읽기
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }
        
        int result = findShortestPath();
        System.out.println(result);
    }
    
    static int findShortestPath() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, false)); // (0,0)에서 시작, 거리 1, 벽을 부수지 않은 상태
        visited[0][0][0] = 1; // 시작점 방문 표시
        
        while (!queue.isEmpty()) {
            Point current = queue.poll();
            
            // 목적지에 도달했을 경우
            if (current.x == N - 1 && current.y == M - 1) {
                return current.distance;
            }
            
            // 네 방향으로 이동 시도
            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                // 새 위치가 유효한지 확인
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    int wallBroken = current.brokeWall ? 1 : 0;
                    
                    // 다음 위치가 벽인 경우
                    if (map[nx][ny] == 1) {
                        // 아직 벽을 부수지 않았다면
                        if (!current.brokeWall && visited[nx][ny][1] == -1) {
                            visited[nx][ny][1] = current.distance + 1;
                            queue.offer(new Point(nx, ny, current.distance + 1, true));
                        }
                    } 
                    // 다음 위치가 벽이 아닌 경우
                    else {
                        if (visited[nx][ny][wallBroken] == -1) {
                            visited[nx][ny][wallBroken] = current.distance + 1;
                            queue.offer(new Point(nx, ny, current.distance + 1, current.brokeWall));
                        }
                    }
                }
            }
        }
        
        return -1; // 경로를 찾지 못한 경우
    }
}