package week2;

import java.io.*;
import java.util.*;

public class Baekjoon7576 {
    private static final int RIPE = 1, UNRIPE = 0;
    private static final int [] dx = {0,0,-1,1};
    private static final int [] dy = {-1,1,0,0};
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static class Node{
        int x,y,day;
        public Node(int x,int y,int day){
            this.x = x;
            this.y = y;
            this.day = day;
        }
    }
    
    private static int M,N;
    private static int [][] map;
    private static Queue<Node> queue;
    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        M = Integer.parseInt(tokens[0]);
        N = Integer.parseInt(tokens[1]);
        map = new int[N][M];
        queue = new LinkedList<>();

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
                if(map[i][j] == RIPE) queue.add(new Node(i,j,0));
            }
        }

        bfs();
        bw.flush();
        bw.close();
        br.close();
    }
    private static void bfs() throws IOException{
        int day = 0;
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            day = curNode.day;

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx, ny)) continue;
                if(map[nx][ny] == UNRIPE){
                    queue.add(new Node(nx,ny,day+1));
                    map[nx][ny] = RIPE;
                }
            }
        }

        if(!checkStatus()){
            bw.write(day+"\n");
        }else{
            bw.write("-1\n");
        }
    }

    private static boolean checkStatus(){
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == UNRIPE) return true;
            }
        }
        return false;
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
