import java.io.*;
import java.util.*;

/*
 * 메모리: 24576KB
 * 시간: 204ms 
 * 
 * treeset 사용!!
 * 
 * 
 * */
public class Main {
    private static class Problem {
        int id, level;
        Problem(int id, int level) { 
        	this.id = id; this.level = level; 
        	}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        Comparator<Problem> comp = (a, b) -> { //난이도, 번호로 정렬 (낮은 난이도,낮은 번호가 선두 
            if (a.level != b.level) return a.level - b.level;
            return a.id - b.id;
        };
        TreeSet<Problem> set = new TreeSet<>(comp);
        Map<Integer, Problem> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            Problem p = new Problem(P, L);
            set.add(p);
            map.put(P, p);
        }
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if (cmd.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                Problem p = new Problem(P, L);
                set.add(p);
                map.put(P, p);
            } else if (cmd.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                Problem p = map.remove(P);
                if (p != null) set.remove(p);
            } else {                 
                int x = Integer.parseInt(cmd.equals("recommend") ? st.nextToken() : "1");
                Problem p = (x == 1) ? set.last() : set.first();
                sb.append(p.id).append('\n');
            }
        }
        System.out.print(sb);
    }
}