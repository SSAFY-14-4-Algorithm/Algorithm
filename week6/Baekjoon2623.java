import java.io.*;
import java.util.*;

/*
 * 메모리 15088KB
 * 시간 152ms
 * 
 */

public class Baekjoon2623{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		List<Integer>[] graph = new ArrayList[N+1];
		int[] inDegree = new int[N+1]; //진입차수를 기록하기 위함
		
		for(int i=0;i<N+1;i++) {
			graph[i] = new ArrayList<>();
			
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int prev = Integer.parseInt(st.nextToken());
			
			for(int j=1;j<num;j++) { //각 피디가 조사해 온 순서 입력받기 
				int next = Integer.parseInt(st.nextToken());
				graph[prev].add(next);
				inDegree[next]++;
				prev = next;
			}
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		List<Integer> res = new ArrayList<>();
		
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) queue.add(i); //진입차수가 0인 가수 큐에 모두 넣기. 
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			res.add(cur);
			
			for(int next: graph[cur]) {
				inDegree[next]--;
				if(inDegree[next]==0) {
					queue.add(next);
				}
				
			}
		}
		if(res.size() < N) { //정렬이 불가능한 경우 검사(res에 모든 가수 없으면 위상 정렬이 제대로 동작 x인 것). 
			System.out.println(0);
		} else{
			for(int x: res) {
				System.out.println(x);
			}
		}
	}
}
	
