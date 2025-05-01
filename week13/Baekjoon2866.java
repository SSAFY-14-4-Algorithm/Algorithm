import java.io.*;
import java.util.*;
/*
 * 메모리 304176KB
 * 시간 1040ms
 */
public class Baekjoon2866 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int r = Integer.parseInt(s[1]);
		int c = Integer.parseInt(s[0]);
		char[][] str = new char[r][c];
		//열을 행으로 저장
		/* ex)
		 * da
		 * od
		 * ba
		 * at
		 * ra
		 * zk
		 */
		for(int i=0;i<c;i++) {
			char[] ch = br.readLine().toCharArray();
			for(int j=0;j<r;j++) {
				str[j][i] = ch[j];
			}
		}
		Set<String> set = new HashSet<>();
		int count = -1;
		A:for(int i=0;i<c;i++) {
			for(int j=0;j<r;j++) { 
				//i~c까지 부분집합 st
				String st = String.valueOf(str[j], i, c-i);
				//중복이면 break
				if(set.contains(st)) {
					break A;
				}
				//중복이 아니면 저장
				else {
					set.add(st);
				}
			}
			//메모리 초과 방지를 위해 한번 돌면 clear
			set.clear();
			count++;
		}
		System.out.println(count==-1?0:count);
	}
}
