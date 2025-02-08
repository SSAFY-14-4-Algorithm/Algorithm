package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon18115 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		Deque<Integer> queueBefore = new ArrayDeque<>();
		Deque<Integer> queueAfter = new ArrayDeque<>();

		for(int i=n;i>0;i--) {
			queueBefore.addLast(i);
		}
		
		int[] skill = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<skill.length;i++) {
			skill[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=skill.length-1;i>=0;i--) {
			int option = skill[i];
			
			if(option == 1) {
				int num = queueBefore.removeLast();
				
				queueAfter.addLast(num);
				
			}
			else if(option == 2) {
				int num = queueBefore.removeLast();
				int firstNum = queueAfter.removeLast();
				
				queueAfter.addLast(num);
				queueAfter.addLast(firstNum);
			}
			else if(option ==3 ) {
				int num = queueBefore.removeLast();

				queueAfter.addFirst(num);
			}
		}
		
		while(!queueAfter.isEmpty()) {
			bw.write(queueAfter.removeLast()+ " ");
		}
		bw.flush();
		bw.close();
		
	}

}
