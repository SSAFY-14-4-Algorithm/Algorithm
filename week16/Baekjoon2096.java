import java.util.*;
import java.io.*;

public class Baekjoon2096 {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		
		int N = Integer.parseInt(br.readLine());
		int resultMin = Integer.MAX_VALUE;
		int resultMax = Integer.MIN_VALUE;
		
		int[][] ori = new int[N+1][3];
		int[][] min = new int[N+1][3];
		int[][] max = new int[N+1][3];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				ori[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0;i<3;i++) {
			min[0][i] = ori[0][i];
			max[0][i] = ori[0][i];
		}
		
		for(int i=1;i<=N;i++) {
			for(int j=0;j<3;j++) {

				if(j == 0) {
					min[i][j] = Math.min(min[i-1][j+1], min[i-1][j]);
					max[i][j] = Math.max(max[i-1][j+1], max[i-1][j]);
				}else if(j == 2) {
					min[i][j] = Math.min(min[i-1][j-1], min[i-1][j]);
					max[i][j] = Math.max(max[i-1][j-1], max[i-1][j]);
				}else {
					min[i][j] = Math.min(Math.min(min[i-1][j-1], min[i-1][j]), min[i-1][j+1]);
					max[i][j] = Math.max(Math.max(max[i-1][j-1], max[i-1][j]), max[i-1][j+1]);
				}
				
				min[i][j] += ori[i][j];
				max[i][j] += ori[i][j];
			}
		}
		
		for(int i=0;i<3;i++) {
			resultMin = Math.min(resultMin, min[N][i]);
			resultMax = Math.max(resultMax, max[N][i]);
		}
		
		System.out.println(resultMax + " " + resultMin);
	}

}
