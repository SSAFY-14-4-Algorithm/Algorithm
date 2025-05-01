/*
 * BOJ 22860번 : 폴더정리(Small)
 * 메모리 : 98,236kb
 * 시간 : 764ms
 * 
 * 1. 이름(폴더/파일)을 인덱싱헤서 저장 Map<String, Integer> index
 * 2. 폴더 간 연결 List<Integer>[] folder
 * 3. 폴더에 속한 파일 저장 Set<String>[] folderfile;
 * 4. dfs 탐색으로 파일 종류 수 / 전체 개수 계산
 * 4-1. 파일 이름은 Set에 추가 (종류 수)
 * 4-2. 개수는 누적해서 리턴 (전체 개수) 
 */

package algorithm;

import java.io.*;
import java.util.*;

public class BOJ22860_폴더정리 {
	static int N, M;
	static Map<String, Integer> index;
	static List<Integer>[] folder;
	static Set<String>[] folderfile;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		index  = new HashMap<>();
		folder = new ArrayList[N+M];
		folderfile = new HashSet[N+M];
		for(int n = 0; n < N+M; n++) {
			folder[n] = new ArrayList<>();
			folderfile[n] = new HashSet<>();
		}
		
		int idx = 0;
		for(int n = 0; n < N+M; n++) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken(); // 상위 폴더 이름 
			String F = st.nextToken(); // 폴더 또는 파일 이름 
			int C = Integer.parseInt(st.nextToken()); // 폴더(1), 파일(0) 
			
			if(!index.containsKey(P)) {
				index.put(P, idx++);
			}
			if(!index.containsKey(F)) {
				index.put(F, idx++);
			}
			
			if(C == 1) { 
				folder[index.get(P)].add(index.get(F)); // 폴더 연결 
			} else {  
				folderfile[index.get(P)].add(F); // 폴더-파일 연결 
			}
		}
		
		int Q = Integer.parseInt(br.readLine());
		for(int q = 0; q < Q; q++) {
			// 폴더 하위에 있는 파일의 종류, 파일의 총 개수 출력하기 
			String[] path = br.readLine().split("/");
			String target = path[path.length-1];
			int start = index.get(target);
			
			Set<String> file = new HashSet<>();
			int fileCnt = dfs(start, file);
			sb.append(file.size()).append(" ").append(fileCnt).append("\n");
		}
		System.out.print(sb);
		br.close();
	}
	
	private static int dfs(int curr, Set<String> file) {
		int cnt = 0;
		
		for(String f : folderfile[curr]) { // 현재 폴더에 직접 속한 파일 
			file.add(f);
			cnt++;
		}
		
		for(int next : folder[curr]) { // 현재 폴더의 하위 폴더 
			cnt += dfs(next, file);
		}
		
		return cnt;
	}
}
