package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon1062 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		if (K < 5) {
			System.out.print(0);
			return;
		}
		int bit_antatica = stringToBit("antic");
		int[] bit_words = new int[N];
		for (int i = 0; i < N; i++) {
			bit_words[i] = stringToBit(br.readLine());
		}
		int maxCount = 0;
		for (int subset = 0; subset < (1 << 26); subset++) {
			if ((subset & bit_antatica) == bit_antatica) {
				if (Integer.bitCount(subset) == K) {
					int tempCount = 0;
					for (int i = 0; i < N; i++)
						if ((subset & bit_words[i]) == bit_words[i])
							tempCount++;
					maxCount = Math.max(maxCount, tempCount);
				}
			}
		}
		System.out.print(new StringBuilder().append(maxCount));
	}

	private static int stringToBit(String str) {
		int bitData = 0;
		for (int i = 0; i < str.length(); i++) {
			bitData |= 1 << (str.charAt(i) - 'a');
		}
		return bitData;
	}
}
