package week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon1253 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 정렬
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int answer = 0;
        // 각 원소가 두 수의 합으로 표현되는지 투 포인터로 검사
        for (int idx = 0; idx < N; idx++) {
            int left = 0;
            int right = N - 1;
            int target = arr[idx];

            while (left < right) {
                // 타겟 무시
                if (left == idx) {
                    left++;
                    continue;
                }
                if (right == idx) {
                    right--;
                    continue;
                }

                // 두 수의 합이 타겟과 같은지 검사
                int sum = arr[left] + arr[right];
                if (sum == target) {
                    answer++;
                    break;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        sb.append(answer);
        System.out.println(sb);
    }
}
