import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Baekjoon1914 {
	static Scanner sc;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void hanoi(int N, int start, int to, int end) throws IOException{
		if(N == 1) {
			bw.write(start+" "+end+"\n");
			return;
		}
		
		hanoi(N-1, start, end, to);
		
		bw.write(start+" "+end+"\n");
		
		hanoi(N-1, to, start, end);
	}

	public static void main(String[] args) throws IOException{

		int n = Integer.parseInt(br.readLine());
		
		BigInteger count =  BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE);
		
		bw.write(count+"\n");
		if(n<=20) hanoi(n, 1, 2, 3);
		
		bw.flush();
		bw.close();
		br.close();
	}

}
