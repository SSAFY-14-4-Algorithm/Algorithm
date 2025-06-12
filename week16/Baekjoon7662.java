import java.io.*;
import java.util.*;
/* 이중 우선순위 큐
 * PQ를 사용한 풀이
 * 373748kb, 3228ms
 */

public class Baekjoon7662 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> minPQ1 = new PriorityQueue<Integer>();
		PriorityQueue<Integer> minPQ2 = new PriorityQueue<Integer>();
		PriorityQueue<Integer> maxPQ1 = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o2, o1));
		PriorityQueue<Integer> maxPQ2 = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o2, o1));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int testCase = 0; testCase < T; testCase++) {
			minPQ1.clear();
			minPQ2.clear();
			maxPQ1.clear();
			maxPQ2.clear();
			int N = Integer.parseInt(br.readLine());
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				if(st.nextToken().equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					minPQ1.add(num);
					maxPQ1.add(num);
				} else {
					int num = Integer.parseInt(st.nextToken());
					if(num == -1) { //최소
						while(!minPQ1.isEmpty() &&!minPQ2.isEmpty() && minPQ1.peek().equals(minPQ2.peek())) {
							minPQ1.poll();
							minPQ2.poll();
						}
						if(!minPQ1.isEmpty()) {
							maxPQ2.add(minPQ1.poll());
						}
					} else { //최대
						while(!maxPQ1.isEmpty() &&!maxPQ2.isEmpty() && maxPQ1.peek().equals(maxPQ2.peek())) {
							maxPQ1.poll();
							maxPQ2.poll();
						}
						if(!maxPQ1.isEmpty()) {
							minPQ2.add(maxPQ1.poll());
						}
					}
				}
			}
			
			while(!minPQ1.isEmpty() &&!minPQ2.isEmpty() && minPQ1.peek().equals(minPQ2.peek())) {
				minPQ1.poll();
				minPQ2.poll();
			}
			while(!maxPQ1.isEmpty() &&!maxPQ2.isEmpty() && maxPQ1.peek().equals(maxPQ2.peek())) {
				maxPQ1.poll();
				maxPQ2.poll();
			}
			if(minPQ1.isEmpty()) {
				sb.append("EMPTY");
			} else {
				sb.append(maxPQ1.peek()).append(" ").append(minPQ1.peek());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}