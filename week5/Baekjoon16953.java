import java.io.*;
import java.util.*;

//그리디
/*
 *	14116kb, 104ms
 */
public class Baekjoon16953 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int count = 0;
		while(B > A) {
			if(B%10 == 1) {
				B /= 10;
			} else if (B%2 == 0){
				B /= 2;
			} else {
				break;
			}
			count++;
		}
		if(A != B) {
			System.out.println(-1);
		} else {
			System.out.println(count+1);
		}
	}
}

//BFS 풀이
//15920kb, 120ms
//public class Main {
//	public static class Node {
//		long x;
//		int cnt;
//		public Node(long x, int cnt) {
//			this.x = x;
//			this.cnt = cnt;
//		}
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringTokenizer st = new StringTokenizer(br.readLine());
//		
//		long A = Long.parseLong(st.nextToken());
//		long B = Long.parseLong(st.nextToken());
//		Queue<Node> q = new ArrayDeque<>();
//		q.add(new Node(A, 1));
//		int answer = -1;
//		while(!q.isEmpty()) {
//			Node cur = q.poll();
//			long x = cur.x;
//			int cnt = cur.cnt;
//			if(x*2 == B || x*10+1==B) {
//				answer = cnt+1;
//				break;
//			}
//			if(x*2 < B) {
//				q.add(new Node(x*2, cnt+1));
//			}
//			if(x*10+1 < B) {
//				q.add(new Node(x*10+1, cnt+1));
//			}
//		}
//		System.out.println(answer);
//	}
//}