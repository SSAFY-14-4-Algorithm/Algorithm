import java.io.*;
import java.util.*;
/*
 * 메모리:17452KB
 * 시간:	140ms
 */
public class Baekjoon15927 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String s = br.readLine();
		
		if(isSame(s)) {
			System.out.println(-1);
		}
		else if(!isPalindrome(s)){
			System.out.println(s.length());
		}
		else {
			System.out.println(s.length()-1);
		}
	}
	private static boolean isSame(String s) {
		char c = s.charAt(0);
		for(int i=1;i<s.length();i++) {
			if(c!=s.charAt(i))
				return false;
		}
		return true;
	}
	public static boolean isPalindrome(String s) {
		int start = 0;
		int end = s.length()-1;
		while(start<end) {
			if(s.charAt(start)!=s.charAt(end))
				return false;
			start++;
			end--;
		}
		return true;
	}
}
