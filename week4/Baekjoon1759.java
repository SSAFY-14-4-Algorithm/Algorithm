import java.io.*;
import java.util.*;

/**
 * 
 * 알파벳을 받아가지고 정렬한 다음에 순열
 * 
 */

public class Baekjoon1759 {
	
	static int L, C;
	static char[] arr;
	static char[] result;
	static boolean[] selected;
	static StringBuilder sb = new StringBuilder();
	static int gatherCnt;
	static int consonantCnt;
	static boolean[] gather;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[C];
		result = new char[L];
		selected = new boolean[C];
		gather = new boolean[C];
		
		gatherCnt = 0;
		consonantCnt = 0;
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C ; i++) {
			String word = st.nextToken();
			arr[i] = word.charAt(0);
		
		}
		
		//증가하는 순서로 배열
		Arrays.sort(arr);
		
		// 자음이면 true, 모음이면 false
		for(int i = 0; i < C ; i++) {
			if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u')
				gather[i] = true;
		}
		
		per(0, 0);
		
		System.out.println(sb);
	}
	
	public static void per(int cnt, int idx) {
		
		// L개중 C개를 선택했을때, 자음 모음 최소 갯수
		if(cnt == L && gatherCnt >= 1 && consonantCnt >= 2) {
			
			for(int i = 0; i < L ; i++) {
				
				sb.append(result[i]);
			}
			sb.append("\n");
			
			return;		
		}
		
		if (cnt == L) return;
		
		// 이전선택 인덱 다음으로 
		for(int i = idx; i < C ; i++) {
			
			// i번재 알파벳을 선택 ㄴㄴ
			if(!selected[i]) {
				
				selected[i] = true;
				result[cnt] = arr[i];
				
				//자음 모음 카운트
				if (gather[i]) gatherCnt++;
				else consonantCnt++;
				
				//재귀
				per(cnt + 1, i + 1);
				
				// 백트레킹
				selected[i] = false;
				if (gather[i]) gatherCnt--;
				else consonantCnt--;
			}
		}
	}
}