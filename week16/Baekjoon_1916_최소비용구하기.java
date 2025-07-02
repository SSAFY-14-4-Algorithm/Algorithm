import java.util.*;
import java.io.*;

/**
 * 250612
 * Java8 | 시간: 328 ms, 메모리: 24,996 KB
 */

public class Baekjoon_1916_최소비용구하기 {
    static class Node implements Comparable<Node>{
        int v, cost;
        Node(int v, int cost){
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        ArrayList<ArrayList<Node>> map = new ArrayList<>();
        for (int i=0;i<=N;i++) map.add(new ArrayList<>());

        for (int i=0;i<M;i++){
            int v = readInt();
            int e = readInt();
            int w = readInt();
            map.get(v).add(new Node(e, w));
        }

        int start = readInt();
        int end = readInt();
        System.out.print(dijkstra(N, start, end, map));
    }

    private static int dijkstra(int N, int start, int end, ArrayList<ArrayList<Node>> map){
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node c = pq.poll();
            if (dist[c.v] < c.cost) continue;

            for (Node nextPoint:map.get(c.v)){
                int nc = c.cost + nextPoint.cost;
                if (dist[nextPoint.v]>nc){
                    dist[nextPoint.v] = nc;
                    pq.offer(new Node(nextPoint.v, nc));
                }
            }
        }
        return dist[end];
    }

    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}
