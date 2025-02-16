package week2;

import java.io.*;
import java.util.*;

public class Baekjoon2178{
    private static final int WALL = '0';
    private static final int [] dx = {0,0,-1,1};
    private static final int [] dy = {-1,1,0,0};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int N,M;
    private static char [][] map;

    private static class Node{
        int x,y,depth;
        public Node(int x,int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
        public Node(){

        }
    }

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);

        map = new char[N][M];

        for(int i=0;i<N;i++){
            String input = br.readLine();
            for(int j=0;j<M;j++){
                map[i][j] = input.charAt(j);
            }
        }

        int answer = bfs(0,0, new boolean[N][M]).depth;
        bw.write(answer+"\n");
        bw.flush();
        bw.close();
        br.close();
    }

    public static Node bfs(int x,int y, boolean [][] visited){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 1));
        Node answer = new Node();
        while (!q.isEmpty()) {
            Node now = q.poll();
            if(now.x==(N-1) && now.y == (M-1)){
                answer = now;
                break;
            }
            for(int i=0;i<4;i++){
                int nextX = now.x+dx[i];
                int nextY = now.y+dy[i];

                if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M){
                    continue;
                }
                if(map[nextX][nextY] == WALL || visited[nextX][nextY]){
                    continue;
                }
                q.offer(new Node(nextX, nextY, now.depth+1));
                visited[nextX][nextY] = true;
            }
        }
        return answer;
    }
}