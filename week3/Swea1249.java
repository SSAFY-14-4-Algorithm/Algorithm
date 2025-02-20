package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Scanner;


class Node implements Comparable<Node>{
    int x;
    int y;
    int cost;
    Node(int x,int y,int cost){
        this.x=x;
        this.y=y;
        this.cost=cost;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost,o.cost);
    }

}
public class Swea1249  {


    public static StringBuilder sb=new StringBuilder();
    public static int[] dx={-1,0,0,1};
    public static int[] dy={0,1,-1,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int T=Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++){
            int N=Integer.parseInt(br.readLine());
            int[][] map=new int[N+1][N+1];
            int[][] cost=new int[N+1][N+1];
            for(int i=1;i<=N;i++){
                String line=br.readLine();
                for(int j=1;j<=N;j++){
                    map[i][j]=line.charAt(j-1)-'0';
                    cost[i][j]=Integer.MAX_VALUE;
                }
            }
            dijkstra(map,cost,N);
//      print(map,N);
            sb.append("#").append(t).append(" ").append(cost[N][N]).append("\n");
        }
        System.out.println(sb);

    }

    static void print(int[][] arr,int N){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    static void dijkstra(int[][] map, int[][] cost, int N){
        PriorityQueue<Node> queue=new PriorityQueue<Node>();
        queue.add(new Node(1,1,0));
        cost[1][1]=0;

        while (!queue.isEmpty()){
            Node node=queue.poll();
            int x=node.x;
            int y=node.y;
            int nodeCost= node.cost;
            if(x==N&&y==N){
                break;
            }
            for(int i=0;i<4;i++){
                int plusX=x+dx[i];
                int plusY=y+dy[i];

                if(plusX>N||plusX<=0||plusY>N||plusY<=0)
                    continue;

                int newCost=nodeCost+map[plusX][plusY];

                if(newCost<cost[plusX][plusY]){
                    cost[plusX][plusY]=newCost;
                    queue.add(new Node(plusX,plusY,newCost));
                }
            }
        }

    }
}
