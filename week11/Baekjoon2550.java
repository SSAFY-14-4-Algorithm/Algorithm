import java.io.*;
import java.util.*;

/*
 * 메모리: 14,080KB
 * 시간: 108ms
 */
public class Baekjoon2550 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	// types
	// constants
	static final int SIZE =  10_000;
	// variables
	static int N;
	static int[] switchToNo = new int[SIZE];
	static int[] bulbToNo = new int[SIZE];
	//static int[] noToSwitch = new int[SIZE+1];
	static int[] noToBulb = new int[SIZE+1];
	
	static int[] A = new int[SIZE];
	static int len = 0;
	static int[] buffer = new int[SIZE];
	static int[] dp = new int[SIZE];
	static int[] lis = new int[SIZE];
	
	/*
	 * A[i]: i번째 스위치에 대응하는 전구의 위치
	 * LIS: A로 생성한 LIS
	 * LIS[i]를 번호로 매핑한 값이 최종 결과
	 */
	
	static int lowerBound(int lo, int hi, int key) {
		while(lo < hi) {
			int mid = (lo + hi) >> 1;
			if(buffer[mid] < key) lo = mid + 1;
			else hi = mid;
		}
		return lo;
	}
	
	static void solution() {
		for(int i = 0; i < N; ++i) {
			int at = lowerBound(0, len, A[i]);
			if(at == len) ++len;
			buffer[at] = A[i];
			dp[i] = at;
		}
		for(int i = N - 1, j = len - 1; j >= 0; --i) {
			if(dp[i] == j) {
				lis[j--] = bulbToNo[A[i]];
			}
		}
		Arrays.sort(lis, 0, len);
	}
	
	public static void main(String[] args) throws IOException {
		// Input
		N = readInt();
		for(int i = 0; i < N; ++i) {
			switchToNo[i] = readInt();
			//noToSwitch[switchToNo[i]] = i;
		}
		for(int i = 0; i < N; ++i) {
			bulbToNo[i] = readInt();
			noToBulb[bulbToNo[i]] = i;
		}
		for(int i = 0; i < N; ++i) A[i] = noToBulb[switchToNo[i]];
		
		// solution
		solution();
		
		// output
		sb.append(len).append("\n");
		for(int i = 0; i < len; ++i) sb.append(lis[i]).append(" ");
		System.out.print(sb);
	}
	
	static int readInt() throws IOException{
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}