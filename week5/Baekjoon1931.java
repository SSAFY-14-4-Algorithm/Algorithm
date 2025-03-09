package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
 * 메모리 : 48,624 kb
 * 실행 시간 : 496 ms
 */

public class Baekjoon1931{

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static PriorityQueue<Node> queue;
	static int[][] meet;
	static int N;
	
	private static class Node{
		int start;
		int end;
		
		Node(int s, int e){
			this.start = s;
			this.end = e;
		}
	}

	public static void main(String[] args)throws IOException {
		
		N = Integer.parseInt(br.readLine());
		
		queue = new PriorityQueue<Node>((o1,o2) -> {
			if(o1.end == o2.end) {
				return o1.start - o2.start;
			} else {
				return o1.end - o2.end;
			}
		});

		meet = new int[N][2];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			queue.offer(new Node(s,e));
		}
		
		for(int i=0;i<N;i++) {
			Node node = queue.poll();
			
			meet[i][0] = node.start;
			meet[i][1] = node.end;
		}
		
		int cnt = 0;
		int pre_number = 0;
		
		for(int i=0;i<N;i++) {
			if(pre_number <= meet[i][0]) {
				cnt++;
				pre_number = meet[i][1];
			}
		}
		
		System.out.println(cnt);
		
	}
}
