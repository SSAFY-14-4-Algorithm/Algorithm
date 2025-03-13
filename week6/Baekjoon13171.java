package test;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리: 11500
 * 시간: 64	
 *
 */

public class Baekjoon13171 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Long A = Long.parseLong(br.readLine());
		Long X = Long.parseLong(br.readLine());
		long result = 1L;
		final int MOD = 1_000_000_007;
		A %= MOD;
		
		String binartString = Long.toBinaryString(X);
		char[] arr = binartString.toCharArray();
		
		for(int i = arr.length - 1; i >= 0 ; i--) {
			
			int value = arr[i] - '0';
			
			if(value == 1) {
				result = (result * A) % MOD;
			}
			
			A = (A * A) % MOD;
		}
		
		System.out.println(result);
	}
}
