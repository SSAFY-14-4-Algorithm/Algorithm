package week10;

import java.io.*;
import java.util.*;

/**
 * 
 * 
 * 메모리 : 20568
 * 시간 : 252
 * 
 */

public class Baekjoon1238 {

	static int N, M, start;
    static boolean[] check;
    static PriorityQueue<Node> pq = new PriorityQueue<>((e1, e2)-> e1.weight -e2.weight);
	
	static class Node{
		int node;
		int weight;
		
		Node(int node, int weight){
			this.node = node;
			this.weight = weight;
		}
	}
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        
        List<Node>[] graph = new ArrayList[N + 1];
        List<Node>[] graphRever = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i <= N; i++) graphRever[i] = new ArrayList<>();
        
        int[] cost = new int[N + 1];
        int[] costRever = new int[N + 1];
        Arrays.fill(cost,  Integer.MAX_VALUE);
        Arrays.fill(costRever,  Integer.MAX_VALUE);
        
        for(int i = 0; i < M ; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	graph[a].add(new Node(b, w));
        	graphRever[b].add(new Node(a, w));
        }
        
        check = new boolean[N + 1];
        dijkstra(start, graph, cost);
        
        check = new boolean[N + 1];
        dijkstra(start, graphRever, costRever);
        
        int result = 0;
        for(int i = 1; i <= N ; i++) {
        	if(i == start) continue;
        	result = Math.max(result, cost[i] + costRever[i]);
        }
        
        System.out.println(result);
    }
    
    public static void dijkstra(int start, List<Node>[] graph, int[] cost) {
    	pq.offer(new Node(start, 0));
    	cost[start] = 0;
    	
    	while(!pq.isEmpty()) {
    		Node curNode = pq.poll();
    		int cur = curNode.node;
    		
    		if(check[cur]) continue;
    		check[cur] = true;
    		
    		for(Node node : graph[cur]) {
    			
    			if(!check[node.node] && cost[node.node] > cost[cur] + node.weight) {
    				
    				cost[node.node] = cost[cur] + node.weight;
    				pq.add(new Node(node.node, cost[node.node]));
    			}
    		}
    	}
    }
}