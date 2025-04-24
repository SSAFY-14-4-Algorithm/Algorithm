import java.io.*;
import java.util.*;

/*
 * 메모리: 11,564KB
 * 시간: 64ms
 */
public class Baekjoon16637 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;	
	// constants
	// variables
	static int N;
	static char[] cStmt;
	static int result = Integer.MIN_VALUE;
	
	
	static int calc(int lhs, int rhs, char op) {
		switch(op) {
		case '+': return lhs + rhs;
		case '-': return lhs - rhs;
		case '*': return lhs * rhs;
		}
		return 0;
	}
	
	static void eachSubset(int idx, int leftNum, char leftOp) {
		if(idx >= N) {
			result = Math.max(result, leftNum);
			return;
		}
		
		int num1 = cStmt[idx] - '0';
		eachSubset(idx + 2, calc(leftNum, num1, leftOp) , cStmt[idx + 1]);
		if(idx != N - 1) {
			int num2 = cStmt[idx + 2] - '0';
			int concat = calc(num1, num2, cStmt[idx + 1]);
			eachSubset(idx + 4, calc(leftNum, concat, leftOp), cStmt[idx + 3]);
		}
	}
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		cStmt =  (br.readLine() + " ").toCharArray();
		eachSubset(0, 0, '+');
		System.out.print(result);
	}
}
