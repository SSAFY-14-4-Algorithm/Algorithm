import java.io.*;
import java.util.*;
/*
 * 메모리 23052KB
 * 시간 208ms
 */
public class Baekjoon19539 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(br.readLine());
		String[] s = br.readLine().split(" ");
		int sum=0;
		int count1=0;
		int count2=0;
		for(int i=0;i<n;i++) {
			sum+=Integer.parseInt(s[i]);
			count1+=Integer.parseInt(s[i])%2;
			count2+=Integer.parseInt(s[i])/2;
		}
		
		if(sum%3>0 || count1>count2) {
			System.out.println("NO");
		}
		else {
			System.out.println("YES");
		}
	}
}
