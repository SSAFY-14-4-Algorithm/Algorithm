import java.io.*;
import java.util.*;
/*
 * 메모리:431148KB
 * 시간:	2540ms
 */
public class Baekjoon_7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        // 각 테스트 케이스 처리
        while (n-- > 0) {
            int k = Integer.parseInt(br.readLine());
            // 값을 키로, 빈도수를 값으로 저장할 TreeMap (자동 정렬)
            TreeMap<Integer, Integer> map = new TreeMap<>();
            
            // k개의 연산 처리
            for (int i = 0; i < k; i++) {
                String[] in = br.readLine().split(" ");
                char operator = in[0].charAt(0);      // 'I' 또는 'D'
                int num = Integer.parseInt(in[1]);
                
                if (operator == 'I') {
                	//삽입
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else if (!map.isEmpty()) {
                    // 삭제는 map이 비어있지 않을 때만 수행
                    // num이 1 이면 최댓값 삭제, 아니면 최솟값 삭제
                    int key = (num == 1 ? map.lastKey() : map.firstKey());
                    int cnt = map.get(key);
                    if (cnt == 1) {
                        // 빈도가 1이면 키 자체 제거
                        map.remove(key);
                    } else {
                        // 빈도가 2 이상이면 빈도만 1 감소
                        map.put(key, cnt - 1);
                    }
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY\n");
            } else {
                // TreeMap은 자동 정렬되므로 lastKey()가 최댓값, firstKey()가 최솟값
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
}
