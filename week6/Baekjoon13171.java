import java.util.*;
import java.io.*;
/*
 * 14248kb, 104ms
 */

//분할 정복을 이용한 거듭제곱
public class Baekjoon13171 {
	static long num = 1_000_000_007;
	public static long DaC(long A, long X) {
		if(X == 1) {
			return A;
		}
		long ret = DaC(A, X/2);
		ret = (ret * ret) % num;
		if(X % 2 == 1) {
			ret = (ret * A) % num;
		}
		return ret;
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long A = Long.parseLong(br.readLine());
		long X = Long.parseLong(br.readLine());
		System.out.println(DaC(A%num, X));
	}
}