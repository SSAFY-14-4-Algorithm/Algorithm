package AlgorithmStudy.week10;

import java.util.*;

/*
 * 메모리 : 19,064 kb
 * 실행 시간 : 128	ms
 * 
 * [이동하는 조건을 큐에 다른 순서로 넣을 경우 다른 답..?]
 */

public class BJ_13549_숨바꼭질3 {

	static int N,K;
	
	static class Node{
		int pos, cnt;
		
		Node(int p, int c){
			this.pos = p;
			this.cnt = c;
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		System.out.println(bfs());
		
		sc.close();
	}
	static int bfs() {
		int result = 0;
		
		Queue<Node> q = new ArrayDeque<Node>();
		boolean[] visited = new boolean [100_001];
		
		q.offer(new Node(N, 0));
		visited[N] = true;
		
		while(true) {
			Node current = q.poll();
			int pos = current.pos;
			int cnt = current.cnt;
			
			if(pos == K) {
				result = current.cnt;
				break;
			}
			
			for(int num : new int[] {pos*2, pos-1, pos+1}) {
				if(!isRange(num) || visited[num] ) continue;
				
				int nCnt = cnt;
				if(num != pos*2) nCnt = cnt + 1;
				
				q.offer(new Node(num, nCnt));
				visited[num] = true;
			}

		}
		
		return result;
	}
	static boolean isRange(int x) {
		return 0 <= x && x <= 100_000;
	}

}
