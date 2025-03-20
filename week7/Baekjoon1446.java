/*
 * BOJ 1446번 : 지름길
 * 메모리 : 11,908kb
 * 시간 : 76ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon1446 {
    static int N, D;
    static List<Node>[] graph;
    static int[] dis;
    static class Node implements Comparable<Node>{
        int end, dis;
        
        public Node(int end, int dis) {
            this.end = end;
            this.dis = dis;
        }

		@Override
		public int compareTo(Node o) {
			return this.dis-o.dis;
		}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        graph = new ArrayList[D+1];
        for (int i = 0; i <= D; i++) {
            graph[i] = new ArrayList<>();
        }

        // 기본 도로 
        for (int i = 0; i < D; i++) {
            graph[i].add(new Node(i+1, 1)); // end, dis
        }

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            
            // end가 목적지 넘으면 안됨 && 원래 길보다 지름길이 더 길면 안됨
            if(e <= D && e-s > d) {
            	graph[s].add(new Node(e, d));
            }
        }

        dis = new int[D+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        
        dijkstra();
        
        sb.append(dis[D]);
        System.out.print(sb);
        br.close();
    }

    static void dijkstra() {
//        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.dis-o2.dis);
        PriorityQueue<Node> pq = new PriorityQueue<>(
        		);
        pq.add(new Node(0, 0)); // start, end
        dis[0] = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            
            // 현재 거리가 더 짧은 경우
            if(dis[curr.end] >= curr.dis) { 
	            for (Node next : graph[curr.end]) {
	            	// 기존에 기록된 거리보다 더 짧으면 갱신
	                if (dis[next.end] > dis[curr.end]+next.dis) {
	                    dis[next.end] = dis[curr.end]+next.dis;
	                    pq.add(new Node(next.end, dis[next.end])); // start, end
	                }
	            }
            }
        }
    }
}
