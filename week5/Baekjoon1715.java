import java.io.*;
import java.util.*;

/*	
 * 메모리 29236KB
 * 시간 412ms
 * 
 * 우선순위 큐를 이용하여 작은값 반환
 * 	단순 sort후 계산시 반례
 *  5
	10 30 30 40 50   	->40
	30 40 40 50 ->70
	40 50 70 ->90
	70 90 -> 160
	40+70+90+160 = 360
 */
public class Baekjoon1715 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int result = 0;
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->a-b);
		for(int i=0;i<n;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		while(pq.size()>1) {
			int a = pq.poll();
			int b = pq.poll();
			pq.offer(a+b);
			result += a+b;
		}
		System.out.println(result);
	}
}
