import java.io.*;
import java.util.*;
/*
 * 메모리:101520KB
 * 시간: 2420ms
 */
public class Baekjoon1786 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		String T = br.readLine();
		String P = br.readLine();
		int n = T.length();
		int m = P.length();

		int[] pi = new int[m];
		// 접두사 배열(pi) 만들기
		for(int i=1,j=0;i<m;i++) {
			while(j>0 && P.charAt(i)!=P.charAt(j)) 
				j = pi[j-1];
			if(P.charAt(i)==P.charAt(j)) 
				pi[i] = ++j;
		}

		ArrayList<Integer> res = new ArrayList<>();
		// KMP 매칭 수행
		for(int i=0,j=0;i<n;i++) {
			while(j>0 && T.charAt(i)!=P.charAt(j)) j = pi[j-1];
			if(T.charAt(i)==P.charAt(j)) {
				if(j==m-1) {
					res.add(i-m+2); // 1-based 위치
					j = pi[j];
				}else j++;
			}
		}

		System.out.println(res.size());
		
		for(int v : res) 
			System.out.print(v+" ");
	}
}
