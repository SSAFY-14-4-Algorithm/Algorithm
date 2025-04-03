import java.io.*;
import java.util.*;
/*
 * 메모리 11556KB
 * 시간 68ms
 */
public class Baekjoon1138 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		//사람들을 넣어야하는 빈 배열
		int[] people = new int[n];
		String[] s = br.readLine().split(" ");
		for(int i=0;i<n;i++) {
			int zeroCount = 0;
			for(int j=0;j<n;j++) {
				//s[i] 는 나보다 큰 사람이 왼쪽에 몇명있는지를 담은 배열
				//작은 값부터 시작
				//앞에서부터 0 개수(빈 자리)를 세고 그 값이 s[i]랑 같으면 그 다음 빈자리(0인 자리)에 사람을 세움 
				if(Integer.parseInt(s[i])==zeroCount && people[j]==0) {
					people[j] = i+1;
					break;
				}

				if(people[j]==0)
					zeroCount++;
			}
		}
		for(int i=0;i<n;i++) {
			System.out.print(people[i]+" ");
		}
	}
}
