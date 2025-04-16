package study5;

import java.io.*;
import java.util.*;

/**
 * 걍 투포인터 갈기면 되는거 아닌교?
 * 
 * 메모리 : 12228 KB
 * 시간 : 88 ms
 */

public class Baekjoon2467 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        int left = 0;
        int right = N - 1;
        int sum = Integer.MAX_VALUE;
        int[] result = new int[2];
        int[] arr = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0 ; i < N ; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        
        while(left < right) {
        	int cursum = Math.abs(arr[left] + arr[right]);
    
	
        	if(sum > cursum) {
        		sum = cursum;
        		result[0] = arr[left];
        		result[1] = arr[right];
        	}
        	
        	if(arr[left] > -arr[right]) right--;
        	else if(arr[left] < -arr[right]) left++;
        	else break;
        }
        
        System.out.println(result[0] + " " + result[1]);
    }
}