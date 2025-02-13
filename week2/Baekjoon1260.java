package week2;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
public class Baekjoon1260 {
    public static StringBuilder stringBuilder=new StringBuilder();
    public static StringBuilder stringBuilder2=new StringBuilder();
    public static int N;
    public static int M;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        //점의 개수
        N=sc.nextInt();
        //간선의 개수
        M=sc.nextInt();
        //탐색을 시작할 점번호
        int V=sc.nextInt();
        boolean[] visited=new boolean[N+1];
        int[][] arr=new int[N+1][N+1];

        boolean[] visited2=new boolean[N+1];
        int[][] arr2=new int[N+1][N+1];

        Queue<Integer> queue=new LinkedList<>();

        for(int t=1;t<=M;t++){
            int firstNum=sc.nextInt();
            int secondNum=sc.nextInt();
            arr[firstNum][secondNum]=1;
            arr[secondNum][firstNum]=1;
            arr2[firstNum][secondNum]=1;
            arr2[secondNum][firstNum]=1;
        }

        node(arr,visited,V);
        bfs(arr2,visited2,V,queue);
        System.out.println(stringBuilder);
        System.out.println(stringBuilder2);


    }

    static void bfs(int[][] arr2,boolean[] visited2,int V,Queue<Integer> queue){

        queue.add(V);
        visited2[V]=true;
        while(!queue.isEmpty()){
            int pop=queue.poll();

            stringBuilder2.append(pop+" ");
            for(int i=1;i<=N;i++){
                if(arr2[pop][i]==1&&visited2[i]==false){
                    queue.add(i);
                    visited2[i]=true;
                }
            }
        }

    }

    //idx 번째 노드를 방문하고 idx와 인접한 노드 방문은 재귀로 넘김
    static void node(int[][] arr,boolean[] visited,int V){
        visited[V]=true;
        stringBuilder.append(V+" ");
        for(int i=1;i<=N;i++){
            if(arr[V][i]==1&&visited[i]==false){
                node(arr,visited,i);

            }
        }
    }
}
