import java.io.*;
import java.util.*;

public class Baekjoon1715 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Long> q = new PriorityQueue<>();
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			q.add(Long.parseLong(br.readLine()));
		}
		long answer = 0;
		while(q.size() > 1) {
			long n = q.poll() + q.poll();
			q.add(n);
			answer += n;
		}
		System.out.println(answer);
	}
}