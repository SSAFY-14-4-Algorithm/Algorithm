import java.util.*;
import java.io.*;
import java.math.*;

public class Baekjoon1914{
	static StringBuilder sb = new StringBuilder();
	
	public static void hanoi(int cnt, int loc, int rest, int go) {
		if(cnt > 0) {
			hanoi(cnt-1, loc, go, rest);
			sb.append(loc).append(" ").append(go).append("\n");
			hanoi(cnt-1, rest, loc, go);
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		BigInteger answer = BigInteger.TWO.pow(N).subtract(BigInteger.ONE);
		if(N <= 20) {
			sb.append(answer).append("\n");
			hanoi(N, 1, 2, 3);
			System.out.print(sb);
		} else {
			System.out.println(answer);
		}
	}
}