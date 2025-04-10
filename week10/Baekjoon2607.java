import java.io.*;
import java.util.*;
/*
 * 메모리:11468KB
 * 시간:68ms
 */
public class Baekjoon2607 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		String first = new String();;
		String[] words = new String[n-1];
		for(int i=0;i<n;i++) {
			if(i==0)
				first = br.readLine();
			else
				words[i-1] = br.readLine();
		}
		int[] countingFirst = new int[26];
		for(char c : first.toCharArray()) {
			//첫 단어의 문자 개수 저장
			countingFirst[c-'A']++;
		}
		int count = 0;
		for(String word : words) {
			int[] countingWord = new int[26];
			for(char c : word.toCharArray()) {
				//첫 단어의 문자 개수 저장
				countingWord[c-'A']++;
			}		
			int different = 0;
			//문자 차이 수 저장
			for(int i=0;i<26;i++) {
				different += Math.abs(countingFirst[i]-countingWord[i]);
			}
			//different==0: 다르지 않거나 ex)DOG DOG
			//different==1: 하나만 추가/삭제하거나 ex)DOG DG
			//different==2 && first.length()==word.length(): 하나 교체하면 같은 단어가 되면 ex)DOG DOL
			// count증가
			if(different==0 || different==1 || (different==2 && first.length()==word.length()))
				count++;
		}
		System.out.println(count);
	}
}
