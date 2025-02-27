package week4;

import java.util.*;
import java.io.*;

public class BaekJoon1759 {
	static char[] gather = {'a', 'e', 'i', 'o', 'u'};
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static char[] alphabet;
	static char[] result;
	static boolean[] isSelected;
	static int L,C;
	
	// 모음 갯수 체크
	private static boolean CheckCount() {
		
		int gatherCnt = 0; // 모음 갯수
		for(int i=0;i<L;i++) {
			for(int j=0;j<gather.length;j++) {	
				// 최소 모음 갯수 체크
				if(result[i] == gather[j]) gatherCnt++;
			}
		}
		
		// 최소 모음, 자음 갯수가 조건에 맞지 않는 경우(L-2 => 자음이 최소 두 개 존재)
		if(gatherCnt < 1 || L-2 < gatherCnt) return false;
		
		return true;
	}
	
	private static void Combination(int depth, int start) {
		
		if(depth == L) { 
			if(CheckCount()) {
				for(int i=0;i<depth;i++) {
					sb.append(result[i]);
				}
				sb.append("\n");
			}
			
			return;
		}
		
		for(int i=start;i<C;i++) {

			result[depth] = alphabet[i];
			Combination(depth+1, i+1);
		}
	}
	
	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		alphabet = new char[C];
		result = new char[L];
		isSelected = new boolean[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			alphabet[i] = st.nextToken().charAt(0);
		}
		
		// 정렬
		Arrays.sort(alphabet);
		
		// 조합
		Combination(0, 0);
		
		System.out.println(sb);
	}

}