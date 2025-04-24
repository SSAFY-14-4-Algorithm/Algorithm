import java.util.*;
import java.io.*;
/*
 * 14196kb, 104ms
 */
public class Baekjoon1744 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> plus = new PriorityQueue<>((o1,o2)->Integer.compare(o2, o1));
		PriorityQueue<Integer> minus = new PriorityQueue<>();
		int zeroCount = 0;
		int answer = 0;
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if(num == 1) {
				answer++;
				continue;
			}
			if(num > 0) {
				plus.add(num);
			} else if(num < 0) {
				minus.add(num);
			} else {
				zeroCount++;
			}
		}
		while(!plus.isEmpty()) {
			int num1 = plus.poll();
			if(!plus.isEmpty()) {
				int num2 = plus.poll();
				answer += num1*num2;
			}else {
				answer += num1;
			}
		}
		while(!minus.isEmpty()) {
			int num1 = minus.poll();
			if(!minus.isEmpty()) {
				int num2 = minus.poll();
				answer += num1*num2;
			}else {
				if(zeroCount == 0) {
					answer += num1;
				}
			}
		}
		System.out.println(answer);
	}
}
