package week5;

import java.util.*;
import java.io.*;

/* 
 * 메모리 : 25,180 kb
 * 실행 시간 : 348 ms
 */

public class Baekjoon1715 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static Queue<Integer> q = new PriorityQueue<Integer>();
	static int N;
	
	public static void main(String[] args) throws IOException {
		
		N = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			q.offer(Integer.parseInt(br.readLine()));
		}
		
		System.out.println(countCard());
			
	}
	private static int countCard() {
		int totalSum = 0;
		
		while(q.size() > 1) {
			int first = q.poll();
			int second = q.poll();
			int sum = first + second;
			
			totalSum += sum;
			
			q.offer(sum);
		}

		return totalSum;
		
	}

}
