import java.util.*;
import java.io.*;
/* 문자열 잘라내기
 * 301112kb, 956ms
 * 이분탐색 풀이 가능
 * 중복 조건이 항상 0(중복X)으로시작해서 1(중복)이 나오면 그뒤로 다 1로 나타남 ex)0000111111
 * 이런식으로 나타나기 때문에 처음 나오는 1(중복)을 찾는 문제이기 때문에
 * 이분탐색으로 풀이가 가능함
 * 민규 풀이는 이분탐색을 쓰면서, 중복 체크를 할 때 라빈카프(해쉬)를 사용해서 같은 지 체크
 */
public class Baekjoon2866 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		String[] s = new String[R];
		for(int i = 0; i < R; i++) {
			s[i] = br.readLine();
		}
		char[][] c = new char[C][R];
		for(int i = 0; i < C; i++) {
			for(int j = 0; j < R; j++) {
				c[i][j] = s[j].charAt(i);
			}
		}
		String[] ret = new String[C];
		for(int i = 0; i < C; i++) {
			ret[i] = String.valueOf(c[i]);
		}
		HashSet<String> set = new HashSet<>();
		int count = 0;
		A:for(int i = 1; i < R; i++) {
			set.clear();
			for(int j = 0; j < C; j++) {
				String sub = ret[j].substring(i);
				if(!set.contains(sub)) {
					set.add(sub);
				} else {
					break A;
				}
			}
			count++;
		}
		System.out.println(count);
	}
}
