package AlgorithmStudy.week6;

import java.util.*;

public class Baekjoon13171 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		long A = sc.nextLong();
		long X = sc.nextLong();
		final long MOD = 1_000_000_007;
		
		System.out.println(mod(A,X,MOD));
		
		sc.close();
		
	}
	private static long mod(long base, long ex, long mod) {
		
		long result = 1;
		base = base % mod;
		
		while(ex > 0) { // 지수가 홀수인 경우 결과에 현재 베이스 곱하기
			 
			if(ex % 2 != 0) {
				result = result * base % mod;
			}
			
			ex = ex / 2 ;  // 지수를 반으로 나누기
			
			base = base * base % mod;   // 베이스를 제곱하기
			
		}
		
		return result;
	}

}
