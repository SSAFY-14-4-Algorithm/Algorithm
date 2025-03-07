import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 11696 kb
 * 시간 76 ms
 *
 * 0 ~ 9 까지 10개의 카드를 가지고 자리수만큼 숫자 뽑기 ㅇㅈ?
 * -> 10자리 까지 만들어짐
 * -> 개수를 모르는데 어떻게 배열로 만들지 
 * -> 1000000개 배열 미리 만듬?
 * -> 못만듬 병신
 *
 */

public class Baekjoon1038 {
	
	static int N;
	static List<Long> list;
	static boolean[] selected = new boolean[10];
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        subSet(0);
        
        Collections.sort(list);
        list.remove(0);
        
        if(N >= list.size()) System.out.println(-1);
        else System.out.println(list.get(N));
        

    }
    
    public static void subSet(int idx) {
    	
    	if(idx == 10) {
    		
    		long number = 0;
    		for(int i = 9; i >= 0 ; i--) {
    			
    			if(selected[i]) {
    				number = number * 10 + i;
    			}
    		}
    		list.add(number);
    		return;
    	}
    	
    	selected[idx] = true;
    	subSet(idx + 1);
    	selected[idx] = false;
    	subSet(idx + 1);
    }
}