/*
 * BOJ 1967번 : 트리의 지름
 * 메모리 : 19,804kb
 * 시간 : 168ms
 * 
 * 
 * 1. 가장 거리가 먼 두 노드 사이가 지름이 된다.
 * 2. 1번째 dfs : 루트에서 가장 거리가 먼 노드 찾기
 * 3. 2번째 dfs : 1번째 dfs로 찾은 노드에서 거리가 먼 노드 찾기
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon1967 {
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
        
        // 1번째 dfs : 가장 거리가 먼 노드 찾기
        visited = new boolean[N+1];
        maxDis = Integer.MIN_VALUE;
        dfs(1, 0); // 시작점, 현재 거리

        // 2번째 dfs : 찾은 노드에서 가장 거리가 먼 노드 찾기
        visited = new boolean[N+1];
        maxDis = Integer.MIN_VALUE;
        dfs(maxNode, 0); // 시작점, 현재 거리
        
        sb.append(maxDis);
        System.out.println(sb);
        br.close();
    }
    
   private static void dfs(int start, int dis) {
	   visited[start] = true;
	   if(dis > maxDis) {
		   maxDis = dis;
		   maxNode = start;
	   }
	   
	   for(Node n : tree[start]) {
		   if(!visited[n.next]) {
			   dfs(n.next, dis+n.weight);
		   }
	   }
   }
}