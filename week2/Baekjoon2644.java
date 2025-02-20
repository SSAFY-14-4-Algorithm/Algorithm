package week2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Baekjoon2644{
    static int People;
    static int findFirstNum;
    static int findSecondNum;
    static int[][] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        People=Integer.parseInt(br.readLine());
        StringTokenizer stringTokenizer=new StringTokenizer(br.readLine());
        findFirstNum=Integer.parseInt(stringTokenizer.nextToken());
        findSecondNum=Integer.parseInt(stringTokenizer.nextToken());
        int Line=Integer.parseInt(br.readLine());
        visited=new boolean[People+1];
        arr=new int[People+1][People+1];
        for(int i=0;i<Line;i++){
            StringTokenizer stringTokenizer1=new StringTokenizer(br.readLine());
            int firstNum=Integer.parseInt(stringTokenizer1.nextToken());
            int secondNum=Integer.parseInt(stringTokenizer1.nextToken());
            arr[firstNum][secondNum]=1;
            arr[secondNum][firstNum]=1;
        }
        dfs(findFirstNum, 0);



        System.out.println(-1);

    }

    // findSecondNum을 만나면 break;
    /**
     * n을 방문하고 n과 인접한 정점에 대한 방문은 재귀로 넘김
     * @param count n까지의 방문하는데 이동한 횟수
     */

    static void dfs(int from, int count){
        if(from==findSecondNum){
            System.out.println(count);
            System.exit(0);
        }
        //if(visited)


//        System.out.println("찾는수"+n);

        visited[from]=true;
        for(int to=1;to<=People;to++){
            if(arr[from][to]==1&&visited[to]==false){
                dfs(to, count+1);
            }
        }


    }

}
