import java.util.*;
import java.io.*;
/* 좋은 배열 만들기
 * 71804kb, 384ms
 */
public class Baekjoon2746 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N]; //기본 배열
		StringTokenizer st = new StringTokenizer(br.readLine());
		long sum = 0;
		int[] cnt = new int[1000001]; //갯수 배열
		int len = 0;
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(cnt[arr[i]] == 0) {
				len++;
			}
			cnt[arr[i]] += 1;
			sum += arr[i];
		}
		int[] arr2 = new int[len]; //중복 없는 정렬 배열
		int index = 0;
		int index2 = 0;
		for(int i = 1; i <= 1000000; i++) {
			if(cnt[i] != 0) {
				for(int j = 0; j < cnt[i]; j++) {
					arr[index++] = i;
				}
				arr2[index2++] = i;
			}
		}
		
		long ans = 0;
		long use_sum = sum;
		//마지막값이 나머지 모든 수의 합일 때
		use_sum -= arr[N-1];
		long target = use_sum - arr[N-1];
		if(target > 0) {
			int left = 0;
			int right = len-2;
			if(cnt[arr2[len-1]] > 1) {
				right = len-1;
				cnt[arr2[len-1]] -= 1;
			}
			while(left <= right) {
				long sum_lf = arr2[left] + arr2[right];
				if(sum_lf == target) {
					if(left != right) {
						ans += (long)cnt[arr2[left]] * cnt[arr2[right]];
					} else {
						ans += (long)cnt[arr2[left]] * (cnt[arr2[left]]-1)/2;
					}
					left++;
					right--;
				} else if(sum_lf < target) {
					left++;
				} else {
					right--;
				}
			}
		}
		
		//마지막 이전 값이 나머지 모든 수의 합일 때
		use_sum = sum;
		use_sum -= arr[N-1] + arr[N-2];
		target = use_sum-arr[N-2];
		if(target > 0) {
			for(int i = 0; i < N-2; i++) {
				if(arr[i] == target) {
					ans++;
				}
				if(arr[i] > target) {
					break;
				}
			}
		}
		
		//마지막 2개 전 값이 나머지 모든 수의 합일 때
		use_sum = sum;
		sum -= arr[N-1]+arr[N-2] + arr[N-3];
		if(sum == arr[N-3]) {
			ans++;
		}
		System.out.println(ans);
	}
}
