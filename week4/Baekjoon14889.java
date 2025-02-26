import java.io.*;
import java.util.*;

public class Baekjoon14889 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] stat;
	static boolean[] isSelected;
	static int n;
	static int result=Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		n = Integer.parseInt(br.readLine());
		stat = new int[n+1][n+1];
		isSelected = new boolean[n+1];
		for(int i=1;i<=n;i++) {
			String[] s = br.readLine().split(" ");
			for(int j=1;j<=n;j++) {
				stat[i][j] = Integer.parseInt(s[j-1]);
			}
		}
		Combination(0,1);
		System.out.println(result);
	}
	public static void Combination(int depth,int start) {
		if(depth == n/2) {
			int startTeam=0;
			int linkTeam=0;
			//n/2만큼 선택되면 isSelected이 true인 곳이 startTeam, false인곳이 linkTeam
			for(int i=1;i<=n;i++) {
				if(isSelected[i]) {
					for(int j=1;j<=n;j++) {
						if(isSelected[j]) {
							startTeam +=stat[i][j];
						}
					}		
				}
				else {
					for(int j=1;j<=n;j++) {
						if(!isSelected[j]) {
							linkTeam +=stat[i][j];
						}
					}	
				}
			}
			result = Math.min(result, Math.abs(startTeam-linkTeam));
			return;
		}
		for(int i=start;i<=n;i++) {
			//선택된 값 인덱스위치에 true
			isSelected[i] = true; 
			Combination(depth+1,i+1);
			isSelected[i] = false;
		}
	}
}
