import java.util.*;
import java.io.*;
/* 벽부수고 이동하기2
 * 347992kb, 1468ms
 */
public class Baekjoon14442 {
    public static class Node{
        int x, y, k;
        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map =  new int[N][M];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = s.charAt(j) - '0';
            }
        }
        int[][][] maps = new int[K+1][N][M];
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, 0));
        maps[0][0][0] = 1; //시작을 1로 잡고 정답에서 1 빼주기, maps의 숫자로 방문처리
        boolean[][] visited = new boolean[N][M];
        visited[0][0] = true;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int k = cur.k;
            for(int i = 0; i < 4; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(0 <= nx &&  nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    if(map[nx][ny] == 1){ //벽을 부숴야 하는 경우
                        //벽을 부수는 횟수가 남아 있고 방문한적이 없는 경우
                        if(k < K && maps[k+1][nx][ny] == 0){
                            maps[k+1][nx][ny] = maps[k][x][y] + 1;
                            q.add(new Node(nx, ny, k+1));
                        }
                    } else { //벽을 부수지 않아도 될 경우
                        //방문한적이 없는 경우
                        if(maps[k][nx][ny] == 0){
                            maps[k][nx][ny] = maps[k][x][y] + 1;
                            q.add(new Node(nx, ny, k));
                        }
                    }
                }
            }
        }
        int ans = N*M+1;
        for(int i = 0; i <= K; i++){
            if(maps[i][N-1][M-1] != 0){
                ans = Math.min(ans, maps[i][N-1][M-1]);
            }
        }
        System.out.println(ans == N*M+1 ? -1 : ans);
    }
}