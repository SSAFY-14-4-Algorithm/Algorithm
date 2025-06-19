import java.io.*;
import java.util.*;

/*
 * 메모리: 24576KB
 * 시간: 204ms 
 * 
 * 
 * 왼쪽부터 보면서 더 큰 수 있으면 삭제 , 스택 사용 
 * 
 * 
 * */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());     
        int cnt = k;                                

        char[] num = br.readLine().toCharArray();      
        Deque<Character> stack = new ArrayDeque<>();


        for (char c : num) {
            while (!stack.isEmpty() && cnt > 0 && stack.peekLast() < c) {
                stack.removeLast();
                cnt--;
            }
            stack.addLast(c);                          
        }

        while (cnt-- > 0) stack.removeLast();


        StringBuilder sb = new StringBuilder();
        for (char c : stack) sb.append(c);
        System.out.println(sb.toString());
    }
}
