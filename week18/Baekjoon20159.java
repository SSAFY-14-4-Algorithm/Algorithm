import java.util.*;
import java.io.*;
/* 동작 그만. 밑장 빼기냐?
 * 24588kb, 268ms
 */
public class Baekjoon20159 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] sum = new int[N];
		sum[0] = Integer.parseInt(st.nextToken());
		sum[1] = Integer.parseInt(st.nextToken());
		for(int i = 2; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum[i] = sum[i-2] + arr[i];
		}
		int ans = Math.max(sum[N-2], sum[N-1]); //밑장 빼기 없는 경우와 첫번째턴에 밑장을 뺐을 때 비교
		ans = Math.max(ans, sum[0] + (N-3 >= 0 ? sum[N-3] : 0)); //2번째장 밑장 빼기
		for(int i = 2; i < N; i++) { //언제 밑장 빼는 지 고르기
			if(i % 2 == 0) { //내가 받는 타이밍에 밑장 빼기
				ans = Math.max(ans, sum[i-2] + sum[N-1]-sum[i-1]);
			} else { //상대 턴에 밑장 빼기
				ans = Math.max(ans, sum[i-1] + sum[N-3]-sum[i-2]);
			}
		}
		System.out.println(ans);
	}
}
