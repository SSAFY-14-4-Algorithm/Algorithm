import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 학생 i에게 Ai 만큼의 돈을 주면, 1달간 친구가 됨!
 * 총 k원의 돈
 * 친구의 친구는 친구다!
 * 가장 적은 비용으로 모든 사람과 친구가 되기
 *
 * N 학생수, M 친구관계 수, K 돈
 *
 * 1. 유니온 파인드로 트리를 만들어 놓자
 * 2. 각 트리의 최소 비용을 구하자
 * 3. 각 트리의 최소 비용들을 더하면 최소 친구비
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        fee = new int[N+1]; //1-based
        parent = new int[N+1]; //자신의 자식을 가르키는 배열

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= N; i++) {
            fee[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(reader.readLine());
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int total = 0;
        for (int i = 1; i <= N; i++) {
            if(parent[i] == i) {
                total += fee[i];
            }
        }

        if(total > k) System.out.println("Oh no");
        else System.out.println(total);

    }

    static int[] parent;
    static int[] fee;

    static int find(int a) {
        if(a == parent[a]) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);

        if(aParent == bParent) return;

        if(fee[aParent] < fee[bParent]) {
            parent[bParent] = aParent;
        }
        else parent[aParent] = bParent;
    }
}

