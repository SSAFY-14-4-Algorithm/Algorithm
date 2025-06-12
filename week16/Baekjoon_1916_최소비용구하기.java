import java.io.*;
import java.util.*;
/*
 * 	메모리:70636KB
 *  시간:	700ms
 */
public class Baekjoon_1916_최소비용구하기 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<Edge>[] graph;
    static int[] dist;
    static final int INF = 1_000_000_000;

    static class Edge {
        int to, cost;
        
        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    //기본적인 dijkstra 문제
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            String[] in = br.readLine().split(" ");
            int u = Integer.parseInt(in[0]);
            int v = Integer.parseInt(in[1]);
            int w = Integer.parseInt(in[2]);
            graph[u].add(new Edge(v,w));
        }

        String[] q = br.readLine().split(" ");
        int start = Integer.parseInt(q[0]);
        int end   = Integer.parseInt(q[1]);

        dist = new int[N+1];
        Arrays.fill(dist, INF);

        dijkstra(start);

        System.out.println(dist[end]);
    }

    static void dijkstra(int start){
        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b)->a.cost - b.cost);
        pq.add(new Edge(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Edge tmp = pq.poll();
            int to = tmp.to;
            int cost = tmp.cost;
            if(dist[to] < cost) continue;

            for(Edge e:graph[to]){
                if(dist[e.to] > cost + e.cost){
                    dist[e.to] = cost + e.cost;
                    pq.add(new Edge(e.to, cost + e.cost));
                }
            }
        }
    }
}
