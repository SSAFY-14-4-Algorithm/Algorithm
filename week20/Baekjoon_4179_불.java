import java.io.*;
import java.util.*;
/*
 * 메모리:54956KB
 * 시간:	412ms
 */
public class Baekjoon_4179_불 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int r, c;
    static char[][] map;
    static int[][] fireTime, jihunTime;
    static boolean[][] fireVisited, jihunVisited;
    static final int[] dx = {0, 0, -1, 1};
    static final int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        r = Integer.parseInt(s[0]);
        c = Integer.parseInt(s[1]);

        map = new char[r][c];
        fireTime = new int[r][c];
        jihunTime = new int[r][c];
        fireVisited = new boolean[r][c];
        jihunVisited = new boolean[r][c];

        Deque<int[]> fireQ = new ArrayDeque<>();
        Deque<int[]> jihunQ = new ArrayDeque<>();

        for(int i=0; i<r; i++){
            String s2 = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = s2.charAt(j);
                if(map[i][j] == 'F'){
                    fireVisited[i][j] = true;
                    fireQ.offer(new int[] {i, j});
                } else if(map[i][j] == 'J'){
                    jihunVisited[i][j] = true;
                    jihunQ.offer(new int[] {i, j});
                }
            }
        }

        bfsFire(fireQ);
        int ans = bfsJihun(jihunQ);

        System.out.println(ans < 0 ? "IMPOSSIBLE" : ans);
    }

    // 불이 번지는 최소 시간을 계산
    static void bfsFire(Deque<int[]> q){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d], ny = y + dy[d];
                if(!isRange(nx,ny)) continue;
                if(map[nx][ny] == '#' || fireVisited[nx][ny]) continue;
                fireVisited[nx][ny] = true;
                fireTime[nx][ny] = fireTime[x][y] + 1;
                q.offer(new int[]{nx, ny});
            }
        }
    }

    // 지훈이가 탈출 가능한 최소 시간을 계산, 불에 잡히면 -1 반환
    static int bfsJihun(Deque<int[]> q){
        while(!q.isEmpty()){
        	
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
           
            // 가장자리 도달 시 탈출
            if(x == 0 || y == 0 || x == r-1 || y == c-1){
                return jihunTime[x][y] + 1;
            }
            for(int d = 0; d < 4; d++){
                int nx = x + dx[d], ny = y + dy[d];
                if(!isRange(nx,ny)) continue;
                if(map[nx][ny] == '#' || jihunVisited[nx][ny]) continue;
                int nt = jihunTime[x][y] + 1;
                
                // 불이 먼저 또는 동시에 도착하면 못 감
                if(fireVisited[nx][ny] && fireTime[nx][ny] <= nt) continue;
                jihunVisited[nx][ny] = true;
                jihunTime[nx][ny] = nt;
                q.offer(new int[]{nx, ny});
            }
        }
        return -1;
    }
    static boolean isRange(int x, int y) {
    	return x>=0 && y>=0 && x<r && y<c;
    }
}
