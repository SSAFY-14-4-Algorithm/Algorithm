/*
 * BOJ 2138번 : 전구와 스위치
 * 메모리 : 16,484kb
 * 시간 : 120ms
 * 
 * i번째 전구 바꾸려면, i-1 눌러야함
 * 0번째 전구는 앞이 없으니까
 * 0번째 누르는 경우, 안누르는 경우 생각해야 함
 */

import java.io.*;
import java.util.*;

public class BOJ2138_전구와스위치 {
	static int N;
	static int[] currStatus;
	static int[] makeStatus;
	static int answer = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		currStatus = new int[N];
		makeStatus = new int[N];

		String currInput = br.readLine();
		String makeInput = br.readLine();
		for (int n = 0; n < N; n++) {
			currStatus[n] = currInput.charAt(n)-'0';
			makeStatus[n] = makeInput.charAt(n)-'0';
		}
		
		solve(currStatus.clone(), 0);
		
		int[] firstOnStatus = currStatus.clone();
		light(firstOnStatus, 0);
		solve(firstOnStatus, 1);
		
		sb.append(answer==Integer.MAX_VALUE ? -1 : answer);
		System.out.print(sb);
		br.close();
	}
	
	public static void solve(int[] status, int count) {
		for(int n = 1; n < N; n++) {
			if(status[n-1] != makeStatus[n-1]) {
				light(status, n);
				count++;
			}
		}
		
		if(isSame(status, makeStatus)) {
			answer = Math.min(answer, count);
		}
	}
	
	public static void light(int[] status, int idx) {
		if(idx-1 >= 0) status[idx-1] = status[idx-1]==0 ? 1 : 0;
		status[idx] = status[idx]==0 ? 1 : 0;
		if(idx+1 < N) status[idx+1] = status[idx+1]==0 ? 1 : 0;
	}
	
	public static boolean isSame(int[] currStatus, int[] makeStatus) {
		for(int n = 0; n < N; n++) {
			if(currStatus[n] != makeStatus[n]) {
				return false;
			}
		}
		return true;
	}
}
