import java.io.*;
import java.util.*;
/*
 * 메모리: 55504
 * 시간: 352
 * 

 * hashmap
 * 
 * 
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(br.readLine());
        String[] words = new String[s];
        Map<String, Integer> order = new HashMap<>();

        for (int i = 0; i < s; i++) {
            words[i] = br.readLine();
            if (!order.containsKey(words[i])) {
                order.put(words[i], i);
            }

        }

        Arrays.sort(words);
        int max = 0;
        String ans1 = words[0], ans2 = words[1];

        for (int i = 0; i < s - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            if (w1.equals(w2)) continue;
            int len = Math.min(w1.length(), w2.length());
            int cnt = 0;
            for (int j = 0; j < len; j++) {
                if (w1.charAt(j) == w2.charAt(j)) cnt++;
                else break;
            }

            if (cnt > max) {
                max = cnt;
                ans1 = w1;
                ans2 = w2;
            } else if (cnt == max) {
                int[] cur = {order.get(w1), order.get(w2)};
                int[] best = {order.get(ans1), order.get(ans2)};
                Arrays.sort(cur);
                Arrays.sort(best);

                if (cur[0] < best[0] || (cur[0] == best[0] && cur[1] < best[1])) {
                    ans1 = w1;
                    ans2 = w2;
                }
            }
        }

        int idx1 = order.get(ans1);
        int idx2 = order.get(ans2);
        if (idx1 < idx2) {
            System.out.println(ans1);
            System.out.println(ans2);
        } else {
            System.out.println(ans2);
            System.out.println(ans1);
        }
    }
}
