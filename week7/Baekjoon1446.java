import java.io.*;
import java.util.*;
/*
 * 메모리 11628KB
 * 시간 72ms
 */
public class Baekjoon1446 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws IOException{
		String[] s = br.readLine().split(" ");
		int n = Integer.parseInt(s[0]);
		int d = Integer.parseInt(s[1]);
		List<int[]> shortcuts = new ArrayList<>(); 

        for (int i = 0; i < n; i++) {
            String[] s2 = br.readLine().split(" ");
            int start = Integer.parseInt(s2[0]);
            int end = Integer.parseInt(s2[1]);
            int cost = Integer.parseInt(s2[2]);
            
            if (end <= d) {//초과하는 지름길은 제외
                shortcuts.add(new int[]{start, end, cost});
            }
        }
        Drive(n,d,shortcuts);
	}
	public static void Drive(int n,int d,List<int[]> shortcuts) {
		int[] dist = new int[d+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;
		
		for(int i=0;i<d;i++) {
			//그냥 도로는 1씩감
			dist[i+1] = Math.min(dist[i+1], dist[i]+1);
			//지름길을 만났을때 거리가 짧다면 이동
			for(int[] shorts : shortcuts) {
				if(i==shorts[0] && dist[i]+shorts[2] < dist[shorts[1]]) {
					dist[shorts[1]] = dist[i] + shorts[2];
				}
			}
		}
		System.out.println(dist[d]);
	}
}
