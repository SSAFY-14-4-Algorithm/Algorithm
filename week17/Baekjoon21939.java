import java.util.*;
import java.io.*;

/**
 * 250615
 * Java8 | 실행시간: 372 ms, 메모리 : 31,748 KB
 */

public class Baekjoon21939 {
	private static class Node implements Comparable<Node>{
        int P, L;
        Node(int P, int L){
            this.P = P; // 문제번호
            this.L = L; // 문제난이도
        }

        @Override
        public int compareTo(Node o) {
            return (this.L!=o.L) ? Integer.compare(this.L, o.L) : Integer.compare(this.P, o.P);
        }
    }
    private static TreeSet<Node> list = new TreeSet<Node>();
    private static HashMap<Integer, Integer> P2L = new HashMap<>(); // 문제번호 -> 난이도 조회

    public static void main(String[] args) throws IOException {
        int N = readInt(); // 문제 개수
        for (int i=0;i<N;i++) add(readInt(), readInt());
    
        int M = readInt(); // 명령문 개수
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<M;i++){
            switch (readString()){
                case "add":
                    add(readInt(), readInt());
                    break;
                case "recommend":
                    sb.append(select(readInt())).append("\n");
                    break;
                case "solved":
                    delete(readInt());
                    break;
            }
        }
        System.out.print(sb);
    }

    private static void add(int P, int L){ // 추가 연산
        list.add(new Node(P, L));
        P2L.put(P, L);
    }

    private static int select(int x){ // 조회 연산
        if (x==1) return list.last().P;
        return list.first().P;
    }

    private static void delete(int P){ // 제거 연산
        list.remove(new Node(P, P2L.get(P)));
        P2L.remove(P);
    }
    
    private static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    private static int readInt() throws IOException{
        st.nextToken(); // 1<=N,P<=100_000 / 1<=M<=10_000 / 1<=L<=100 / |x|=1
        return (int) st.nval;
    }
    private static String readString() throws IOException{
        st.nextToken();
        return st.sval;
    }
}
