package week4;

import java.io.IOException;

public class Baekjoon11051 {
	private static final int MOD = 10_007;

	public static void main(String[] args) throws IOException {
		int N = readInt();
		int K = readInt();

		// 팩토리얼 미리 계산
		int[] factorial = new int[N + 1];
		factorial[0] = 1;
		for (int i = 1; i <= N; i++) {
			factorial[i] = (factorial[i - 1] * i) % MOD;
		}

		// 모듈러 역원 계산
		int denominator = (factorial[K] * factorial[N - K]) % MOD; // (K! * (N-K)!)
		int denominatorInv = modInverse(denominator, MOD); // 역원 계산: (K! * (N-K)!)^(-1) mod MOD

		// 결과 계산 (N! * denominatorInv) % MOD
		System.out.println((factorial[N] * denominatorInv) % MOD);
	}

	// 모듈러 역원 계산: a^(MOD-2) % MOD
	private static int modInverse(int a, int mod) {
		return power(a, mod - 2, mod); // 페르마의 소정리 이용
	}

	// 거듭제곱 연산
	private static int power(int base, int exp, int mod) {
		int result = 1;
		while (exp > 0) {
			if ((exp & 1) == 1) { // 홀수 지수 처리
				result = (result * base) % mod;
			}
			base = (base * base) % mod; // 거듭제곱
			exp >>= 1; // 지수 나누기 2
		}
		return result;
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
