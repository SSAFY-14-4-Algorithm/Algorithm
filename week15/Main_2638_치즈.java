import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2638_치즈 {
    static int R;
    static int C;
    static int[][] map;
    static int count;
    static int[] nR={-1,1,0,0};
    static int[] nC={0,0,-1,1};
    static class Node{
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        count=0;
        map=new int[R][C];
        for (int i=0;i<R;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++){
                int n=Integer.parseInt(st.nextToken());
                if(n==1) {
                    count++;
                }
                map[i][j]=n;
            }
        }
        int result=0;
        while(count>0){
            boolean[][] visited=new boolean[R][C];
            List<Node> list=new ArrayList<>();
            int[][] newMap=new int[R][C];
            bfs(visited,newMap,list);
            for(Node n:list){
                map[n.r][n.c]=0;
                count--;
            }
//            for(int i=0;i<R;i++){
//                for(int j=0;j<C;j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();

            result++;
        }
        System.out.println(result);

    }
    static void bfs(boolean[][] visited, int[][] newMap, List<Node> list){
        visited[0][0]=true;
        Queue<Node> queue=new LinkedList<>();
        queue.add(new Node(0,0));
        while (!queue.isEmpty()){
            Node n=queue.poll();
            for(int i=0;i<4;i++){
                int newR=n.r+nR[i];
                int newC=n.c+nC[i];
                if(newR<0||newC<0||newR>=R||newC>=C||visited[newR][newC])
                    continue;
//                if(map[newR][newC]==1){
//                    if(newMap[newR][newC]==1){
//                        list.add(new Node(newR,newC));
//                    } else {
//                        newMap[newR][newC]++;
//                    }
//                    continue;
//                }
                if (map[newR][newC] == 1) {
                    // 1) 먼저 누적
                    newMap[newR][newC]++;
                    // 2) 접촉 횟수가 2가 되는 순간만 한 번만 리스트에 담는다
                    if (newMap[newR][newC] == 2) {
                        list.add(new Node(newR, newC));
                    }
                    continue;
                }
                visited[newR][newC]=true;
                queue.add(new Node(newR,newC));

            }
        }
    }
}
