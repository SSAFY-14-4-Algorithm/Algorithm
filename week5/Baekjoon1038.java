package week5;

import java.util.*;

public class Baekjoon1038 {
	
	static Queue<Long> q = new PriorityQueue<Long>();
	static int N;
	static long cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		
		if(N > 1022) {
			System.out.println(-1);
			System.exit(0);;
		}
		
		insertNumber(0, 10);

		for(int i=0;i<=N;i++) cnt = q.poll();
		
		System.out.println(cnt);

		sc.close();
	}

	private static void insertNumber(long beforeNum, int lastDigit) {

		for(int i=0;i<lastDigit;i++) {
			
			long num = beforeNum*10+i;
				
			q.offer((long)num);	
			insertNumber((long)num, i);
		}
	
	}
}
