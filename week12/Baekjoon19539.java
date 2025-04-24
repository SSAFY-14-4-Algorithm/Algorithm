import java.util.*;
import java.io.*;
/*
 * 23632kb, 244ms
 */
public class Baekjoon19539 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int twoCnt = 0;
		int oneCnt = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			twoCnt += num/2;
			oneCnt += num%2;
		}
		if(twoCnt >= oneCnt && (twoCnt-oneCnt) % 3 == 0) {
			System.out.println("YES");
		} else {
			System.out.println("NO");
		}
	}
}
