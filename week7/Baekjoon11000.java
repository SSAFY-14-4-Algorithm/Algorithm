/*
 * BOJ 11000번 : 강의실 배정 
 * 메모리 : 76,732kb
 * 시간 : 684ms
 * 
 * 최소한의 강의실 수로 배정 
 */

package algorithm;

import java.io.*;
import java.util.*;

public class Baekjoon11000 {
	static int N;
	static Node[] meeting;
	static int cnt;
	static class Node{
		int start, end;
		
		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		meeting = new Node[N];
		for(int n = 0; n < N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			meeting[n] = new Node(s, e);
		}

		Arrays.sort(meeting, (o1,o2) -> o1.start-o2.start);
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(meeting[0].end);
		
		for(int n = 1; n < N; n++) {
			// 가장 빠른 종료 시간 <= 현재 강의 시작시
			if(pq.peek() <= meeting[n].start) {
				pq.poll(); // 강의실 재사용 가능, poll() 빼주기 
			}
			pq.add(meeting[n].end); // 새로운 강의 종료시간 추가 
//			System.out.println(pq);
		}
		
		cnt = pq.size();
		sb.append(cnt);
		System.out.print(sb);
		br.close();
	}
}
