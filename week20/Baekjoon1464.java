import java.io.*;
import java.util.*;

/**
 * 14272kb
 * 120ms
 */
public class Baekjoon1464 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        Deque<Character> dequeue = new ArrayDeque<>();

        for(int i=0;i<input.length();i++){
            Character c = input.charAt(i);

            if(dequeue.isEmpty()) dequeue.add(c);
            else{
                if(c <= dequeue.getFirst()){
                    dequeue.addFirst(c);
                }else{
                    dequeue.addLast(c);
                }
            }
        }

        while (!dequeue.isEmpty()) bw.write(dequeue.pollFirst());

        bw.flush();
        bw.close();
        br.close();
    }
}
