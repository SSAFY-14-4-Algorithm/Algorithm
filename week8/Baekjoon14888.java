import java.util.*;
import java.io.*;

public class Baekjoon14888 {
	static int n;
	static int[] number, opt;
	static int maxAns;
	static int minAns;

	//메모리14264kb 시간104ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		maxAns = Integer.MIN_VALUE;
		minAns = Integer.MAX_VALUE;
		
		n = Integer.parseInt(br.readLine());
		
		number = new int[n];
		
		st  = new StringTokenizer(br.readLine());
		for (int i = 0 ; i < n; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		
		opt = new int[4];
		st  = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < 4; i++) {
			opt[i] = Integer.parseInt(st.nextToken());
		}
		
		cal(0, number[0]);
		
		System.out.println(maxAns);
		System.out.println(minAns);
	}
	
	static void cal(int idx, int currentResult) {
		if (idx == n-1) {
			maxAns = Math.max(maxAns, currentResult);
            minAns = Math.min(minAns, currentResult);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			if (opt[i] > 0) {
				opt[i]--;
				int nextResult = calculate(currentResult, number[idx+1], i);
				cal(idx+1, nextResult);
				opt[i]++;
			}
			
		}
	}
	
	static int calculate(int currentResult, int nextNumber, int optIdx) {
		switch(optIdx) {
		case 0:
			return currentResult + nextNumber;
		case 1:
			return currentResult - nextNumber;
		case 2:
			return currentResult * nextNumber;
		case 3:
			return currentResult / nextNumber;
		}
		return currentResult;
	}

}
