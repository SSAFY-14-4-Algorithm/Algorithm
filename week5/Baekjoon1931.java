import java.io.*;
import java.util.*;


public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[][] meet = new int[n][2];
		
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			meet[i][0] = Integer.parseInt(st.nextToken());
			meet[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(meet, (a,b) -> a[1]==b[1]?a[0]-b[0]:a[1]-b[1]); //종료 시간 기준 정렬, 종료 시간이 같으면 시작이 빠른 순서로 정렬 
		
		int cnt =0;
		int EndTime = 0; //이전 회의 종료 시간 
		
		for(int i=0;i<n;i++) {
			if(meet[i][0]>=EndTime) { //겹친 회의 불가이므로 회의 끝난후에 다음 회의하는 건지 확인 
				EndTime = meet[i][1];
				cnt ++;
				
			}
		}
		
		System.out.println(cnt);
	}
	
	
}
