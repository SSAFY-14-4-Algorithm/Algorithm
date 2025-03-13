/*
 * BOJ 13171번 : A
 * 메모리 : 11,520kb
 * 시간 : 68ms
 */

package algorithm;
import java.io.*;
import java.util.*;

public class Baekjoon13171 {
	static long A, X;
	static final int MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		A = Long.parseLong(br.readLine());
		X = Long.parseLong(br.readLine());
		
		sb.append(pow(A%MOD, X));
		System.out.print(sb);
		br.close();
	}
	
	private static long pow(long A, long X) {
		if(X==0) {
			return 1;
		}
		
		long halfPow = pow(A, X/2);
		long squared = (halfPow*halfPow) % MOD;
		
		if(X%2==0) {
			return squared;
		} else {
			return (A*squared) % MOD;
		}
	}
}
