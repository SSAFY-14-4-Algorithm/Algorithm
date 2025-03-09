import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


//메모리 14168kb
//시간 104ms

public class Main {
  static int result=-1;
  static boolean check;
  static Long min;
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st=new StringTokenizer(br.readLine());


    int A=Integer.parseInt(st.nextToken());
    int B=Integer.parseInt(st.nextToken());
    min=Long.MAX_VALUE;

    dfs(A,B,1);
    if (check) {
      System.out.println(min);
    } else {
      System.out.println(-1);
    }
  }
  static void dfs(long a,int b,int cnt){
    if(a>b)
      return;
    if(a==b){
      min=Math.min(min,cnt);
      check=true;
      return;
    }
    dfs(a*10+1,b,cnt+1);

    dfs(a*2,b,cnt+1);
  }

}
//1000000000
