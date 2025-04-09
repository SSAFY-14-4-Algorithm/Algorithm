import java.io.*;
import java.util.*;

public class Baekjoon13549 {

	static int n, k;
	static int[] dist;
	
	//메모리 17272kb 시간 132ms
	//bfs활용
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		if (n == k) {
			System.out.println(0);
		} else {
			bfs();
			System.out.println(dist[k]);
		}
		
	}
	
	//0-1 BFS
	//간선의 가중치가 0 또는 1인 그래프에서 동작
	static void bfs() {
		Deque<int[]> dq = new ArrayDeque<>();
		dist = new int[100001];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[n] = 0;
		dq.addFirst(new int[] {n, 0});
		
		while (!dq.isEmpty()) {
			int[] now = dq.pollFirst();
			int pos = now[0];
			int time = now[1];
			
			//가중치가 0이면 덱의 front에 삽입
			if(pos * 2 <= 100000 && dist[pos * 2] > time) {
				dist[pos*2] = time;
				dq.addFirst(new int[] {pos * 2, time});
			}
			
			//가중치가 1이면 덱의 back에 삽입
			if(pos - 1 >= 0 && dist[pos - 1] > time + 1) {
				dist[pos-1] = time + 1;
				dq.addLast(new int[] {pos - 1, time + 1});
			}
			
			if(pos + 1 <= 100000 && dist[pos + 1] > time + 1) {
				dist[pos+1] = time + 1;
				dq.addLast(new int[] {pos + 1, time + 1});
			}
		}
	}
}
