package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon5430 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int testCase = Integer.parseInt(br.readLine());
		Deque<Integer> queue;
		
		for(int t=0;t<testCase;t++) {
			queue = new ArrayDeque<>();
			
			st = new StringTokenizer(br.readLine());
			String[] arrFunc = st.nextToken().split("");
			
			int n = Integer.parseInt(br.readLine());
			
			String str = br.readLine().replaceAll("[\\[\\]]", "");
			int prime = 0; // 0 : 정방향, 1 : 역방향, 2: 에러
			
			for(String s:str.split(",")) {
				if(!str.isEmpty()) {
					queue.addLast(Integer.parseInt(s));
				}
			}

			for(int i=0;i<arrFunc.length;i++) {
				String func = arrFunc[i];
							
				if(func.equals("R")) {
					if(prime == 0) prime = 1;
					else if(prime == 1) prime = 0;
				}
				else if(func.equals("D")) {
					if(!queue.isEmpty()) {
						if(prime == 0) queue.removeFirst();
						else if(prime == 1) queue.removeLast();
					}
					else {
						prime = 2;
						break;
					}
				}	
			}

			if (prime == 0){
				bw.write("[");
				while(!queue.isEmpty()) {
					bw.write(queue.removeFirst()+"");
					if(!queue.isEmpty()) bw.write(",");
				}
				bw.write("]\n");
			}
			else if (prime == 1){
				
				bw.write("[");
				while(!queue.isEmpty()) {
					bw.write(queue.removeLast()+"");
					if(!queue.isEmpty()) bw.write(",");
				}
				bw.write("]\n");
			}
			else if(prime == 2) bw.write("error\n");
			
		} 
		bw.flush();
		bw.close();

	}

}
