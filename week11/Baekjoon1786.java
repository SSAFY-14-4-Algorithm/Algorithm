package study5;

import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 : 84184 KB
 * 시간 : 404 ms
 */

public class Baekjoon1786 {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String t = br.readLine();
		String p = br.readLine();
		
		KMP(t,p);
	}
	static void KMP(String parent, String pattern){
		int[] table = makeTable(pattern);
		
		int n1 = parent.length(), n2 = pattern.length();
		int begin =0, matched=0;
		int cnt=0;
		StringBuilder sb = new StringBuilder();
		while(begin <= n1-n2) {
			if(matched < n2 && parent.charAt(begin+matched) == pattern.charAt(matched)) {
				++matched;
				if(matched == n2) {
					sb.append((begin+1)+" ");
					cnt++;
				}
			}else{
				if(matched ==0) {
					++begin;
				}else {
					begin += matched - table[matched-1];
					matched = table[matched-1];
				}
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
		
	}
	static int[] makeTable(String pattern) {
		int n = pattern.length();
		int[] table = new int[n];
		int idx = 0;
		for(int i=1; i<n; i++) {
			while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
				idx = table[idx-1];
			}
			if(pattern.charAt(i) == pattern.charAt(idx)) {
				table[i] = ++idx;
			}
		}
		return table;
	}
}
