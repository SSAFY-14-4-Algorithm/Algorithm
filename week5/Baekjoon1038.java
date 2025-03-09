import java.io.*;
import java.util.*;
//메모리 14356KB 실행시간 108ms
public class Baekjoon1038 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		List<Long> decreasingNumbers = new ArrayList<>();
		Queue<Long> queue = new LinkedList<>();

		for (long i = 0; i <= 9; i++) {
			queue.offer(i);
			decreasingNumbers.add(i);
		}

		while (!queue.isEmpty()) {
			long num = queue.poll();
			long lastDigit = num % 10;

			for (long nextDigit = 0; nextDigit < lastDigit; nextDigit++) {
				long newNum = num * 10 + nextDigit;
				queue.offer(newNum);
				decreasingNumbers.add(newNum);
			}
		}

		if (N >= decreasingNumbers.size()) {
			System.out.println(-1);
		} else {
			System.out.println(decreasingNumbers.get(N));
		}
	}
}
