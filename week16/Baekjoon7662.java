import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * lazy 처리 요약
 * 우선순위 큐 2개를 만들고,
 * 삭제 연산을 바로 반영하지 않고, map에 기록해두고,
 * 큐에서 꺼낼 때 그 값이 삭제된 값이면 무시하는 방법
 *
 * deleted map 에서 value 값을 삭제 요청을 count 하는게 아닌, 현재 남아 있는 갯수로 사용해야 함
 * 삭제 요청으로 관리하면, 출력할 때, maxPQ에서 deleted 를 쓰면서 minPQ가 영향을 받는다
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(reader.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            int k = Integer.parseInt(reader.readLine());

            PriorityQueue<Integer> minPQ = new PriorityQueue<>(); //오름차순
            PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder()); //내림차순
            countMap = new HashMap<>(); //삭제된 값 관리, key, value = 남은 갯수

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(reader.readLine());
                char order = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (order == 'I') { //삽입
                    minPQ.offer(num);
                    maxPQ.offer(num);
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                } else { //삭제
                    if (num == -1) delete(minPQ); //최솟값 삭제
                    else delete(maxPQ); //최댓값 삭제
                }
            }

            Integer max = getValid(maxPQ);
            Integer min = getValid(minPQ);

            if(max == null || min == null) sb.append("EMPTY").append("\n");
            else sb.append(max).append(" ").append(min).append("\n");
        }
        System.out.print(sb);
    }

    static HashMap<Integer, Integer> countMap;

    //pq와 저장된 deleted를 통해 lazy deletion을 하는 함수
    static void delete(PriorityQueue<Integer> pq) {

        while (!pq.isEmpty()) {
            int key = pq.poll();
            int count = countMap.getOrDefault(key,0); //없으면 0
            if(count > 0) { //삭제할 값이 있으면
                countMap.put(key, count-1);
                break;
            }
        }
    }

    static Integer getValid(PriorityQueue<Integer> pq) {
        while (!pq.isEmpty()) {
            int key = pq.peek();
            int count = countMap.getOrDefault(key,0);
            if(count > 0) return key; //유효한 값일 경우
            else pq.poll(); //삭제된 값이면 무시
        }
        return null;
    }
}

