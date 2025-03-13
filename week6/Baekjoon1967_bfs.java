/*
 * BOJ 1967번 : 트리의 지름
 * 메모리 : 20,312kb
 * 시간 : 188ms
 * 
 * 
 * 1. 가장 거리가 먼 두 노드 사이가 지름이 된다.
 * 2. 1번째 dfs : 루트에서 가장 거리가 먼 노드 찾기
 * 3. 2번째 dfs : 1번째 dfs로 찾은 노드에서 거리가 먼 노드 찾기
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon1967_bfs {
    static int N;
    static List<Node>[] tree;
    static boolean[] visited;
    static int maxDis, maxNode;

    static class Node {
        int next;
        int weight;
        
        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N+1];
        for(int n = 1; n < N+1; n++) {
        	tree[n] = new ArrayList<>();
        }
        for(int n = 1; n < N ; n++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	int weight = Integer.parseInt(st.nextToken());
        	tree[a].add(new Node( b, weight ));
        	tree[b].add(new Node( a, weight ));
        }
        
        bfs(1);

        bfs(maxNode);
        
        sb.append(maxDis);
        System.out.println(sb);
        br.close();
    }
    
    private static void bfs(int start) {
    	visited = new boolean[N+1];
    	Queue<int[]> q = new ArrayDeque<int[]>();
    	q.offer(new int[] { start, 0 }); // 출발지, 시작점
    	visited[start] = true;
    	
    	maxDis = Integer.MIN_VALUE;
    	maxNode = start;
    	
    	while(!q.isEmpty()) {
    		int[] curr = q.poll();
    		int currNode = curr[0];
    		int currDis = curr[1];
    		
    		if(currDis > maxDis) {
    			maxDis = currDis;
    			maxNode = currNode;
    		}
    		
    		for(Node n : tree[currNode]) {
    			if(!visited[n.next]) {
    				visited[n.next] = true;
    				q.offer(new int[] { n.next, currDis+n.weight });
    			}
    		}
    	}
	}
}