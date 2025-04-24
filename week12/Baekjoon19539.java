import java.io.*;
import java.util.StringTokenizer;

public class Baekjoon19539 {

	static int n, one, two, sum;

	//메모리 23564kb 시간 252ms
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int tree = Integer.parseInt(st.nextToken());
			sum += tree;
			one += tree % 2;
			two += tree / 2;
		}

		if (sum % 3 == 0 && one <= two) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}


	}

}
