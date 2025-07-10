import java.io.*;
import java.util.*;

/*
 *메모리: 11608
 *시간: 68
 *
 * 그리디 
 *  
 */
public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int n = Integer.parseInt(br.readLine());
    	int[] arr = new int[n];
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=0;i<n;i++) {
    		arr[i] = Integer.parseInt(st.nextToken());
    	}
    	
    	int s = Integer.parseInt(br.readLine());
    	int cnt=0;
    	
    	for(int i=0;i<n;i++) {
    		if(s<=0) break;
    		int idx = i;
    		for(int j=i+1;j<=Math.min(n-1,i+s);j++) {
    			if(arr[j]>arr[idx]) idx = j;
    		}
    		for(int j = idx;j>i;j--) {
    			int temp = arr[j];
    			arr[j] = arr[j-1];
    			arr[j-1] = temp;
    			}
    		s-=idx-i;
    	}
    		
    	for(int num : arr) {
    		sb.append(num).append(' ');
    	}
    	System.out.println(sb);
    	
    	
 }

}