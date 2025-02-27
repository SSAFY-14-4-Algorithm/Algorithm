import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static int N;
	static int S;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//부분집합

		subset(0, 0,0);
		System.out.println(count);

	}

	static void subset(int start, int sum,int cnt) {

		if (start == N) {
			if (sum == S  && cnt>0) {
				count++;
			}
			return;
		}
		subset(start + 1, sum,cnt);

		subset(start + 1, sum + arr[start],cnt+1);


	}

}