import java.util.*;
import java.io.*;
/* 중량제한
 * 57284kb, 448ms
 */
public class Baekjoon1939 {
    static class Node{
        int B, C;
        public Node(int B, int C) {
            this.B = B;
            this.C = C;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] arr = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            arr[A].add(new Node(B,C));
            arr[B].add(new Node(A,C));
        }
        int[] weight = new int[N+1];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.C - o1.C);
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        weight[start] = 0;
        pq.add(new Node(start, Integer.MAX_VALUE));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.B == end) {
            	System.out.println(cur.C);
            	break;
            }
            if(cur.C < weight[cur.B]) continue;
            for(Node nxt :  arr[cur.B]){
                int cost = Math.min(cur.C, nxt.C);
                if(cost > weight[nxt.B]){
                    weight[nxt.B] = cost;
                    pq.add(new Node(nxt.B,cost));
                }
            }
        }
    }
}