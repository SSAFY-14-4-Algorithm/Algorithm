import java.io.*;
import java.util.*;
/*
 * 메모리 12960KB
 * 시간 76ms
 */
public class Baekjoon1038 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	public static void main(String[] args) throws IOException{
		n = Integer.parseInt(br.readLine());
		//자리수가 1부터 10까지 가능 9876543210이 최대
		for(int i=1;i<=10;i++) {
			decrease(0,i,0,10);
		}
		//다 돌았을때 값이 0이상이면 없다는 뜻이므로 -1
		if(n>=0) System.out.println(-1);
	}
	public static void decrease(int cnt,int length,long num,int select) { 
		//length 자리수만큼 다 채워졌다면
		if(cnt==length) {
			n--;
			//n이 -1이면 출력후 종료
			//0이 아닌 이유는 0이 1번째감소하는수가 아니라 0번째 감소하는 수이기 때문에
			if(n==-1) {
				System.out.println(num);
				System.exit(0);
			}
		}
		//다음 자리수는 앞에 선택된것보다 작아야함
		for(int i=0;i<select;i++) {
			//다음 재귀로 넘겨줄때 이전값*10한 후 i 더함
			decrease(cnt+1,length,num*10+i,i);
		}
	}
}
