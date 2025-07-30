import java.io.*;
import java.util.*;
/*
 * 	메모리:11520KB
 *   시간:	68ms
 */
public class Baekjoon_16974_레벨햄버거 {
  static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
  static long[] layer=new long[51];   // 버거의 총 레이어 수
  static long[] patty=new long[51]; // 버거에 포함된 패티 수

  public static void main(String[] args) throws IOException {
    String[] s = br.readLine().split(" ");
    int n = Integer.parseInt(s[0]);     // 버거 레벨
    long x = Long.parseLong(s[1]);      // 아래에서 x장 먹음

    // 레벨 0 버거 초기값
    layer[0] = 1;
    patty[0] = 1;

    // 전체 레이어와 패티 수 미리 계산
    for(int i=1;i<=n;i++) {
      layer[i] = layer[i-1]*2+3;           // 레벨 i-1 2개랑 햄버거번 2개 패티 1개
      patty[i] = patty[i-1]*2+1;       // 레벨 i-1에 있는 패티 2개 + 가운데 패티 1개
    }

    System.out.println(count(n,x));  // x장 먹었을 때 패티 개수 출력
  }

  static long count(int level,long x) {
	  // 레벨 0 버거일때 패티 1개만 있음
	  if(level==0)
		  return x<=0?0:1;

	  long left = layer[level-1];       // 레벨-(L-1) 버거 길이
	  long mid = 1+left;              // 중간 패티 위치 = 시작햄버거번 + 왼쪽버거
	  
	  if(x==1) 		  // 시작 번만 먹은 경우
		  return 0;            	
	  if(x<=1+left)                // 왼쪽 버거 일부 or 전체 먹은 경우
	    return count(level-1,x-1);	//시작번먹고 x-1을 왼쪽 버거 패티수 재귀
	  if(x==mid+1)                 // 왼쪽 전체 + 중간 패티까지 먹은 경우
	    return patty[level-1]+1;	//왼쪽 버거 패티수 + 중간 패티 1개
	  if(x<=mid+left+1)            // 오른쪽 버거 일부까지 먹은 경우
	    return patty[level-1]+1+count(level-1,x-(mid+1)); //왼쪽 버거 패티수 + 중간패티 + 오른쪽 버거 패티수 재귀

	  // 전체 다 먹은 경우
	  return patty[level];
	}

}
