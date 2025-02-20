package week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Baekjoon2178 {
    public static int result;
    public static int Computer;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        Computer=Integer.parseInt(br.readLine());
        int Line=Integer.parseInt(br.readLine());
        int[][] arr=new int[Computer+1][Computer+1];
        boolean[] visited=new boolean[Computer+1];
        for(int i=1;i<=Line;i++){
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int firstNum=Integer.parseInt(stringTokenizer.nextToken());
            int secondNum=Integer.parseInt(stringTokenizer.nextToken());
            arr[firstNum][secondNum]=1;
            arr[secondNum][firstNum]=1;
        }
        dfs(arr,visited,1);
        System.out.println(result);



    }

    //나가는 조건 : 숫자가 더이상 없을떄 return 해주면 됌
    //연결된 라인을 모두 접근 arr[][]==1일 시 dfs
    //접근한 카운트 세기
    static void dfs(int[][] arr,boolean[] visited,int start){
//        System.out.println(start);
        visited[start]=true;
        for(int i=1;i<=Computer;i++) {
            if(arr[start][i]==1&&visited[i]==false){
                dfs(arr,visited,i);
                result++;

            }
        }
    }
}
