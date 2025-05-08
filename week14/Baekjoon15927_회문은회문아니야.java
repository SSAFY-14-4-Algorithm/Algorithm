package week14;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 17088
 * 시간 : 152
 *
 */


public class Baekjoon15927_회문은회문아니야 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String words = br.readLine();
		
		int N = words.length();
		int result = -1;
		
		if(!check(words, N)) result = N;
		else if(allSame(words)) result = -1;
		else result = N -1;
		
		System.out.println(result);
	}

	public static boolean check(String word, int m) {
		
		for(int i = 0; i < m / 2 ; i++) {
			
			if(word.charAt(i) != word.charAt(m - i - 1)) return false;
		}
		
		return true;
	}
    public static boolean allSame(String s) {
        char first = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != first) return false;
        }
        return true;
    }
}
