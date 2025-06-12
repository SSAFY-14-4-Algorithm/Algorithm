/*
 * BOJ 1916번 : 최소비용 구하기
 * 메모리 : 51,844kb
 * 시간 : 400ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_1916_최소비용구하기 {
    static int N, M;
    static int S, E;
    static int minCost;
    static int[] dist;
    static List<Bus>[] buses;
    static class Bus implements Comparable<Bus> {
        int end, cost;
        public Bus(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Bus o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        buses = new ArrayList[N+1];
        for(int n = 1; n <= N; n++) {
            buses[n] = new ArrayList<>();
        }
        for(int m = 0; m < M; m++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            buses[start].add(new Bus(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[S] = 0;

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(S, 0));

        while(!pq.isEmpty()) {
            Bus bus = pq.poll();
            int curr = bus.end;
            int cost = bus.cost;

            if(dist[curr] < cost) continue;

            for(Bus next : buses[curr]) {
                if(dist[next.end] > dist[curr] + next.cost) {
                    dist[next.end] = dist[curr] + next.cost;
                    pq.offer(new Bus(next.end, dist[next.end]));
                }
            }
        }

        minCost = dist[E];
        sb.append(minCost);
        System.out.println(sb);
        br.close();
    }
}
