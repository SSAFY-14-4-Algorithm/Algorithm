import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// 메모리 53096 KB 실행시간 600ms
// 그리디하게 마지막 시간을 기준으로 정렬후 사용하면 될것같다.
public class Baekjoon1931 {

	public static class Node implements Comparable<Node>{
		int x;
		int y;
		public Node(int x,int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Node o) {
			if (this.y == o.y) {
				return this.x - o.x;
			}
			return this.y - o.y;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		List<Node> list = new ArrayList<>();
		int cnt = 0;

		for (int i = 0 ; i < n ; i ++ ) {
			String[] input = br.readLine().split(" ");

			list.add(new Node(Integer.parseInt(input[0]),Integer.parseInt(input[1])));
		}
		Collections.sort(list);

		int maxIndex = 0;
		for (Node tmp : list) {
			if (tmp.x >= maxIndex) {
				cnt ++ ;
				maxIndex = tmp.y;
			}
		}

		System.out.println(cnt);


	}
}
