import java.io.*;
import java.util.*;

public class Baekjoon2178 {
    static class Node {
        private int x;
        private int y;
        private int count;
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] array = new int[N][M];
        for(int i = 0; i < N; i++){
            String[] tmp = br.readLine().split("");
            for(int j = 0; j < M; j++){
                array[i][j] = Integer.parseInt(tmp[j]);
            }
        }
        int[][] visited = new int[N][M];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0, 1));
        visited[0][0] = 1;
        int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        A: while(!q.isEmpty()){
            Node now = q.poll();
            int x = now.x;
            int y = now.y;
            int cnt = now.count;
            for(int i = 0; i < 4; i++){
                int nx = x + dir[i][0];
                int ny = y + dir[i][1];
                if(nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == 0 && array[nx][ny] == 1){
                    if (nx == (N-1) && ny == (M-1)){
                        System.out.println(cnt+1);
                        break A;
                    }
                    visited[nx][ny] = 1;
                    q.offer(new Node(nx, ny, cnt+1));
                }
            }
        }
    }
}