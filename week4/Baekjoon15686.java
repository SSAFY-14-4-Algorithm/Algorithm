import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static class Node {

		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static List<Node> home = new ArrayList<>();
	static List<Node> chicken = new ArrayList<>();
	static int M;
	static int N;
	static int max = Integer.MAX_VALUE;
	static Node[] result;

	public static void main(String[] args) throws IOException {
		/**
		 * N*N 빈칸,치킨집,집
		 * 도시의 칸은 (r,c) 형태
		 * r행 c열 1부터 시작
		 * 치킨거리 -> 집과 가장 가까운 치킨집 사이의 거리
		 * 도시의 치킨 거리 -> 모든 집의 치킨 거리의 합
		 * 사이 거리 |r1-r2| + |c1-c2|
		 *
		 *
		 * 배열에 집과 치킨집 각각 넣고
		 * 집과 집킨집의 조합중 가장 합의 값이 작은걸 고르면 될듯
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		int h = 0;
		int c = 0;
		result = new Node[M];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				String a = st.nextToken();
				if (a.equals("1")) {
					home.add(new Node(i, j));
				} else if (a.equals("2")) {
					chicken.add(new Node(i, j));
				}
			}
		}
		combination(0, 0);
		System.out.println(max);

	}

	//기저조건
	//cnt==M 치킨집 최대 개수와 같을떄
	//반복 치킨집을 M개 만큼 선택
	//선택한 치킨집과 집과의 거리 계산
	static void combination(int start, int cnt) {
		if (cnt == M) {
			int sum = 0;

			for (int i = 0; i < home.size(); i++) {
				int homeC = home.get(i).c;
				int homeR = home.get(i).r;
				int lengthSum=Integer.MAX_VALUE;
				for (int j = 0; j < result.length; j++) {
					int chickenC = result[j].c;
					int chickenR = result[j].r;
					int length = Math.abs(chickenC - homeC) + Math.abs(chickenR - homeR);
					lengthSum=Math.min(lengthSum,length);
				}
				sum = sum + lengthSum;

			}
//        System.out.println(sum);
			max = Math.min(max, sum);
			return;
		}
		for (int i = start; i < chicken.size(); i++) {
			result[cnt] = chicken.get(i);
			combination(i + 1, cnt + 1);
		}


	}
}

