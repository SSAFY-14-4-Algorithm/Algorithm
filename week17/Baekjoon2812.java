import java.io.*;
import java.util.*;

/**
 * 앞에서부터 최댓값으로 구성하기 위해 K개를 지우기
 * 만약 더 작은 값이 있다면 K개만큼 지울 수 있다.
 * 앞자리가 큰 값이 항상 큰 값
 * 따라서 K개를 지워서 앞자리를 최댓값으로 구성해줘야 한다
 */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int len = N-K;

        String input = reader.readLine();

        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(String.valueOf(input.charAt(i)));

            //스택이 비어있지 않고, K가 남아있다면 앞자리를 더 큰값으로 채워넣는다.
            while (!stk.isEmpty() && stk.peekLast() < num && K > 0) {
                stk.pollLast();
                K--;
            }
            stk.offer(num);
        }
        for (int i = 0; i < len; i++) {
            sb.append(stk.poll());
        }
        System.out.println(sb);
    }

}
