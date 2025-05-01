import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* 비슷한 단어
 * 17268kb, 132ms
 */
class Baekjoon2179 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Map<String, Integer> map = new HashMap<>(); //전체 접두사 저장 및 갯수 셀 맵
        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N]; //단어 저장
        List<String> max = new ArrayList<>(); // 공통 접두사 저장 
        int ans = 1; // 최대 길이 저장 
        
        //최대 길이 찾기
        for(int i=0;i<N;i++) {
            words[i] = br.readLine();

            for(int j=ans;j<=words[i].length();j++) {
                String s = words[i].substring(0, j); // 접두사 추출 
                int value = map.getOrDefault(s, 0) + 1;
                ans = value != 1 ? Math.max(ans, s.length()) : ans; // 2번이상 등장시 값 갱신 
                map.put(s, value); // 접두사 저장 
            }
        }
        
        // 최대길이와 같고 2개 이상 나온 최대 접두사 후보들 저장
        for(String key : map.keySet()) {
        	if(map.get(key) != 1 && key.length() == ans) {
        		max.add(key);
        	}
        }
        
        //접두사 후보를 가지고 가장 먼저 나온 2개의 단어 찾기
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for(String s : words) {
            if(s.length() < ans) continue;
            
            // 공통 접두사 리스트에 있다면 
            if(max.contains(s.substring(0, ans))) {
                cnt++;
                max.clear(); // 동일 접두사만 남기기 위해 비움 
                max.add(s.substring(0, ans)); // 현재 접두사 추가 
                sb.append(s).append("\n");
                if(cnt == 2) break; // 2개 출력하면 종료
            }
        }
        System.out.print(sb);
    }
}