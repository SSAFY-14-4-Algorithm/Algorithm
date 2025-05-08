import java.io.*;
import java.util.*;

/**
 * Java8
 * 메모리: 11,836KB
 * 시간: 76ms
 * 
 * Java11
 * 메모리: 14,456KB
 * 시간: 116ms
 * 
 * @author 배준수
 */
public class Baekjoon28423_게임 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int SIZE = 100_000;
	// variables
	static boolean[] isVisited = new boolean[SIZE + 1];

	static int combine(int a, int b) {
		int shift = 10;
		while (b >= shift)
			shift *= 10;
		return a * shift + b;
	}

	static int getF(int N) {
		int x = N;
		int a = 0, m = 1;

		while (x > 0) {
			int digit = x % 10;

			a += digit;
			m *= digit;

			x /= 10;
		}
		return combine(a, m);
	}

	static int getG(int N) {
		int f = getF(N);
		if (N == f)
			return 1;
		if (f > SIZE)
			return -1;
		if (isVisited[N])
			return 0;

		isVisited[N] = true;
		int ans = getG(f);
		isVisited[N] = false;
		return ans;
	}

	public static void main(String[] args) throws IOException {
		int L = readInt(), R = readInt();
		int sum = 0;
		for (int i = L; i <= R; ++i) {
			sum += getG(i);
		}
		System.out.print(sum);
	}

	static int readInt() throws IOException {
		int c, n = 0;
		while ((c = System.in.read()) >= 0x30)
			n = (n << 3) + (n << 1) + (c & 0x0F);
		if (c == '\r')
			System.in.read();
		return n;
	}
}