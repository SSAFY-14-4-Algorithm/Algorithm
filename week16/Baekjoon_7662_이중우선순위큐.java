/*
 * BOJ 7662번 : 이중 우선순위 큐
 * 메모리 : 440,392kb
 * 시간 : 2,412ms
 */

import java.io.*;
import java.util.*;

public class Baekjoon_7662_이중우선순위큐 {
	static int T, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			K = Integer.parseInt(br.readLine());
			// 숫자의 개수 저장 
			TreeMap<Integer, Integer> map = new TreeMap<>(); // O(logN) 정렬이 된다!!!
			
			for(int k = 0; k < K; k++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int num = Integer.parseInt(st.nextToken());
				
				switch(command) {
				case "I" :
					map.put(num, map.getOrDefault(num, 0)+1); // num이 존재하면 num 반환, 없다면 0 반환 
					break;
				case "D" :
					if(!map.isEmpty()) {
						int target = 0;
						if(num == 1) {
							target = map.lastKey();
						} else {
							target = map.firstKey();
						}
						
						if(map.get(target)==1) {
							map.remove(target);
						} else {
							map.put(target, map.get(target)-1);
						}
					}
					break;
				}
			}
			
			if(map.isEmpty()) {
				sb.append("EMPTY").append("\n");
			} else {
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
			}
		}
		System.out.print(sb);
		br.close();
	}
}
