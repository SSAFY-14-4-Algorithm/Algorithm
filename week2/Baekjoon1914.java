import java.io.*;
import java.util.*;
import java.math.*;

public class Baekjoon1914 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());
		if(N<=20) {
			System.out.println((int)Math.pow(2, N)-1);
			Hanoi(N,1,2,3);
		}
		else {
			BigInteger bi = BigInteger.valueOf(2);
			BigInteger result = bi.pow(N).subtract(BigInteger.ONE);
			System.out.println(result);
		}
	}
	public static void Hanoi(int N,int start,int mid,int end) {
		if(N==1) {
			System.out.println(start + " " + end);
			return;
		}
		else {
			Hanoi(N-1,start,end,mid);
			Hanoi(1,start,mid,end);
			Hanoi(N-1,mid,start,end);
		}
	}
}
