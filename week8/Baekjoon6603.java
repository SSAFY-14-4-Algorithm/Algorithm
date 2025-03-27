import java.io.*;
import java.util.*;
/*
 * 메모리:14312KB	
 * 시간: 180ms
 */
public class Baekjoon6603 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] output;
	static int[] s;
	public static void main(String[] args) throws IOException{
		while(true) {
			String inp = br.readLine();
			if(inp.equals("0")) break;
			else {
				String[] s2 = inp.split(" ");
				int k = Integer.parseInt(s2[0]);
				s = new int[k];
				for(int i=0;i<k;i++) {
					s[i] = Integer.parseInt(s2[i+1]);
				}
				output = new int[6];
				Combination(0,0,k,6);
				System.out.println();
			}
		}
	}
	public static void Combination(int depth, int start, int n, int k) {
		if(depth == k) {
			for(int result : output) {
				System.out.print(result+" ");
			}
			System.out.println();
			return;
		}
		for(int i=start;i<n;i++) {
			output[depth] = s[i];
			Combination(depth+1,i+1,n,k);
		}
	}
}
