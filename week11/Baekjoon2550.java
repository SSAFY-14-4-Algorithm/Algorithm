/*
 * BOJ 2550번 : 전구
 * 메모리 : 18,812kb
 * 시간 : 176ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon2550 {
	static int N;
	static int[][] arr;
	static int[] LIS;
	static int[] POS;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		int[] B = new int[N];
		Map<Integer, Integer> indexMap = new HashMap<>();

		// A 입력 + A 전구 번호 -> 인덱스 매핑
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			A[n] = Integer.parseInt(st.nextToken());
			indexMap.put(A[n], n);
		}
		// B 입력
		st = new StringTokenizer(br.readLine());
		for (int n = 0; n < N; n++) {
			B[n] = Integer.parseInt(st.nextToken());
		}

		// arr[n][0] = B 전구 번호
		// arr[n][1] = A 기준 인덱스
		arr = new int[N][2];
		for(int n = 0; n < N; n++) {
			arr[n][0] = B[n];
			arr[n][1] = indexMap.get(B[n]);
		}

		LIS = new int[N];
		POS = new int[N];
		LIS[0] = arr[0][1];
		POS[0] = 0;
		int length = 1;

		for(int n = 1; n < N; n++) {
			if(arr[n][1] > LIS[length-1]) {
				LIS[length] = arr[n][1];
				POS[n] = length;
				length++;
			} else {
				int pos = binarySearch(0, length-1, arr[n][1]);
				LIS[pos] = arr[n][1];
				POS[n] = pos;
			}
		}

		Set<Integer> set = new HashSet<>();
		int idx = length-1;

		for(int n = N-1; n >= 0; n--) {
			if(POS[n]==idx) {
				set.add(arr[n][0]); // B 전구 번호 출력
				idx--;
			}
			if(idx < 0) break;
		}

//		System.out.println(Arrays.toString(LIS));
//		System.out.println(Arrays.toString(POS));

		List<Integer> list = new ArrayList<Integer>(set);
		Collections.sort(list);

		sb.append(length).append("\n");
		for(int answer : list) {
			sb.append(answer).append(" ");
		}
		System.out.print(sb);
		br.close();
	}

	private static int binarySearch(int left, int right, int target) {
		while(left < right) {
			int mid = (left+right)/2;
			if(LIS[mid] < target) {
				left=mid+1;
			} else {
				right=mid;
			}
		}
		return left;
	}
}