package week4;

import java.io.IOException;
import java.util.Arrays;

public class Baekjoon1759 {
	private static int L;
	private static int C;
	private static char[] chars;
	private static char[] result;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		L = readInt();
		C = readInt();
		result = new char[L];
		chars = new char[C];
		for (int i = 0; i < C; i++) {
			chars[i] = (char) System.in.read();
			System.in.read();
		}
		Arrays.sort(chars);
		getPassword(0, 0, 0, 0);
		System.out.print(sb);
	}

	private static void getPassword(int depth, int pos, int vowel, int consonant) {
		if (depth == L) {
			if ((1 <= vowel) && (2 <= consonant)) {
				for (int i = 0; i < L; i++)
					sb.append(result[i]);
				sb.append("\n");
			}
			return;
		}
		for (int i = pos; i < C; i++) {
			if (depth + (C - pos) < L) {
				return;
			}
			result[depth] = chars[i];
			if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u') {
				getPassword(depth + 1, i + 1, vowel + 1, consonant);
			} else {
				getPassword(depth + 1, i + 1, vowel, consonant + 1);
			}
		}
	}

	private static int readInt() throws IOException {
		int c;
		int n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n * 10) + (c & 15);
		if (c == 13)
			System.in.read();
		return n;
	}
}