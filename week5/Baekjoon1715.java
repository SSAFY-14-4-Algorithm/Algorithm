/*
 * 백준 1715번 : 카드 정렬하기 
 * 메모리 : 25,092kb
 * 시간 : 308ms
 * 
 * 그리디
 * 가장 작은 두 묶음을 먼저 합쳐야 최소 비교 횟수가 나온다
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon1715 {
	static int N;
	static PriorityQueue<Integer> pq;
	static int minSum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine()); 
		pq = new PriorityQueue<Integer>();
		minSum = 0;
		for(int n = 0; n < N; n++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		
		while(pq.size() > 1) {
			int a = pq.poll();
			int b = pq.poll();
			
			int sum = a+b;
			minSum += sum;
			
			pq.offer(sum);
		}
		
		sb.append(minSum);
		System.out.print(sb);
		br.close();
	}
}
