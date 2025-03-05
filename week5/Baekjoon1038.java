import java.io.*;
import java.util.*;

public class Baekjoon1038 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long answer = -1;
		if(N <= 10) {
			System.out.println(N);
		} else if (N < 1023){
			Queue<Long> q = new ArrayDeque<>();
			int cnt = 9;
			for(long i = 1; i <= 9; i++) {
				q.add(i);
			}
			A:while(!q.isEmpty()) {
				long num = q.poll();
				for(long i = 0; i <= 9; i++) { //감소체크
					if(num % 10 <= i) break;
					cnt += 1;
					if(cnt == N) {
						answer = 10*num+i;
						break A;
					}
					q.add(num*10+i);
				}
			}
			System.out.println(answer);
		} else {
			System.out.println(-1);
		}
	}
}