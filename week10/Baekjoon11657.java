package week10;

import java.io.*;
import java.util.*;

/**
 * 
 * 
 * 메모리 : 16748
 * 시간 : 204
 * 
 */

public class Baekjoon11657 {
	
	static Edge[] graph;
	
	static class Edge {
        int st;
        int end;
        int cost;

        public Edge(int s, int e, int c) {
            this.st = s;
            this.end = e;
            this.cost = c;
        }
    }
	
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        graph = new Edge[m + 1];
        long[] dist = new long[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[i] = new Edge(a, b, w);
        }

        dist[1] = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Edge now_edge = graph[j];
                if (dist[now_edge.st] != Integer.MAX_VALUE && dist[now_edge.end] > dist[now_edge.st] + now_edge.cost) {
                    dist[now_edge.end] = dist[now_edge.st] + now_edge.cost;
                }
            }
        }

        boolean cycle = false;
        for(int i = 0 ; i < m; i++){
            Edge now_edge = graph[i];
            if (dist[now_edge.st] != Integer.MAX_VALUE && dist[now_edge.end] > dist[now_edge.st] + now_edge.cost) {
                cycle = true;
            }
        }
        if(cycle){
        	sb.append("-1\n");
        } else {
            for(int i = 2; i <= n; i++){
                if(dist[i]==Integer.MAX_VALUE)
                	sb.append(-1+"\n");
                else sb.append(dist[i]+"\n");
            }
        }
        System.out.println(sb);
    }
}