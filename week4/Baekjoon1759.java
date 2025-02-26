import java.io.*;
import java.util.*;


public class Baekjoon1759 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] alpha;
	static char[] output;
	static int n;
	static int r;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		r = Integer.parseInt(s[0]);
		n = Integer.parseInt(s[1]);
		output = new char[r];
		alpha = br.readLine().replace(" ", "").toCharArray();
		Arrays.sort(alpha);
		Combination(0,0,0,0);
		System.out.println(sb.toString());
	}
	static boolean isVowel(char c) {
		if(c=='a' || c=='e' || c=='i' || c=='o' || c=='u')
			return true;
		else
			return false;
	}
	static void Combination(int depth,int start,int vowel,int notVowel) {
		if(depth == r) {
			//모음 한개 이상, 자음 2개 이상일때 출력
			if(vowel>=1 && notVowel>=2) {
				for(int i=0;i<output.length;i++) {
					sb.append(output[i]);
				}
				sb.append("\n");				
			}
			return;
		}
		for(int i=start;i<n;i++) {
			output[depth] = alpha[i];
			//모음이면 vowel+1 자음이면 notVowel+1
			if(isVowel(alpha[i])) 
				Combination(depth+1,i+1,vowel+1,notVowel);
			else 
				Combination(depth+1,i+1,vowel,notVowel+1);
		}
	}
}
