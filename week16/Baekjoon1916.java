import java.io.*;
import java.util.*;

/**
 * 61848kb, 580ms
 */
public class Baekjoon1916 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,M;
    static int [] dist;
    static boolean [] visited;
    
    static class Node implements Comparable<Node>{
        int end;
        int weight;
        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }

    static ArrayList<ArrayList<Node>> arrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N+1];
        visited = new boolean[N+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int i=0;i<N+1;i++){
            arrayList.add(new ArrayList<>());
        }

        for(int i=0;i<M;i++){
            String [] tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            arrayList.get(start).add(new Node(end, weight));
        }

        String [] tokens = br.readLine().split(" ");
        int startPos = Integer.parseInt(tokens[0]);
        int endPos = Integer.parseInt(tokens[1]);

        int result = dijkstra(startPos,endPos);
        bw.write(result+"\n");
        bw.flush();
        br.close();
        bw.close();
    }
    static int dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node curNode = pq.poll();
            int cur = curNode.end;
            if(!visited[cur]){
                visited[cur] = true;

                for (Node node : arrayList.get(cur)) {
                    if(!visited[node.end] && dist[node.end] > dist[cur] + node.weight){
                        dist[node.end] = dist[cur] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }
        return dist[end];
    }
}
