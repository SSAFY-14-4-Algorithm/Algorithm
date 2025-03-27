import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
  static int N;
  static int[] arr;
  static int[] cal;
  static int max;
  static int min;
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    N=Integer.parseInt(br.readLine());
    StringTokenizer st=new StringTokenizer(br.readLine());
    min=Integer.MAX_VALUE;
    max=Integer.MIN_VALUE;
    arr=new int[N];
    //0 + 1 - 2 * 3 /
    cal=new int[4];
    for(int i=0;i<N;i++){
      arr[i]=Integer.parseInt(st.nextToken());
    }
    st=new StringTokenizer(br.readLine());
    for(int i=0;i<4;i++){
      cal[i]=Integer.parseInt(st.nextToken());
    }
    calculate(arr[0],1);
    System.out.println(max);
    System.out.println(min);
  }
  static void calculate(int sum,int cnt){
    if(cnt==N) {
      max = Math.max(max, sum);
      min = Math.min(min, sum);
      return;
    }
    for(int i=0;i<4;i++) {
      if (cal[i] > 0) {
        cal[i]--;
        if (i == 0) {
          calculate(sum + arr[cnt], cnt + 1);
        } else if (i == 1) {
          calculate(sum - arr[cnt], cnt + 1);
        } else if (i == 2) {
          calculate(sum * arr[cnt], cnt + 1);
        } else if (i == 3) {
          calculate(sum / arr[cnt], cnt + 1);
        }
      }
      cal[i]++;
    }

  }
}
