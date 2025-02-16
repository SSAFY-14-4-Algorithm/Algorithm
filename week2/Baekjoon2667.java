package week2;

import java.io.*;
import java.util.*;

public class Baekjoon2667{
    private static final int [] dx = {0,0,1,-1};
    private static final int [] dy = {-1,1,0,0};
    private static final int HOUSE = 1, BLANK = 0;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N;
    private static int [][] map;
    private static boolean [][] visited;
    private static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<N;j++){
                map[i][j] = input.charAt(j)-'0';
            }
        }

        int houseCnt = 0;
        int count = 0;
        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(map[i][j] == HOUSE && !visited[i][j]){
                    count++;
                    houseCnt = bfs(i,j);
                    answer.add(houseCnt);
                }
            }
        }
        bw.write(count+"\n");
        Collections.sort(answer);
        for (Integer integer : answer) {
            bw.write(integer+"\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs(int x,int y){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            cnt++;
            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == BLANK) continue;

                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        return cnt;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}