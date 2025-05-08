/*
 * BOJ 11437 : LCA
 * 메모리 : 42,632kb
 * 시간 : 1,272ms
 * 
 * 1. 모든 노드에 대한 깊이 계산
 * 2. 최소 공통 조상을 찾을 두 노드 확인
 * 3. 먼저 두 노드의 깊이가 동일하도록 거슬러 올리감
 * 4. 부모가 같아질 때까지 반복적으로 두 노드의 부모 방향으로 거슬러 올라감
 * 5. 모든 LCA(a, b) 연산에 대해 3~4번 과정 반복
 */

import java.io.*;
import java.util.*;

public class BOJ11437_LCA {
	static int N, M;
    static int[] parent;
    static int[] level;
    static List<Integer>[] tree;
    
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        level = new int[N+1];
        tree = new ArrayList[N+1];
        for(int n = 1; n <= N; n++) {
        	tree[n] = new ArrayList<>();
        }
        
        for(int n = 1; n < N; n++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	tree[a].add(b);
        	tree[b].add(a);
       }
       
       // 1번 노드부터 트리 만들기
       dfs(1, 0); // childNode, parentNode
    
       M = Integer.parseInt(br.readLine());
       for(int m = 0; m < M; m++) {
    	   StringTokenizer st = new StringTokenizer(br.readLine());
    	   int a = Integer.parseInt(st.nextToken());
    	   int b = Integer.parseInt(st.nextToken());
    	   
    	   sb.append(LCA(a, b)).append("\n");
       }
       
       System.out.println(sb);
       br.close();
    }
    
    private static void dfs(int childNode, int parentNode) {
		parent[childNode] = parentNode;
		level[childNode] = level[parentNode]+1;
		
		for(int child : tree[childNode]) {
			if(child == parentNode) continue;
			dfs(child, childNode);
		}
	}
    
    private static int LCA(int a, int b) {
    	// a 가 더 깊은 노드가 되도록 (깊이 맞추기 위해서)
    	if(level[a] < level[b]) {
    		int temp = a;
    		a = b;
    		b = temp;
    	}
    	
    	// a, b 깊이 맞추기
    	while(level[a] != level[b]) {
    		a = parent[a];
    	}
    	
    	// a, b 공통 조상 찾기
    	while(a != b) {
    		a = parent[a];
    		b = parent[b];
    	}
    	
    	return a;
	}
}


