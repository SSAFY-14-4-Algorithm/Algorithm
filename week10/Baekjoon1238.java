package BAEKJOON.Gold.Gold_III.P1238번_파티;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Baekjoon1238 {
    public static List<Road>[] roads;
    public static List<Road>[] reverseRoads;
    public static int[] minTimeFromX;
    public static int[] minTimeToX;
    public static int N;
    public static int M;
    public static int X;

    static class Road implements Comparable<Road> {
        int village;
        int time;

        Road(int to, int time) {
            this.village = to;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        X = readInt() - 1;
        roads = new ArrayList[N];
        reverseRoads = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            roads[i] = new ArrayList<>();
            reverseRoads[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            int from = readInt() - 1;
            int to = readInt() - 1;
            int time = readInt();
            roads[from].add(new Road(to, time));
            reverseRoads[to].add(new Road(from, time));
        }
        minTimeFromX = dijkstra(roads, X);
        minTimeToX = dijkstra(reverseRoads, X);

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, minTimeFromX[i] + minTimeToX[i]);
        }
        System.out.print(max);
    }

    public static int[] dijkstra(List<Road>[] graph, int start) {
        int[] distance = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(distance, 1_000_000_000);
        distance[start] = 0;

        PriorityQueue<Road> pq = new PriorityQueue<Road>();
        pq.add(new Road(start, 0));

        while (!pq.isEmpty()) {
            Road r = pq.poll();
            if (!visited[r.village]) {
                visited[r.village] = true;
                for (Road next : graph[r.village]) {
                    if (distance[next.village] > r.time + next.time) {
                        distance[next.village] = r.time + next.time;
                        pq.add(new Road(next.village, distance[next.village]));
                    }
                }
            }
        }
        return distance;
    }

    private static int readInt() throws IOException {
        int c, n = 0;
        c = System.in.read();
        while (c <= ' ') {
            c = System.in.read();
        }
        while ('0' <= c && c <= '9') {
            n = (n * 10) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}