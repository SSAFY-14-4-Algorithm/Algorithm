import java.io.*;
import java.util.*;

public class Baekjoon15686 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static int result=Integer.MAX_VALUE;
	static ArrayList<int[]> home = new ArrayList<>();
	static ArrayList<int[]> chicken = new ArrayList<>();
	static int[][] dist;
	static int[] output;
	public static void main(String[] args) throws IOException{
		String[] s1 = br.readLine().split(" ");
		int n = Integer.parseInt(s1[0]);
		int m = Integer.parseInt(s1[1]);
		map = new int[n][n];
		for(int i=0;i<n;i++) {
			String[] s2 = br.readLine().split(" ");
			for(int j=0;j<n;j++) {
				map[i][j] = Integer.parseInt(s2[j]);
				//집일때 좌표값 저장
				if(map[i][j]==1) {
					home.add(new int[] {i,j});
				}
				//치킨집일때 좌표값 저장
				if(map[i][j]==2) {
					chicken.add(new int[] {i,j});
				}
			}
		}
		//치킨집에서 집까지의 거리 저장
		dist = new int[home.size()][chicken.size()];
		for(int i=0;i<home.size();i++) {
			for(int j=0;j<chicken.size();j++) {
				dist[i][j] = Math.abs(home.get(i)[0]-chicken.get(j)[0])+ Math.abs(home.get(i)[1]-chicken.get(j)[1]);
			}
		}
		for(int i=1;i<=m;i++) {
			output= new int[i];
			ChickenHouse(0,0,chicken.size(),i);			
		}
		System.out.println(result);
	}
//	부분집합으로 구현하려했는데 n개중에 1~m개 뽑는 부분집합 구현 실패
//	public static void ChickenHouse(int cnt,int n) {
//		if(cnt == n) {
//			int totaldist = 0;
//			for(int i=0;i<home.size();i++) {
//				int mindist=10000;
//				for(int j=0;j<isSelected.length;j++) {
//					if(isSelected[j]) {
//						mindist = Math.min(mindist, dist[i][j]);
//					}
//				}
//				System.out.println("mindist:"+mindist);
//				totaldist += mindist;
//			}
//			System.out.println("totaldist:"+totaldist);
//			result = Math.min(result,totaldist);
//			System.out.println("result"+result);
//			return;
//		}
//		
//		isSelected[cnt] = true;
//		ChickenHouse(cnt+1,n);
//		
//		isSelected[cnt] = false;
//		ChickenHouse(cnt,n);		
//	}
	//조합으로 구현
	public static void ChickenHouse(int depth,int start,int n,int r) {
		if(depth==r) {
			int totaldist = 0;
			//저장한 거리를 바탕으로 각 집마다 선택된 치킨집까지 거리중 최소값 totaldist에 저장
			for(int i=0;i<home.size();i++) {
				int mindist=10000;
				for(int j: output) {
					mindist = Math.min(mindist, dist[i][j]);
				}
				totaldist += mindist;
			}
			//구한 totaldist중 최소값을 결과값에 저장
			result = Math.min(result,totaldist);
			return;
		}
		for(int i=start;i<n;i++) {
			output[depth] = i;
			ChickenHouse(depth+1,i+1,n,r);
		}
	}
}