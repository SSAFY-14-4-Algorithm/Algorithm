package AlgorithmStudy.week8;

import java.util.*;
import java.io.*;

public class Baekjoon6603 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int[] nums;
	static boolean[] visited;
	static int N;
	
	/*
	 * 메모리 : 14,316 kb
	 * 실행 시간 : 192 ms
	 */
	
	public static void main(String[] args) throws IOException {
		String input;
		
		while(!(input = br.readLine()).equals("0")) {
			st = new StringTokenizer(input);
			
			N = Integer.parseInt(st.nextToken());
			
			nums = new int[N];
			for(int i=0;i<N;i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0, new int[6]);
			
			System.out.println();
		}
		
	}

	private static void combination(int start, int depth, int[] result) {
		
		if(depth == 6) {
			for(int i=0;i<depth;i++) {
				System.out.print(result[i] + " ");
			}
			System.out.println();
			
			return;
		}
		for(int i=start;i<N;i++) {
			result[depth] = nums[i];
			combination(i+1, depth+1, result);
		}

	}
	
}
