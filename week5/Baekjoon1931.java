import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//시간 492ms
// 43720kb
public class Main {
  static class Node{
    int start;
    int end;
    Node(int s,int e){
      this.start=s;
      this.end=e;
    }
  }
  static List<Node> reserve;
  static int result;
  static int N;
  public static void main(String[] args) throws IOException {
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    N=Integer.parseInt(br.readLine());
    reserve=new ArrayList<>();
    for(int i=0;i<N;i++){
      StringTokenizer st=new StringTokenizer(br.readLine());
      int first=Integer.parseInt(st.nextToken());
      int second=Integer.parseInt(st.nextToken());
      reserve.add(new Node(first,second));
    }
    Collections.sort(reserve, (a,b)->{
      if(a.end==b.end)
        return a.start-b.start;
      return a.end-b.end;
    });
    int endTime=0;

    //마지막 시간을 오름차순하고 
    //시작 시간이 끝나는시간보다 클때 그 값을 선택 후
    //result 를 한개 더해줌
    //한마디로 끝나는 시간이 제일 빠른것을 모두 선택하는 것이
    //제일 많은 회의를 선택할 수 있다
    for(int i=0;i<N;i++){
      if(endTime<=reserve.get(i).start){
        endTime=reserve.get(i).end;
        result++;
      }
    }
    System.out.println(result);
  }


}
