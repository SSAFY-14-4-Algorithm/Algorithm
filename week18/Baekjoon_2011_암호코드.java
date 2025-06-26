import java.io.*;
import java.util.*;
import java.util.function.BiFunction;

/*
 * https://www.acmicpc.net/problem/2011
 * 시간: 64ms
 * 메모리:11520KB
 */
public class Baekjoon_2011_암호코드 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	static final int MOD = 1_000_000;
	// variables
	static char[] password;
	
	static int solution() {
		if(password[0] == '0') return 0;
		
 		int one = 1, two = 0;
		for(int i = 1 ; i < password.length; ++i) {
			int prevDigit = password[i-1] & 0x0F;
			int curDigit = password[i] & 0x0F;
			if(prevDigit == 0 && curDigit == 0) return 0;
			
			int nextOne = curDigit != 0 ? one + two : 0;
			int nextTwo = prevDigit != 0 && prevDigit * 10 + curDigit <= 26 ? one : 0;
			
			one = nextOne % MOD;
			two = nextTwo;
		}
		return (one + two) % MOD;
	}
	
	public static void main(String[] args) throws IOException {
		password = br.readLine().toCharArray();
		System.out.println(solution());
		
	}
}