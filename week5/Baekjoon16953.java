import java.io.*;
import java.util.*;
/*
 * 메모리 11556KB
 * 시간 64ms
 */
public class Baekjoon16953 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int a = Integer.parseInt(s[0]);
		int b = Integer.parseInt(s[1]);
		int count=1;
		while(a<b) {
			//끝자리 1이면 /10
			if(b%10==1)
				b /=10;
			//2의 배수이면 /2
			else if(b%2==0)
				b /=2;
			//둘다 할수없으면 종료
			else
				break;
			count++;
		}
		if(a==b)
			System.out.println(count);
		else
			System.out.println(-1);
	}
}
