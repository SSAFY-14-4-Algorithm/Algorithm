import java.io.*;
import java.util.*;

public class Baekjoon25624 {

	static int n, m;
	static String[] snupti;
	static boolean dupChar, dupStr;
	
	//메모리36556kb 시간328ms
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		snupti = new String[m];
		for (int i = 0; i < m; i++) {
			snupti[i] = br.readLine();
		}
		

		//각 문자열 내부 문자 중복 검사
		for (int i = 0; i < m; i++) {
			HashSet <Character> hs = new HashSet<>();
			for (int j = 0; j < n; j++) {
				if (hs.contains(snupti[i].charAt(j))) {
					dupChar = true;
				}
				hs.add(snupti[i].charAt(j));
			}
		}
		
		//문자열 전체 중복 검사
		HashSet<String> set = new HashSet<>();
		for (int i = 0; i < m; i++) {
		    if (!set.add(snupti[i])) {
		        dupStr = true;
		    }
		}

		
		//자리별 가능한 문자 수의 곱 == m
		List<Set<Character>> posCharSets = new ArrayList<>();
		for (int i = 0; i < n; i++) posCharSets.add(new HashSet<>());

		for (String str : snupti) {
			for (int i = 0; i < n; i++) {
				posCharSets.get(i).add(str.charAt(i));
			}
		}

		long totalCombinations = 1;
		for (Set<Character> s : posCharSets) {
			totalCombinations *= s.size();
		}

		if (dupChar || dupStr || totalCombinations != m) {
			System.out.println("NO");
		} else {
			System.out.println("YES");
			for (Set<Character> s : posCharSets) {
				StringBuilder sb = new StringBuilder();
				for (char c : new TreeSet<>(s)) {
					sb.append(c);
				}
				System.out.println(sb);
			}
		}
	}

}
