import java.util.*;
import java.io.*;
/* 용액
 * 28492kb, 328ms
 */
public class Baekjoon2467 {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int left = 0;
		int right = N-1;
		
		int min = Integer.MAX_VALUE;
		int[] answer = new int[2];
		answer[0] = arr[left];
		answer[1] = arr[right];
		while(left < right) {
			int sum = arr[left] + arr[right];
			if(min > Math.abs(sum)) {
				min = Math.abs(sum);
				answer[0] = arr[left];
				answer[1] = arr[right];
			}
			if(sum < 0) {
				left++;
			} else if(sum > 0) {
				right--;
			} else {
				break;
			}
		}
		System.out.println(answer[0] + " " + answer[1]);
	}
}	
