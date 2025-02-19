import java.util.*;
import java.io.*;
import java.math.*;

public class Baekjoon17471{
	static boolean[] selected;
	static int num;
	static int N;
	static ArrayList<ArrayList<Integer>> arr;
	static int answer;
	static int[] peoples;
	public static boolean bfs(boolean target, int length) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		for(int i = 1; i < N+1; i++) {
			if(selected[i] == target) {
				visited[i] = true;
				q.add(i);
				break;
			}
		}
		int cnt = 0;
		while(!q.isEmpty()) {
			int cur = q.poll();
			cnt++;
			for(int nxt : arr.get(cur)) {
				if(!visited[nxt] && selected[nxt] == target) {
					q.add(nxt);
					visited[nxt] = true;
				}
			}
		}
		if(cnt == length) {
			return true;
		}
		return false;
	}
	
	
	public static void select(int cnt, int index) {
		if(cnt == num) {
			if(bfs(true, num) && bfs(false, N-num)) {
				int temp = 0;
				for(int i = 1; i <=N; i++) {
					if(selected[i]) {
						temp += peoples[i];
					} else {
						temp -= peoples[i];
					}
				}
				answer = Math.min(answer, Math.abs(temp));
			}
			return;
		}
		if(index > N) {
			return;
		}
		selected[index] = true;
		select(cnt+1, index+1);
		selected[index] = false;
		select(cnt, index+1);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		peoples = new int[N+1];
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			peoples[i] = Integer.parseInt(st.nextToken());
		}
		arr = new ArrayList<>();
		for(int i = 0; i <=N; i++) {
			arr.add(new ArrayList<>());
		}
		for(int i = 1; i <=N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			for(int j = 0; j < m; j++) {
				arr.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		answer = Integer.MAX_VALUE;
		for(int i = 1; i <= N/2; i++) {
			selected = new boolean[N+1];
			num = i;
			select(0, 1);
		}
		if(answer == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(answer);
		}
	}
}