import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
//메모리 25896KB 실행시간 312ms
public class Baekjoon1715 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}

		int answer = 0;
		while (pq.size() > 1) {
			int first = pq.poll();
			int second = pq.poll();
			int sum = first + second;
			answer += sum;
			pq.offer(sum);
		}

		System.out.println(answer);
	}
}
