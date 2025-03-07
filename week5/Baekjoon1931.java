import java.io.*;
import java.util.*;
/* 
 * 메모리 61448KB	
 * 시간 628ms
 */
public class Baekjoon1931 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] conf;
	static int result = 0;
	public static void main(String[] args) throws IOException{
		int n = Integer.parseInt(br.readLine());
		conf = new int[n][2];
		for(int i=0;i<n;i++) {
			String[] s = br.readLine().split(" ");
			conf[i][0] = Integer.parseInt(s[0]);
			conf[i][1] = Integer.parseInt(s[1]);
		}
		//종료순으로 정렬 같으면 시작순으로 정렬
		Arrays.sort(conf,(a,b)->{
			if(a[1]==b[1]) { 
				return a[0]-b[0];
			}
			else
				return a[1]-b[1];
		});

		int end = 0;
		for(int i=0;i<n;i++) {
			//시작시간이 종료 후라면
			if(conf[i][0]>=end) {
				//회의 1추가
				result ++;
				//종료시간 변경
				end = conf[i][1];
			}
		}
		System.out.println(result);
	}
}
