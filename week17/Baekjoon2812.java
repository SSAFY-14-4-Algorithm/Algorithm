import java.io.*;
import java.util.*;

/**
 * 38272kb, 340ms
 */
public class Baekjoon2812 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException{
        String [] tokens = br.readLine().split(" ");
        int K = Integer.parseInt(tokens[1]);

        String number = br.readLine();
        Deque<Character> queue = new ArrayDeque<>();

        for(int i=0;i<number.length();i++){
            char currentChar = number.charAt(i);

            /**
             * 스택의 맨 위 숫자보다 크고, 아직 지울 횟수가 남았다면, 스택에서 숫자를 빼고 지울 횟수 -1
             */
            while(!queue.isEmpty() && K > 0 && queue.peekLast() < currentChar){
                queue.pollLast();
                K--;
            }
            queue.addLast(currentChar);
        }

        /**
         * 모든 숫자를 다 확인했는데 K가 남아있는 경우 -> 뒤에서부터 K만큼 제거
         */
        while(K > 0){
            queue.pollLast();
            K--;
        }

        while(!queue.isEmpty()){
            bw.write(queue.pollFirst()+"");
        }
        bw.write("\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
