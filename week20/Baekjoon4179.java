import java.io.*;
import java.util.*;

/**
 * 87660kb
 * 640ms
 */
public class Baekjoon4179 {
    static final Character WALL = '#', BLANK = '.', JIHOON = 'J' ,FIRE = 'F';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int [] dx = {0,0,-1,1};
    static int [] dy = {-1,1,0,0};

    static int R,C;
    static Node[][] map;
    static Node jihoon;

    public static void main(String[] args) throws IOException{
        init();
        simulation();
    }

    static void simulation() throws IOException{
        Queue<Node> queue = new ArrayDeque<>();

        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j].status == FIRE) queue.add(map[i][j]);
            }
        }

        queue.add(jihoon);
        while(!queue.isEmpty()){
            Node curNode = queue.poll();

            if(curNode.status == JIHOON){
                if(curNode.x == 0 || curNode.x == R-1 || curNode.y == 0 || curNode.y == C-1){
                    bw.write(curNode.time+1+"\n");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
            }

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx, ny)) continue;
                
                if(curNode.status == FIRE){
                    if(map[nx][ny].status == BLANK || map[nx][ny].status == JIHOON){
                        map[nx][ny].status = FIRE;
                        queue.add(new Node(nx,ny,0,FIRE));
                    }
                }else if(curNode.status == JIHOON){
                    if(map[nx][ny].status == BLANK){
                        map[nx][ny].status = JIHOON;
                        queue.add(new Node(nx,ny,curNode.time+1,JIHOON));
                    }
                }
            }
        }

        bw.write("IMPOSSIBLE\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void init() throws IOException{
        String [] tokens = br.readLine().split(" ");
        R = Integer.parseInt(tokens[0]);
        C = Integer.parseInt(tokens[1]);

        map = new Node[R][C];

        for(int i=0;i<R;i++){
            String input = br.readLine();
            for(int j=0;j<C;j++){
                Character status = input.charAt(j);

                if(status == JIHOON){
                    jihoon = new Node(i, j,0,status);
                }
                map[i][j] = new Node(i, j,0, status);
            }
        }
    }

    static boolean inRange(int x,int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }

    static class Node{
        int x,y;
        int time;
        Character status;
        public Node(int x,int y,int time, Character status){
            this.x = x;
            this.y = y;
            this.time = time;
            this.status = status;
        }
    }
}
