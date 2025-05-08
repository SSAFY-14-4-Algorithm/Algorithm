package week14;

import java.io.*;
import java.util.*;

/**
 * 지피티 썼슴다 물어보지 마십쇼
 *  
 * 메모리 : 121652
 * 시간 : 492
 *
 */


public class Baekjoon28423_게임 {
	
	   static Map<Integer, Integer> memo = new HashMap<>();

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        int L = Integer.parseInt(st.nextToken());
	        int R = Integer.parseInt(st.nextToken());
	        
	        int total = 0;
	        for (int n = L; n <= R; n++) {
	            total += computeG(n);
	        }
	        System.out.println(total);
	    }
	    
	    // f
	    public static int computeF(int n) {
	        String s = Integer.toString(n);
	        int sum = 0;
	        int product = 1;
	        for (int i = 0; i < s.length(); i++) {
	            int digit = s.charAt(i) - '0';
	            sum += digit;
	            product *= digit;
	        }
	        return Integer.parseInt(Integer.toString(sum) + Integer.toString(product));
	    }
	    
	    // g
	    public static int computeG(int n) {
	        if (memo.containsKey(n)) {
	            return memo.get(n);
	        }
	        
	        Set<Integer> visited = new HashSet<>();
	        int current = n;
	        
	        while (true) {
	            if (current > 100000) {
	                memo.put(n, -1);
	                return -1;
	            }
	            if (visited.contains(current)) {
	                memo.put(n, 0);
	                return 0;
	            }
	            visited.add(current);
	            int next = computeF(current);
	            if (next == current) {
	                memo.put(n, 1);
	                return 1;
	            }
	            current = next;
	        }
	    }
	}
