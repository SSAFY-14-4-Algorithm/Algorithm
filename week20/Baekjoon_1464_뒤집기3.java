import java.io.*;
import java.util.*;
/*
 * 	메모리:11612KB
 * 시간:	68ms
 */
public class Baekjoon_1464_뒤집기3 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws IOException {
        String s = br.readLine();    // 원본 문자열
        
        Deque<Character> dq = new ArrayDeque<>();  
        
        // 문자열을 한 글자씩 순회하면서
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            // 덱이 비었거나, 현재 문자가 덱 맨 앞 문자보다 사전순 ≤ 이면
            if(dq.isEmpty() || c<=dq.peekFirst()){
                dq.addFirst(c);   // 앞으로 삽입
            } else {
                dq.addLast(c);    // 뒤로 삽입
            }
        }
        
        // 덱을 비우며 순서대로 꺼내 최종 문자열 완성
        StringBuilder sb = new StringBuilder();
        while(!dq.isEmpty()){
            sb.append(dq.pollFirst());
        }
        
        System.out.println(sb.toString());
    }
}
