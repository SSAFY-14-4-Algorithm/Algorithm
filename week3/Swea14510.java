import java.io.*;
import java.util.*;

public class Swea14510 {
	public static int[][] map;
	public static int[] dx = {1,1,1};
	public static int[] dy = {-1,0,1};
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int i=1;i<=T;i++) {
			int n = Integer.parseInt(br.readLine());
			String[] s = br.readLine().split(" ");
			int[] trees = new int[n];
			int maxTree = 0;
			for(int j=0;j<n;j++) {
				trees[j] = Integer.parseInt(s[j]);
				maxTree = Math.max(maxTree, trees[j]);
			}
			int[] countTree = new int[2];
			int count1 = 0;
			int count2 = 0;
			for(int j=0;j<n;j++) {
				int gap = maxTree - trees[j];
				countTree[1] += gap/2;
				if(gap%2==1) countTree[0]++;
				if(gap==1) count1++;
				if(gap%2==0) count2+=gap/2;
			}
			int result = Math.min(countTree[0], countTree[1])*2;
			countTree[0] -= result/2;
			countTree[1] -= result/2;		
			if(count1>count2 && countTree[0]>countTree[1]) {
				int only1 = count1-count2;
				result += ((countTree[0]+countTree[1]-only1)*2)/3*2 + ((countTree[0]+countTree[1]-only1)*2)%3;			
				result += only1*2-1;
			}
			else {
				result += (countTree[0]+countTree[1]*2)/3*2 + (countTree[0]+countTree[1]*2)%3;		
			}
			sb.append("#"+i+" "+result+"\n");
		}
		System.out.print(sb.toString());
	}
}