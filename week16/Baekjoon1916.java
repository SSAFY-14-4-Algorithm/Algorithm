import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 
 * 50576ms	324kb
 * 서로 다른 도시 N개, 버스 M 개
 * 출발 도시 번호, 도착지 번호, 버스 비용
 * 출발점 도시 번호, 도착점 도시번호
 *
 * 다익스트라
 * 1과 연결된 것 중 가장 짧은 간선 뽑고, dist 배열 갱신
 * 1 -> 4 를 경유하는 것 중 가장 짧은 간선들 갱신
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(reader.readLine());
        int M = Integer.parseInt(reader.readLine());

        graph = new ArrayList[N+1];
        dist = new int[N+1]; //시작점에서 다른 도시까지의 최소 거리
        Arrays.fill(dist, Integer.MAX_VALUE); //dist 배열 초기화

        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, cost));
        }

        st = new StringTokenizer(reader.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start,end);
    }

    static ArrayList<Node>[] graph; //배열 안 노드 클래스
    static int[] dist;

    static void dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Node(start,0));

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();

            if(curNode.to == end) { //end가 poll 된다면, end를 통하는 도착지를 확인하는 건데, 그 뒷 경유지는 더이상 볼 필요 없다
                System.out.println(dist[end]);
                return;
            }

            if(curNode.cost > dist[curNode.to]) continue; //이미 더 짧은 거리를 알고 있으면 무시

            for(Node nextNode : graph[curNode.to]) { //현재 노드와 연결된 다른 노드들
                int nextCost = curNode.cost + nextNode.cost;

                if(nextCost < dist[nextNode.to]) { //경유지를 거쳐오는 것이 더 빠르다면
                    dist[nextNode.to] = nextCost;
                    pq.add(new Node(nextNode.to, nextCost));
                }
            }

        }
    }

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}

