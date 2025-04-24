import java.io.*;
import java.util.*;
/*
 * 메모리 11588KB
 * 시간 64ms
 */
public class Baekjoon1744 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positive.add(num);
            } else if (num <= 0) {
                negative.add(num);
            } else {
                sum += num; // 1은 더하는 것이 최적
            }
        }

        // 양수는 내림차순 정렬
        Collections.sort(positive, Collections.reverseOrder());
        // 음수는 오름차순 정렬
        Collections.sort(negative);

        int i = 0;

        // 양수 처리
        while (i < positive.size()) {
            if (i+1 < positive.size()) {
                sum += positive.get(i) * positive.get(i+1);
                i += 2;
            } else {
                sum += positive.get(i);
                i++;
            }
        }

        i = 0;

        // 음수 처리
        while (i < negative.size()) {
            if (i+1 < negative.size()) {
                sum += negative.get(i) * negative.get(i+1);
                i += 2;
            } else {
                sum += negative.get(i);
                i++;
            }
        }
		System.out.println(sum);
	}
}
