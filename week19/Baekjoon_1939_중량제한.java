import java.io.*;
import java.util.*;


/**
 * 시간: 24644ms
 * 메모리: 228KB
 */
public class Baekjoon_1939_중량제한 {
	// IO
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    // types
    static class Node implements Comparable<Node> {
        int to, transfer;

        Node(int to, int transfer){
            this.to = to;
            this.transfer = transfer;
        }

        @Override
        public int compareTo(Node other) { return other.transfer - this.transfer; }
    }
    // variables
    static int N, M;
    static List<Node>[] adj;
    static int S, E;
    static int[] maxTransfers;

    static int solution(){
        PriorityQueue<Node> q = new PriorityQueue<>();

        maxTransfers[S] = 0;
        for(Node node : adj[S]) {
            maxTransfers[node.to] = node.transfer;
            q.offer(node);
        }

        while(!q.isEmpty()) {
            Node node = q.poll();
            if(node.to == E) break;

            for(Node next : adj[node.to]){
                // 전체 경로에서 최소 중량 만큼만 전달가능하다.
                int nextTransfer = Math.min(next.transfer, node.transfer);
                if(maxTransfers[next.to] < nextTransfer){
                    maxTransfers[next.to] = nextTransfer;
                    q.offer(new Node(next.to, nextTransfer));
                }
            }
        }
        return maxTransfers[E];
    }

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        adj = new ArrayList[N + 1];
        maxTransfers = new int[N + 1];
        for(int i = 1; i <= N; ++i) adj[i] = new ArrayList<>();

        for(int i = 0; i < M; ++i) {
            int s = readInt(), e = readInt(), w = readInt();
            adj[s].add(new Node(e, w));
            adj[e].add(new Node(s, w));
        }

        S = readInt();
        E = readInt();

        System.out.print(solution());
    }

    static int readInt() throws IOException{
        int c, n = 0;
        while((c = System.in.read()) > 0x20) n = (n << 3) + (n << 1) + (c & 0x0F);
        if(c == '\r') System.in.read();
        return n;
    }
}