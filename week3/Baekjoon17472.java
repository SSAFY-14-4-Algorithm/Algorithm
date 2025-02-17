package week3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Baekjoon17472{
private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final int ISLAND = 1, WATER = 0;
    private static int N,M;
    private static int [][] map;
    private static boolean [][] visited;

    private static int [] dx = {0,0,-1,1};
    private static int [] dy = {-1,1,0,0};

    private static class Node{
        int x,y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }

    private static class Edge implements Comparable<Edge>{
        int from,to,cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        public int compareTo(Edge o){
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        N = Integer.parseInt(tokens[0]);
        M = Integer.parseInt(tokens[1]);
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++){
            tokens = br.readLine().split(" ");
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(tokens[j]);
            }
        }

        int islandCnt = findIsland();
        int[][] dist = buildBridge(islandCnt);
        bw.write(kruskal(dist,islandCnt)+"\n");
        bw.flush();
        br.close();
    }

    private static int kruskal(int[][] dist, int islandCnt) {
        ArrayList<Edge> edges = new ArrayList<>();
        int [] parent = new int[islandCnt];
        int realIslandCnt = islandCnt-1;
        for(int i=0;i<dist.length;i++){
            for(int j=i+1;j<dist[0].length;j++){
                if(dist[i][j] != Integer.MAX_VALUE){
                    edges.add(new Edge(i,j,dist[i][j]));
                }
            }
        }

        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }

        Collections.sort(edges);

        int minCost = 0;
        int edgeCnt = 0;
        for(Edge edge : edges){
            int rootA = find(edge.from, parent);
            int rootB = find(edge.to, parent);

            if(rootA != rootB){ //싸이클이 생기지 않을때..
                union(rootA,rootB,parent);
                minCost+=edge.cost;
                edgeCnt++;

                if(edgeCnt == realIslandCnt-1) break;
            }
        }
        if(edgeCnt != realIslandCnt-1) return -1;
        return minCost;
    }

    private static void union(int a,int b,int [] parent){
        int rootA = find(a,parent);
        int rootB = find(b,parent);

        if(rootA > rootB){
            parent[rootB] = rootA;
        }else{
            parent[rootA] = rootB;
        }
    }

    private static int find(int a, int [] parent){
        if(parent[a] == a) return a;

        return parent[a] = find(parent[a],parent);
    }

    private static int [][] buildBridge(int islandCnt){
        int [][] dist = new int[islandCnt][islandCnt];

        for(int [] d : dist){
            Arrays.fill(d,Integer.MAX_VALUE);
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] != WATER){
                    //좌 우 상 하
                    for(int d=0;d<4;d++){
                        boolean findIsland = true;
                        int x = i;
                        int y = j;

                        int curLength = 0;
                        while(inRange(x + dx[d], y + dy[d])){
                            x+=dx[d];
                            y+=dy[d];

                            if(map[x][y] != WATER){
                                findIsland = false;
                                break;
                            }

                            curLength++;
                        }
                        if(curLength < 2){
                            continue;
                        }

                        if(!findIsland){
                            int islandA = map[i][j];
                            int islandB = map[x][y];

                            dist[islandA][islandB] = Math.min(dist[islandA][islandB],curLength);
                            dist[islandB][islandA] = dist[islandA][islandB];
                        }
                    }
                }
            }
        }

        return dist;
    }
    private static int findIsland(){
        int islandCnt = 1;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(map[i][j] == ISLAND && !visited[i][j]) bfs(i,j,islandCnt++);
            }
        }
        return islandCnt;
    }

    private static void bfs(int x,int y, int islandCnt){
        Queue<Node> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Node(x,y));

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            map[curNode.x][curNode.y] = islandCnt;

            for(int d=0;d<4;d++){
                int nx = curNode.x + dx[d];
                int ny = curNode.y + dy[d];

                if(!inRange(nx,ny) || visited[nx][ny] || map[nx][ny] == WATER) continue;

                visited[nx][ny] = true;
                queue.add(new Node(nx,ny));
            }
        }
    }

    private static boolean inRange(int x,int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}