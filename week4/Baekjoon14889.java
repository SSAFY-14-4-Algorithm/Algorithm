package week4;

import java.io.IOException;

public class Baekjoon14889 {
	private static int N;
	private static int[][] powerData;

	public static void main(String[] args) throws IOException {
		N = readInt();
		powerData = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				powerData[i][j] = readInt();
			}
		}
		int minDifference = Integer.MAX_VALUE;
		int teamSize = N / 2;
		for (int subset = 0; subset < (1 << N); subset++) {
			if (Integer.bitCount(subset) == teamSize) {
				int startTeam = 0;
				int linkTeam = 0;
				for (int mask1 = 0; mask1 < N; mask1++) {
					if ((subset & (1 << mask1)) == 0) {
						for (int mask2 = 0; mask2 < N; mask2++)
							if ((subset & (1 << mask2)) == 0)
								startTeam += powerData[mask1][mask2];
					} else {
						for (int mask2 = 0; mask2 < N; mask2++) {
							if ((subset & (1 << mask2)) != 0) {
								linkTeam += powerData[mask1][mask2];
							}
						}
					}
				}
				minDifference = Math.min(minDifference, Math.abs(startTeam - linkTeam));
			}
		}
		System.out.print(new StringBuilder().append(minDifference));
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
