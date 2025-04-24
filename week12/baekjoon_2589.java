import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//시간 452ms
public class Main {
    static int[] dR={-1,1,0,0};
    static int[] dC={0,0,-1,1};
    static int R;
    static int C;
    static char[][] map;
    static int max;
    static class Node{
        int r;
        int c;
        int count;


        public Node(int r, int c,int count) {
            this.r = r;
            this.c = c;
            this.count=count;
        }
    }
    public static void main(String[] args) throws IOException {
        // 각 칸 육지 = L 바다 = W
        // 상하좌우로 이웃한 육지로만 이동
        // 한칸 이동하는데 1시간
        // 보물은 가장 긴 시간이 걸리는 육지 2곳에 나뉘어 뭍혀있음
        // 같은 곳을 두번이상 지나가거나 멀리 돌아가서는 안된다?
        //
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        map=new char[R][C];
        max=0;
        for(int i=0;i<R;i++){
            String s=br.readLine();
            for(int j=0;j<C;j++){
                map[i][j]=s.charAt(j);
            }
        }
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(map[i][j]=='L')
                    bfs(i,j);
            }
        }
        System.out.println(max);
    }

    private static void bfs(int i, int j) {
        boolean[][] visited=new boolean[R][C];
        visited[i][j]=true;
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(i,j,0));
        while (!queue.isEmpty()){
            Node n=queue.poll();
            max=Math.max(max,n.count);
            for(int z=0;z<4;z++){
                int newR=dR[z]+n.r;
                int newC=dC[z]+n.c;
                if(newR<0||newC<0||newC>=C||newR>=R||visited[newR][newC])
                    continue;
                if(map[newR][newC]=='W')
                    continue;
                visited[newR][newC]=true;
                queue.add(new Node(newR,newC,n.count+1));

            }
        }

    }
}
