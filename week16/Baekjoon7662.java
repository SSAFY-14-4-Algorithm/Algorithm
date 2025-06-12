
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


//메모리 448644kb 시간 2888ms
//이중 우선순위 큐
public class Baekjoon7662 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());

		for (int test = 0; test < t; test++) {
			int input = Integer.parseInt(br.readLine());

			Queue<Integer> minHeap = new PriorityQueue<>(); //최소힙
			Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder()); //최대힙

			Map<Integer, Integer> countMap = new HashMap<>();

			for(int i=0; i<input; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op = st.nextToken();

				if (op.equals("I")) {
					int num = Integer.parseInt(st.nextToken());
					maxHeap.add(num);
					minHeap.add(num);
					//heap간의 동기화를 위해 사용
					countMap.put(num, countMap.getOrDefault(num, 0) + 1);
				} else if (op.equals("D")) {
					int type = Integer.parseInt(st.nextToken());

					if (countMap.isEmpty()) continue;
					//1일 때 최대값 삭제, 아닐때 최소값 삭제
					if(type == 1) { //최댓값 삭제
						remove(maxHeap, countMap);
					}else { // 최솟값 삭제
						remove(minHeap, countMap);
					}
				}
			}

			// countMap이 비어있으면 큐가 비어있는 것
			if (countMap.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				// 각 힙에 있는 유효하지 않은(다른 힙에서 삭제된) 원소들을 모두 제거
				int maxVal = syncAndPeek(maxHeap, countMap);
				int minVal = syncAndPeek(minHeap, countMap);
				System.out.println(maxVal + " " + minVal);
			}
		}
		
	}

	static void remove(Queue<Integer> heap, Map<Integer, Integer> countMap) {
		while (!heap.isEmpty()) {
			int num = heap.peek();
			// countMap에 없는 유령 값이면 힙에서 그냥 제거
			if (countMap.getOrDefault(num, 0) == 0) {
				heap.poll();
				continue; //유효한 값을 만날 때까지 반복
			}

			// 유효한 값(삭제할 값)이면 개수를 1 줄이고 힙에서 제거한 후 종료
			countMap.put(num, countMap.get(num) - 1);
			if (countMap.get(num) == 0) {
				countMap.remove(num);
			}
			heap.poll();
			break;
		}
	}


	static int syncAndPeek(Queue<Integer> heap, Map<Integer, Integer> countMap) {
		while (!heap.isEmpty()) {
			//다른 힙에서 이미 삭제된 값일 수 있으므로,
			//countMap으로 유효성 검증
			int num = heap.peek();
			// 유효한 값이면(countMap에 존재하면) 바로 그 값을 반환
			if (countMap.getOrDefault(num, 0) > 0) {
				return num;
			}
			// 유효하지 않은 유령 값이면 힙에서 제거하고 다음 값을 확인
			heap.poll();
		}
		return 0;
	}
	
}
