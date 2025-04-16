
import java.io.*;
import java.util.*;

public class Baekjoon2467 {

	static int n;
	static int[] arr;
	static int min; //두 용액의 차

	//메모리 28684KB 시간 332ms
	//투 포인터 사용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); //작은 값부터 큰 값으로 오름차순

		int left = 0;
		int right = n-1;
		int ansL = 0, ansR = 0;

		//왼쪽 포인터는 오른쪽 포인터보다 작아야함
		while(left < right) {
			//현재 두 용액의 합
			int sum = arr[left] + arr[right];

			//이전에 합친 특성값이 현재 합보다 크면 현재 합으로 갱신
			if (Math.abs(sum) < min) {
				min = Math.abs(sum);
				ansL = arr[left];
				ansR = arr[right];
			}

			//포인터 옮기기
			if (sum < 0) { //합이 0보다 작으면 값 조정으로 위해 왼쪽 포인터 오른쪽으로 한칸 옮기기
				left++;
			} else { //합이 0보다 크면 오른쪽 포인터를 왼쪽으로 옮기기
				right--;
			}
		}

		System.out.println(ansL + " " + ansR);

	}

}
