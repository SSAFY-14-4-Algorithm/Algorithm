package week10;

import java.io.*;
import java.util.*;

/**
 *  생각해보니까 우선순위 큐를 안쓰고 * 2를 먼저 담으면 되네 굳
 * 
 * 
 * 메모리 : 13956
 * 시간 : 84
 * 
 */

public class Baekjoon13549 {
	
	static int N, K, result;
	static boolean[] visited;
	
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()); 
        
        visited = new boolean[100001];
        
        bfs(N);
        
        System.out.println(result);
        
    }
    
    public static void bfs(int start) { 	
    	Deque<int[]> pq = new ArrayDeque<>();
    	
    	pq.offer(new int[] {start, 0});
    	visited[start] = true;
    	
    	while (!pq.isEmpty()) {
    		
    		int[] next = pq.poll();
    		
    		int number = next[0];
    		int count = next[1];
    		if(number == K) {
    			result = count;
    			return;
    		}
    		
    		if (number > 0 && number * 2 <= Math.min(K * 2, 100000)) {
    			pq.offer(new int[] {number * 2, count});
    			visited[number*2] = true;
    		}
    		
    		if(number - 1 >= 0 && !visited[number - 1]) {
    			pq.offer(new int[] {number - 1, count + 1});
    			visited[number - 1] = true;
    		}
    		if (number + 1 <= 100000 && !visited[number +1]) {
    			pq.offer(new int[] {number + 1, count + 1});
    			visited[number + 1] = true;
    		}
    	}
    }
}