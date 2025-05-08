package week14;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 41440
 * 시간 : 324
 *
 */


public class Baekjoon15922_아우으우아으이야 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int result = 0;
		
		if(N == 1) result += end - start;
		
		for(int i = 0; i < N-1 ; i++) {
			st = new StringTokenizer(br.readLine());
			
			int nextStart = Integer.parseInt(st.nextToken());
			int nextEnd = Integer.parseInt(st.nextToken());
			
			if(end < nextStart) {
				
				result += end - start;
				start = nextStart;
				end = nextEnd;
			}
			else {
				end = Math.max(nextEnd, end);
				
			}
			
			if(i == N - 2) result += end - start;
		}
		
		System.out.println(result);
	}
}
