import java.io.*;
import java.util.*;

/**
 * 메모리: 15992KB
 * 시간108ms
 */
public class Baekjoon2138_전구와스위치 {
	// Input Handler
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	// types
	// constants
	// variables
	static int N;
	static boolean[] fromP;
	static boolean[] fromN;
	static boolean[] to;
	
	static boolean[] toBooleanArray(char[] cArr) {
		boolean[] bArr = new boolean[cArr.length];
		for(int i = 0; i < N; ++i) bArr[i] = cArr[i] == '1';
		return bArr;
	}
	
	public static void main(String[] args) throws IOException {
		N = readInt();
		char[] cArr = br.readLine().toCharArray();
		fromP = toBooleanArray(cArr);
		fromN = toBooleanArray(cArr);
		to = toBooleanArray(cArr = br.readLine().toCharArray());
			
		int cntP = 1, cntN = 0;
		fromP[0] = !fromP[0];
		fromP[1] = !fromP[1];
		
		for(int i = 1; i < N; ++i) {
			if(fromP[i-1] != to[i-1]) {
				++cntP;
				for(int j = -1; j < 2 && i + j < N; ++j) {
					fromP[i + j] = !fromP[i + j];
				}
			}
			if(fromN[i-1] != to[i-1]) {
				++cntN;
				for(int j = -1; j < 2 && i + j < N; ++j) {
					fromN[i + j] = !fromN[i + j];
				}
			}
		}
		if(fromP[N-1] == to[N-1] && fromN[N-1] == to[N-1]) {
			System.out.print(Math.min(cntP, cntN));
		} else if(fromP[N-1] == to[N-1]) System.out.print(cntP);
		else if(fromN[N-1] == to[N-1]) System.out.print(cntN);
		else System.out.print(-1);
	}
	
	static int readInt() throws IOException {
		int c, n = 0;
		while((c = System.in.read()) >= 0x30) n = (n << 3) + (n << 1) + (c & 0x0F);
		if(c == '\r') System.in.read();
		return n;
	}
}