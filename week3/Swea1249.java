package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Swea1249 {
    static final int[] dx = {0,1,0,-1};
    static final int[] dy = {1,0,-1,0};
     
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st; 
     
    static PriorityQueue<Node> queue;
    static int[][] arr;
    static boolean[][] visited;
    static int[] distance;
     
    static int N=0;
     
    static Node startNode, endNode;
     
    private static class Node {
        int x;
        int y;
        int distance;
        boolean visited;
         
        Node(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.distance = dis;
        }
    }
     
    private static int dijkstra(){
 
        int [][] dist = new int[N][N];
        for(int [] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
         
        dist[0][0] = 0;
         
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2)->o1.distance-o2.distance);
        pq.add(startNode);
         
        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
             
            if(curNode.distance > dist[curNode.x][curNode.y]) continue;
             
            for(int d=0;d<4;d++) {
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];
                 
                if(!inRange(nx, ny)) continue;
                 
                if(dist[nx][ny] > curNode.distance + arr[nx][ny]) {
                    dist[nx][ny] = curNode.distance + arr[nx][ny];
                    pq.add(new Node(nx,ny,dist[nx][ny]));
                }
            }
        }
         
        return dist[N-1][N-1];
    }
     
    private static boolean inRange(int x, int y) {
        return 0 <= x && x <N && 0 <= y && y < N;
    }
     
     
    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(br.readLine());
         
        for(int t=0;t<testCase;t++) {
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
             
            visited = new boolean[N][N];
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine(), "");
                String s = st.nextToken();
                for (int j = 0; j < N; j++) {
                    arr[i][j] = s.charAt(j) - '0';
                }
            }
             
            startNode = new Node(0,0,0);
            endNode = new Node(N-1,N-1,Integer.MAX_VALUE);
             
            bw.write("#"+(t+1)+" ");
            bw.write(dijkstra()+"\n");
        }
        bw.flush();
        bw.close();
         
    }
}