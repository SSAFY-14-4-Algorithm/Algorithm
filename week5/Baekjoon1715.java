
import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 29172 kb
 * 시간 424 ms
 *
 * 우선 순위 큐를 써서 합칠떄마다 큐에 넣어서 새롭게 정렬을 해야한다.
 *
 *
 */
public class Baekjoon1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>((e1, e2)-> e1 - e2);
        
        int N = Integer.parseInt(br.readLine());
        long result = 0;
        
        int[] arr = new int[N];
        
        for(int i = 0; i < N; i++) pq.offer(Integer.parseInt(br.readLine()));
        
        
        while (pq.size() != 1) {
     
        	int number1 = pq.poll();
        	int number2 = pq.poll();
        	
        	result += number1 + number2;
        	
        	pq.offer(number1 + number2);
        	
        }
        
        System.out.println(result);
    }
}