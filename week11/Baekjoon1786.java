import java.io.*;
import java.util.*;
/*
 * 메모리: 98176KB
 * 시간: 2384ms
 * 
 * kmp 알고리즘 
 * 
 */

public class Baekjoon1786{
    static List<Integer> result = new ArrayList<>();
    static int count = 0;
    public static void main(String[] args) throws IOException {        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String T = br.readLine(); 
        String P = br.readLine();
        
        kmp(T, P);
    }
    public static void kmp(String text, String pattern) {
        int[] t = table(pattern);
        int n1 = text.length();
        int n2 = pattern.length(); 
        int begin = 0, matched= 0;
        int cnt = 0;
        
        StringBuilder sb = new StringBuilder();
        while (begin<=n1-n2) {
           if(matched < n2 && text.charAt(begin+matched)==pattern.charAt(matched)) {
        	   ++matched;
        	   if(matched == n2) { //m글자가 모두 일치시 답에 추가 
        		   sb.append((begin+1)+" ");
        		   cnt++;
        	   	}
           }else {
        		   if(matched==0) { //matched가 0 이면 다음칸
        			   ++begin;
        		   }else {
        			   begin+=matched-t[matched-1];
        			   matched = t[matched-1];
        			   
        		   }
        	   }
           }
           System.out.println(cnt);
           System.out.println(sb.toString());
    }

    public static int[] table(String pattern) {
    	int n = pattern.length();
        int[] table = new int[n];
        int idx = 0;
        for(int i=0;i<n;i++) {
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
