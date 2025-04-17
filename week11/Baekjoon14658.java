import java.util.*;
import java.io.*;
/* 별똥별
 * 14308kb, 112ms
 */
public class Baekjoon14658 {
	static class Node{
		int x, y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		st.nextToken();
		st.nextToken();
		int L = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Node[] arr = new Node[K];
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Node(x, y);
		}
		int answer = 0;
		for(int i = 0; i < K; i++) {
			for(int j = i; j < K; j++) {
				//필요없는 부분 조회 하지 않기
				if((arr[i].x-arr[j].x) * (arr[i].y-arr[j].y) > 0 || Math.abs(arr[i].x-arr[j].x) > L || Math.abs(arr[i].y-arr[j].y) > L) continue;
				int count = 0;
				int x = Math.min(arr[i].x, arr[j].x);
				int y = Math.min(arr[i].y, arr[j].y);
				for(int l = 0; l < K; l++) {
					if(K-l + count < answer) break;
					int nx = arr[l].x;
					int ny = arr[l].y;
					if(x <= nx && nx <= x+L && y <= ny && ny <= y+L) {
						count++;
					}
				}
				answer = Math.max(count, answer);
			}
		}
		System.out.println(K-answer);
	}
}