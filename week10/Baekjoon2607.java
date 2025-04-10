package AlgorithmStudy.week10;

import java.util.*;
import java.io.*;

/*
 * 메모리 : 11,708 kb
 * 실행 시간 : 72 ms
 * 
 * [해결 프로세스]
 * 1. 기준이 될 단어(첫번째 단어)에 비교할 단어(두번째 이후 단어들)의 단어 갯수를 뺌(selected)
 * 2. selected 
 * 		- 1과 -1이 하나씩 나온 경우 -> 단어 변경으로 가능
 * 		- 1이 하나 나온 경우 -> 단어 더하기로 가능
 * 		- -1이 하나 나온 경우 -> 단어 빼기로 가능
 * 		- 0, -1, 1이 아니거나 -1, 1이 여러개 나온 경우, 비슷한 단어 성립 불가
 */
public class BJ_2607_비슷한단어 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	static String word, cWord;
	static int N, result;
	static int[] isSelected, selectedCopy;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		
		isSelected = new int [26];
		
		word = br.readLine();
		for(String s : word.split("")) {
			char c = s.charAt(0);
			
			isSelected[c - 'A']++;
		}
		
		for(int i=0;i<N-1;i++) {
			
			int minus = 0;
			int plus = 0;
			int not = 0;
			String input = br.readLine();
			selectedCopy = isSelected.clone();
			
			for(String s : input.split("")) {
				char c = s.charAt(0);
				
				selectedCopy[c - 'A']--;
			}
			
			for(int j=0;j<selectedCopy.length;j++) {
				if(selectedCopy[j] == -1) minus++;
				else if(selectedCopy[j] == 1) plus++;
				else if(selectedCopy[j] != 0) not++;
			}
			
			if(not > 0) continue;
			if(minus <= 1 && plus <= 1) result++;
			
		}
		
		System.out.println(result);
	}

}
