import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		for(int i=0;i<n;i++) {
			pq.offer(Integer.parseInt(br.readLine()));
		}
		int cnt =0;
		
		while(pq.size()>1) {
			int first = pq.poll();
			int second = pq.poll();
			int sum = first + second;
			cnt += sum;
			pq.offer(sum);
		}
		System.out.println(cnt);
	}
	
	
}
