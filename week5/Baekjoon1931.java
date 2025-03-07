
import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 45924 KB
 * 시간 : 496 ms
 *
 * 시간복잡도 : O(N log N) 정렬
 *
 *  걍 끝나는 시간을 기준으로 내림차순, 끝나는 시간이 같이면 시작시간 기준으로 내림차순
 *  -> 시작시간 기준으로 내림차순하는 이유는 회의시간이 시작과 동시에 끝나는 회의들이 있기때문
 */

public class Baekjoon1931 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(arr, (e1,e2)-> e1[1] == e2[1] ? e1[0] - e2[0] : e1[1] - e2[1]);
		
		
		int result = 0;
		int start = 0;
		for(int i = 0; i < N ; i++) {
			if(start <= arr[i][0]) {
				result++;
				start = arr[i][1];
			}
		}
		
		System.out.println(result);
	}
}
