import java.util.*;
import java.io.*;
/* 좋다
 * 15036kb, 160ms
 */
public class Baekjoon1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N <= 2) {
			System.out.println(0);
		} else {
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int cnt = 0;
			for(int i = 0; i < N; i++) {
				int l = 0;
				int r = N-1;
				int target = arr[i];
				while(l < r) {
					int sum = arr[l] + arr[r];
					if(sum == target) {
						if(l == i) {
							l++;
						} else if(r == i) {
							r--;
						} else {
							cnt++;
							break;
						}
					} else if(sum < target) {
						l++;
					} else {
						r--;
					}
				}
			}
			System.out.println(cnt);
		}
	}
}
