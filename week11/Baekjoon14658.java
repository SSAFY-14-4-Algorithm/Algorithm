import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
/*
 * 메모리 12212KB
 * 시간 92ms
 */
public class Baekjoon14658 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int m = Integer.parseInt(s[1]);
		int l = Integer.parseInt(s[2]);
		int k = Integer.parseInt(s[3]);
		int[][] stars = new int[k][2];
		for(int i=0;i<k;i++) {
			String[] star = br.readLine().split(" ");
			int x = Integer.parseInt(star[0]);
			int y = Integer.parseInt(star[1]);
			stars[i][0]= x;
			stars[i][1]= y;
		}

		int result = Integer.MIN_VALUE;
		for(int i=0;i<k;i++) {
			for(int j=0;j<k;j++) {
				//두 점을 좌표로 길이 l인 트램펄린 만들기
				int count = 0;
				int x1 = stars[i][0];
				int y1 = stars[j][1];
				for(int st=0;st<k;st++) {
					int x = stars[st][0];
					int y = stars[st][1];
					//트램펄린 안에 들어오는 별똥별이면 count증가
					if(x>=x1 && x<=x1+l && y>=y1 && y<=y1+l) {
						count++;
					}
				}
				result = Math.max(result, count);
			}
		}
		System.out.println(k-result);
	}
}
