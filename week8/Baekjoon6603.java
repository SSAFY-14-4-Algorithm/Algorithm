import java.io.*;
import java.util.*;

public class Baekjoon6603 {
    static int n;
    static int[] arr;
    static int[] ans;

    //메모리 16252kb 시간232ms
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while(true) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n==0) break;
            arr = new int[n];

            for (int i = 0; i < n; i++) {
            	arr[i] = Integer.parseInt(st.nextToken());
            }
            
            ans = new int[6];
            combi(0, 0);
            System.out.println();
        }
        
        
    }
    
    static void combi(int idx, int start) {
    	if (idx == 6) {
    		for (int a : ans) System.out.print(a + " ");
    		System.out.println();
    		return;
    	}
    	
    	for (int i = start; i < n; i++) {
    		ans[idx] = arr[i];
    		combi(idx+1, i+1);
    	}
    }

    
}
