/*
 * 이분탐색 O(NlogN)
 * 메모리 : 11,832kb
 * 시간 : 72ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon11053_NlogN {
	static int N;
	static int[] A;
	static int[] LIS;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		LIS = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
		}
		
		LIS[0] = A[0];
		int length = 1;
		for(int n = 1; n < N; n++) {
			if(A[n] > LIS[length-1]) {
				LIS[length++] = A[n];
			} else {
				int pos = binarySearch(0, length-1, A[n]);
				LIS[pos] = A[n];
			}
		}
		
		answer = length;
		sb.append(answer);
		System.out.print(sb);
		br.close();
	}
	
	private static int binarySearch(int left, int right, int target) {
		while(left < right) {
			int mid = (left+right)/2;
			if(LIS[mid] < target) {
				left = mid+1;
			} else {
				right = mid;
			}
		}
		return left;
	}
}
