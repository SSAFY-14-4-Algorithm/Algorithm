package dat0319;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
//메모리 100752KB 실행시간 804ms
public class P11000 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] lectures = new int[N][2];

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            lectures[i][0] = Integer.parseInt(input[0]);
            lectures[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(lectures, (o1,o2) -> o1[0] - o2[0]);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0][1]);

        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lectures[i][0]) {
                pq.poll();
            }
            pq.add(lectures[i][1]);
        }

        System.out.println(pq.size());
    }
}
