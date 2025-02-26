import java.io.*;
import java.util.*;

public class Baekjoon11051 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		//파스칼 삼각형 용 배열 생성
		int[][] pascalTriangle = new int[n+1][m+1];
		for(int i=0;i<=n;i++) {
			for(int j=0;j<=m;j++) {
				if(i==j || i==0 || j==0) {
					pascalTriangle[i][j] = 1;
				}
				else{
					//파스칼 삼각형 nCk = n-1Ck-1 + n-1Ck
					//모듈러 연산을 위해 각각 10007의 나머지 더함
					pascalTriangle[i][j] = pascalTriangle[i-1][j-1]%10007+pascalTriangle[i-1][j]%10007;
				}
			}
		}
		System.out.println(pascalTriangle[n][m]%10007);
	}
}