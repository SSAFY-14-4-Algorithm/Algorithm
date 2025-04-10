import java.io.*;
import java.util.*;
/*
 * 메모리: 84672KB
 * 시간: 212ms
 * 
 * X+-1(1초 소요), X*2(0초 소요)
 * N에서 K로 가는 최소 시간 
 */

public class Main {
    static int max = 100001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[max + 1];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{N, 0});

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int current = tmp[0];
            int time = tmp[1];
            visited[current] = true;


            if (current == K) { //종료 조건 
                answer = Math.min(answer, time);
            }

   
            if (current * 2 <= max && !visited[current * 2]) {
                q.offer(new int[]{current * 2, time});
            }
            if (current + 1 <= max && !visited[current + 1]) {
                q.offer(new int[]{current + 1, time + 1});
            }
            if (current - 1 >= 0 && !visited[current - 1]) {
                q.offer(new int[]{current - 1, time + 1});
            }
        }
        System.out.println(answer);
    }
}
