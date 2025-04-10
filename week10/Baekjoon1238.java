import java.io.*;
import java.util.*;
/*
 * 메모리: 21676KB
 * 시간: 284ms
 * 
 * 학생 N명, 단방향 도로 M개, 마을 X번 
 * Ti: i번째 길을 지나는 시간 
 * 최소시간 N개  중 최대 시간 
 */

public class Main {
    static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static final int max = 1_000_000_000;
    static int N, M, X;
    static List<List<Edge>> goGraph = new ArrayList<>();
    static List<List<Edge>> backGraph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); 
        M = Integer.parseInt(st.nextToken()); 
        X = Integer.parseInt(st.nextToken()); 

        for (int i = 0; i <= N; i++) { //초기화 
            goGraph.add(new ArrayList<>());
            backGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            goGraph.get(from).add(new Edge(to, weight)); // 정방향 
            backGraph.get(to).add(new Edge(from, weight));// 역방향 
        }

        int[] toX = dijkstra(backGraph, X); // 각 마을에서 X까지
        int[] fromX = dijkstra(goGraph, X); // X에서 각 마을까지
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, toX[i] + fromX[i]); //왕복 시간들 중 가장 오래걸리는 시간 구하기 
        }

        System.out.println(maxTime);
    }

    public static int[] dijkstra(List<List<Edge>> graph, int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight)); //거리가 작은 간선을 먼저 꺼내도록 기준 설정 
        int[] dist = new int[N + 1];
        Arrays.fill(dist, max);
        dist[start] = 0;
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll(); //거리가 가장 작은 정점 꺼내기(정렬되어있기 때문에) 
            int now = current.to; // 방문 중인 정점 번호를 now에 저장 
            if (current.weight > dist[now]) continue; //최단 시간을 찾아야하므로 최단 시간 아니면 continue
            for (Edge next : graph.get(now)) { //연결된 모든 next를 탐색 
                if (dist[next.to] > dist[now] + next.weight) {
                    dist[next.to] = dist[now] + next.weight;
                    pq.offer(new Edge(next.to, dist[next.to]));
                }
            }
        }
        return dist;
    }
}
