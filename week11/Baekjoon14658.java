package study5;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 12228 KB
 * 시간 : 88 ms
 */

public class Baekjoon14658 {

	static int N, M, L, K, result;
	static int[][] arr;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        arr = new int[K][2];
        
        for(int i = 0; i < K ; i++) {
        	st = new StringTokenizer(br.readLine());
        	arr[i][0] = Integer.parseInt(st.nextToken());
        	arr[i][1] = Integer.parseInt(st.nextToken());
        }
        
        
        for(int i = 0; i < K ; i++) {
        	for(int j = 0; j < K ; j++) {
        		result = Math.max(result, distance(arr[i][0], arr[j][1]));
        	}
        }
        
        System.out.println(K - result);
    }
    
    public static int distance(int x, int y) {
    	int count = 0;
    	
    	for(int i = 0; i < K ; i++) {	
    		
    		if(arr[i][0] >= x && arr[i][0] <= x + L && arr[i][1] >= y && arr[i][1] <= y + L) count++;
    	}
    	
    	return count;
    }
}