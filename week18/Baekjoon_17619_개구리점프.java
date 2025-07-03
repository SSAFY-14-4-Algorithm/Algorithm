import java.io.*;
import java.util.*;
/*
 * 메모리:119160KB
 * 시간:	1436ms
 */
public class Baekjoon_17619_개구리점프 {
    static int[] parent;

    // Find (경로 압축)
    static int find(int x) {
        return parent[x] == x ? x : (parent[x] = find(parent[x]));
    }

    // Union
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) parent[b] = a;
    }

    // 스위핑 이벤트
    static class Event implements Comparable<Event> {
        int x, type, idx;
        // type 0 = 시작, 1 = 끝
        Event(int x, int type, int idx) {
            this.x = x; this.type = type; this.idx = idx;
        }
        public int compareTo(Event o) {
            if (this.x != o.x) return this.x - o.x;
            return this.type - o.type; // 시작(0)이 끝(1)보다 먼저
        }
    }

    // TreeSet 에 보관할 로그 객체 (높이 기준 정렬)
    static class Log {
        int y, idx;
        Log(int y, int idx) { this.y = y; this.idx = idx; }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int Q = Integer.parseInt(s[1]);

        int[] x1 = new int[N], x2 = new int[N], y = new int[N];
        for (int i = 0; i < N; i++) {
            String[] s2 = br.readLine().split(" ");
            x1[i] = Integer.parseInt(s2[0]);
            x2[i] = Integer.parseInt(s2[1]);
            y[i]  = Integer.parseInt(s2[2]);
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        // 3) 이벤트 목록 생성 (2N개)
        Event[] events = new Event[2 * N];
        for (int i = 0; i < N; i++) {
            events[2*i]   = new Event(x1[i], 0, i);
            events[2*i+1] = new Event(x2[i], 1, i);
        }
        Arrays.sort(events);

        // 4) 스위핑 + TreeSet(높이 기준)으로 인접 로그 union
        TreeSet<Log> active = new TreeSet<>((a,b) -> 
            a.y != b.y ? a.y - b.y : a.idx - b.idx
        );
        Log[] logs = new Log[N];
        for (int i = 0; i < N; i++) logs[i] = new Log(y[i], i);

        for (Event e : events) {
            int idx = e.idx;
            if (e.type == 0) {
                // 시작: active에 추가 전, 위·아래 인접 로그와 union
                Log cur = logs[idx];
                Log lower = active.lower(cur);
                Log higher= active.higher(cur);
                if (lower != null) union(lower.idx, idx);
                if (higher!= null) union(higher.idx, idx);
                active.add(cur);
            } else {
                // 끝: active에서 제거
                active.remove(logs[idx]);
            }
        }

        // 5) 쿼리: 같은 컴포넌트인지 검사
        StringBuilder sb = new StringBuilder();
        for (int qi = 0; qi < Q; qi++) {
            String[] pp = br.readLine().split(" ");
            int u = Integer.parseInt(pp[0]) - 1;
            int v = Integer.parseInt(pp[1]) - 1;
            sb.append(find(u) == find(v) ? '1' : '0').append('\n');
        }
        System.out.print(sb);
    }
}
