import java.io.*;
import java.util.*;

/**
 * 
 * 메모리 76784
 * 시간 712
 *
 */

public class Baekjoon2589 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, (e1, e2) -> e1[0] - e2[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(arr[0][1]);

        int result = 1;

        for (int i = 1; i < N; i++) {
        	
        	while (!pq.isEmpty() && pq.peek() <= arr[i][0]) {
                pq.poll();
            }
        	
            pq.add(arr[i][1]);
            result = Math.max(result, pq.size());
        }

        System.out.println(result);
    }
}
