package week5;

import java.util.*;

/* 
 * 메모리 : 14,764	kb
 * 실행 시간 : 112 ms
 */

public class Baekjoon16953 {
	
	private static class Node{
		long num;
		int cnt;
		
		Node(long n, int c){
			this.num = n;
			this.cnt = c;
		}
		
	}
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();

		System.out.println(bfs(a, b));
		
		sc.close();
		
	}
	private static int bfs(int a, int b) {
		
		Queue<Node> queue = new ArrayDeque<Node>();
	
		queue.offer(new Node(a, 1));
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			if(node.num == b) {
				return node.cnt;
				
			}

			if(node.num *2 <= b) {
				queue.offer(new Node(node.num*2, node.cnt+1));
			}
			
			if(node.num * 10 + 1 <= b) {
				queue.offer(new Node(node.num*10+1, node.cnt+1));
			}
		}
		
		return -1;
		
	}
}
