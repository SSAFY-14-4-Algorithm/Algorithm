import java.io.*;
import java.util.*;

/*
 * 메모리: 31,764KB
 * 시간: 252ms
 */
public class Baekjoon2467 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st = null;
	// constants
	// variables
	static int N;
	static long[] potions;
	
	static long[] solution(long[] potions) {
		Arrays.sort(potions);
		if(potions[0] >= 0) {
			return new long[] {potions[0], potions[1]};
		} else if(potions[potions.length - 1] <= 0) {
			return new long[] {potions[potions.length - 2], potions[potions.length - 1]};
		}
		
		
		int minL = 0, minR = potions.length - 1;
		long minVal = potions[minL] + potions[minR];
		{
			int l = minL, r = minR;
			while(l + 1 < r) {
				long val = potions[l] + potions[r];
				if(val < 0) {
					++l;
				} else {
					--r;
				}
				val = potions[l] + potions[r];
				if(Math.abs(val) < Math.abs(minVal)) {
					minL = l;
					minR = r;
					minVal = val;
				}
			}
		}
		return new long[] {potions[minL], potions[minR]};
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		potions = new long[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; ++i) potions[i] = Long.parseLong(st.nextToken());
		
		long[] ret = solution(potions);
		bw.append(ret[0] + " " + ret[1]).flush();
	}
}
	