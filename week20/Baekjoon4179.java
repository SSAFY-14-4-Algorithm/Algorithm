package m07.d12;

/**
 * JAVA8) 메모리: 46_648 KB, 시간: 396ms
 */

import java.io.*;
import java.util.*;

public class Main_4179_불 {

    private static char[][] map;
    private static Queue<int[]> people, fire;
    private static boolean[][] isVisited; // 사람 방문여부
    private static int R, C, time = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        isVisited = new boolean[R][C];
        people = new ArrayDeque<>();
        fire = new ArrayDeque<>();

        for (int i=0;i<R;i++) {
            String s = br.readLine();
            for (int j=0;j<C;j++) {
                switch (map[i][j] = s.charAt(j)){
                    case 'J':
                        people.offer(new int[]{i, j});
                        isVisited[i][j] = true;
                        break;
                    case 'F':
                        fire.offer(new int[]{i, j});
                        break;
                }
            }
        }

        System.out.print(bfs() ? time : "IMPOSSIBLE");
    }

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    private static boolean bfs(){

        while (!people.isEmpty()){
            int ps = people.size();
            int fs = fire.size();

            // 불 이동
            while (fs-->0){
                int[] cur = fire.poll();
                for (int i=0;i<4;i++){
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (nx<0||ny<0||nx>=R||ny>=C) continue;
                    if (map[nx][ny]=='F'||map[nx][ny]=='#') continue;

                    map[nx][ny] = 'F';
                    fire.offer(new int[]{nx, ny});
                }
            }

            // 사람 이동
            while (ps-->0){
                int[] cur = people.poll();

                for (int i=0;i<4;i++){
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];
                    if (nx<0||ny<0||nx>=R||ny>=C) return true;
                    if (map[nx][ny]=='F'||map[nx][ny]=='#') continue;
                    if (isVisited[nx][ny]) continue;

                    isVisited[nx][ny] = true;
                    people.offer(new int[]{nx, ny});
                }
            }

            time++;
        }

        return false;
    }
}
