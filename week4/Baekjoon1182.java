import java.io.*;
import java.util.*;

/**
 * 
 *
 * 
 * 
 * 메모리 : 21272 KB
 * 시간 : 372 ms
 *
 */

public class Baekjoon1182 {
	
	static int N, S;
	static int[] arr;
	static int result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
			
		Arrays.sort(arr);
		
		subSet(0, 0, 0);
		
		System.out.println(result);
		
	}
	
    public static void subSet(int idx,int cnt, int sum) {
        if (idx == N && cnt > 0) {
            if (sum == S) {
                result++;
            }
            return;
        }

        if (idx == N) return;
        // 현재 원소를 선택하는 경우
        subSet(idx + 1, cnt +1, sum + arr[idx]);
        // 현재 원소를 선택하지 않는 경우
        subSet(idx + 1, cnt, sum);
    }
}