import java.io.*;
import java.util.*;
/*
 * 메모리 100420KB
 * 시간 748 ms
 */
public class Baekjoon11000 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		int[][] classroom = new int[n][2];
		for(int i=0;i<n;i++) {
			String[] s = br.readLine().split(" ");
			classroom[i][0] = Integer.parseInt(s[0]);
			classroom[i][1] = Integer.parseInt(s[1]);
		}
		//시작 시간으로 정렬
		Arrays.sort(classroom,(a,b)->(a[0]-b[0]));
		
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		//첫 수업 종료시간 추가
		pq.add(classroom[0][1]);
		
		for(int i=1;i<n;i++) {
			//가장 빨리 끝나는 강의가 현재 수업 시작시간보다 작으면 기존강의 없애고 새거 추가
			if(pq.peek() <= classroom[i][0]) {
				pq.poll();
			}
			pq.add(classroom[i][1]);
		}
		System.out.println(pq.size());
	}
}
