import java.io.*;
import java.util.*;

public class Baekjoon16637 {
	static int n = 0;
	static int ans = Integer.MIN_VALUE;
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character> op = new ArrayList<>();

	//메모리 14300kb 시간 104ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		String su = br.readLine();

		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				num.add(su.charAt(i)-'0');
			} else {
				op.add(su.charAt(i));
			}
		}
		int start = num.get(0);
		dfs(0, start);
		System.out.println(ans);

	}

	static void dfs(int now, int sum) {
		if (now >= op.size()) {
			ans = Math.max(ans, sum);
			return;
		}

		int one = cal(now, sum, num.get(now+1));
		dfs(now + 1, one);

		if (now + 1 < op.size()) {
			int two = cal(now+1, num.get(now+1), num.get(now+2));
			int result = cal (now, sum, two);
			dfs(now+2, result);
		}
	}

	static int cal(int opIdx, int a, int b) {
		switch(op.get(opIdx)) {
			case '+':
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				return a / b;
		}
		return 1;
	}


}
