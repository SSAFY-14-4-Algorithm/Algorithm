package m07.d12;

import java.io.*;
import java.util.*;

public class Main_1464_뒤집기3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        ArrayDeque<Character> q = new ArrayDeque<>();
        q.offer(S.charAt(0));
        for (int i=1;i<S.length();i++){
            char s = S.charAt(i);
            if (s <= q.getFirst()) q.offerFirst(s);
            else q.offerLast(s);
        }
        StringBuilder sb = new StringBuilder();
        for (char s : q) sb.append(s);
        System.out.print(sb);
    }

}
