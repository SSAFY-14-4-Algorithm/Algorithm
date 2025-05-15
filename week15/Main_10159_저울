import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_10159_저울 {
    static int N;
    static int M;
    static int[] result;
    static List<List<Integer>> list;
    public static void main(String[] args) throws IOException {
        // 무게가 서로 다른 N개 물건
        // 1부터 N 까지 매겨져있다
        // 양팔 저울로 어떤 것이 무거운 것인지를 측정한 결과표를 가지고 있다
        // 직접 측정하지 않은 물건 쌍의 비교 결과를 알아낼 수도 있고 알아내지 못할 수도 있다
        // https://tussle.tistory.com/1041

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        M=Integer.parseInt(br.readLine());
        list=new ArrayList<>();
        result=new int[N+1];
        for(int i=0;i<=N;i++){
            list.add(new ArrayList<>());
        }
        for(int i=0;i<M;i++){
            StringTokenizer st=new StringTokenizer(br.readLine());
            int A=Integer.parseInt(st.nextToken());
            int B=Integer.parseInt(st.nextToken());
            list.get(A).add(B);
        }

        for(int i=1;i<=N;i++){
            boolean[] visited=new boolean[N+1];
            visited[i]=true;
            result[i]++;
            dfs(i,visited,i);
        }
        StringBuilder sb=new StringBuilder();
        for(int i=1;i<=N;i++){
            sb.append(N-result[i]).append('\n');
        }
        System.out.println(sb);
    }

    // 큰수에서 작은수로 갈 수 있으면 (비교할 수 있으면) 내 result + 1
    // 작은수도 큰수에서 온 것 이기 때문에 result + 1
    static void dfs(int start,boolean[] visited,int root){
        for(int n:list.get(start)){
            if(visited[n])
                continue;
            result[root]++;
            result[n]++;
            visited[n]=true;
            dfs(n,visited,root);
        }
    }
}
