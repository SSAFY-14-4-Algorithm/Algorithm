/**
 * 250611
 * Java8 | 시간: 388ms, 메모리 : 123,540KB
 */
import java.util.*;
import java.io.*;

public class Baekjoon_2573_빙산 {
    private static int N, M;
    private static boolean[][] isIceberg, isVisited;
    private static final int[] dx = {0, 0, -1, 1};
    private static final int[] dy = {-1, 1, 0, 0};

    private static class Cor{
        int x, y, height;
        Cor(int x, int y, int height){
            this.x = x;
            this.y = y;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        Queue<Cor> icebergQueue = new ArrayDeque<>();

        for (int i=0;i<N;i++) for (int j=0;j<M;j++) {
            int height = readInt();
            if (height>0) icebergQueue.offer(new Cor(i, j, height));
        }

        int year = 0;
        while(!icebergQueue.isEmpty()){
            isIceberg = new boolean[N][M];
            for (Cor c:icebergQueue) isIceberg[c.x][c.y] = true;

            if (isSeparated(icebergQueue)){
                System.out.print(year);
                return;
            }

            simulation(icebergQueue);
            year++;
        }

        System.out.print(0);
    }

    // 녹이기
    private static void simulation(Queue<Cor> icebergQueue){
        int size = icebergQueue.size();

        while(size-->0){
            Cor c = icebergQueue.poll();
            int water = 0;
            for (int idx=0; idx<4; idx++){
                int nx = c.x + dx[idx];
                int ny = c.y + dy[idx];
                if (nx<0||nx>=N||ny<0||ny>=M||isIceberg[nx][ny]) continue;
                water++;
            }

            int newHeight = Math.max(c.height-water, 0);
            if (newHeight>0) {
                icebergQueue.offer(new Cor(c.x, c.y, newHeight));
            }
        }
    }

    // 두 덩어리 이상으로 분리되었는지 확인
    private static boolean isSeparated(Queue<Cor> icebergQueue){

        isVisited = new boolean[N][M];
        int ct = 0;

        for (Cor c : icebergQueue) {
            if (!isVisited[c.x][c.y]) {
                bfs(c.x, c.y);
                if (++ct>=2) return true;
            }
        }
        return false;
    }

    // 영역 구하기
    private static void bfs(int x, int y){
        Queue<Cor> q = new ArrayDeque<>();
        q.offer(new Cor(x, y, 0));
        isVisited[x][y] = true;

        while(!q.isEmpty()){
            Cor c = q.poll();
            for (int idx=0; idx<4; idx++){
                int nx = c.x + dx[idx];
                int ny = c.y + dy[idx];
                if (nx<0||nx>=N||ny<0||ny>=M) continue;
                if (isVisited[nx][ny]||!isIceberg[nx][ny]) continue;
                isVisited[nx][ny] = true;
                q.offer(new Cor(nx, ny, 0));
            }
        }
    }

    private static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
