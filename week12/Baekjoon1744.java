import java.io.*;
import java.util.*;

public class Baekjoon1744 {

	static int n;
	static List<Integer> plus, minus; //양수, 음수 나누기
	static int zero, one; //0, 1개수
	static int ans;

	//메모리 14240 kb 시간 100 ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		plus = new ArrayList<>();
		minus = new ArrayList<>();
		zero = 0;
		one = 0;
		ans = 0;

		for (int i = 0; i < n; i++) {
			int cmd = Integer.parseInt(br.readLine());
			if (cmd == 0) {
				zero++;
			} else if (cmd == 1) {
				one++;
			} else if (cmd < 0) {
				minus.add(cmd);
			} else if (cmd > 1) {
				plus.add(cmd);
			}
		}

		Collections.sort(minus);
		Collections.sort(plus, Collections.reverseOrder());

		//음수 일 경우에는 짝수개면 묶어서 양수, 홀수면 0이 있으면 같이 묶기, 없으면 그냥 더하기
		//0이 있을 경우에는 음수 곱해서 0으로 만드는 방식도 고려
		for (int i = 0; i < minus.size() - 1; i += 2) {
			ans += minus.get(i) * minus.get(i + 1);
		}
		if (minus.size() % 2 == 1) {
			if (zero > 0) {
				// 음수 하나 + 0 => 무시
			} else {
				ans += minus.get(minus.size() - 1);
			}
		}

		//1일 경우에는 더하기
		ans += one;

		//2 이상 양수일 경우에는 곱하기
		for (int i = 0; i < plus.size() - 1; i += 2) {
			ans += plus.get(i) * plus.get(i + 1);
		}
		if (plus.size() % 2 == 1) {
			ans += plus.get(plus.size() - 1);
		}


		System.out.println(ans);
	}
}
