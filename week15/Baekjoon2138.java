import java.util.*;
import java.io.*;
/* 전구와 스위치
 * 18312kb, 152ms
 */
public class Baekjoon2138 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] origin1 = br.readLine().toCharArray(); //첫번째 스위치 안 킨 경우
		char[] origin2 = new char[N];
		System.arraycopy(origin1, 0, origin2, 0, N);
		char[] target = br.readLine().toCharArray();
		
		//첫번째 스위치 눌러주기
		origin2[0] ^= 1;
		origin2[1] ^= 1;
		int cnt1 = 0, cnt2 = 1;
		for(int i = 1; i < N; i++) {
			if(origin1[i-1] != target[i-1]) {
				cnt1++;
				origin1[i-1] ^= 1;
				origin1[i] ^= 1;
				if(i+1 < N) {
					origin1[i+1] ^= 1;
				}
			}
			
			if(origin2[i-1] != target[i-1]) {
				cnt2++;
				origin2[i-1] ^= 1;
				origin2[i] ^= 1;
				if(i+1 < N) {
					origin2[i+1] ^= 1;
				}
			}
		}
		int result = -1;

		if (target[N-1] == origin1[N-1]) {
		    result = cnt1;
		}
		if (target[N-1] == origin2[N-1]) {
		    result = (result == -1) ? cnt2 : Math.min(result, cnt2);
		}

		System.out.println(result);
	}
}
