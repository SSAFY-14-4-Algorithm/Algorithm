import java.util.*;
import java.io.*;
import java.math.*;
/*
 * 14280kb, 220ms
 */
public class Baekjoon9663 {
	static int N;
	static int answer, cnt;
	public static void nq(int row, int c, int slash, int bslash) {
		if(row == N) {
			cnt++;
			return;
		}
		if(row > 0) {
			int available = ((1<<N)-1) & ~(c|slash|bslash);
			while(available > 0) {
				int position = available & -available;
				available -= position;
				nxtNQ(row, c, slash, bslash, position);
			}
		} else {
			for(int i = 0; i < N/2; i++) {
				nxtNQ(row, c, slash, bslash, 1<<i);
			}
			answer += cnt;
			if(N % 2 == 1) {
				nxtNQ(row, c, slash, bslash, (1<<(N/2)));
			}
			answer += cnt;
		}
	}
	
	public static void nxtNQ(int row, int c, int slash, int bslash, int p) {
		nq(row+1, (c|p), (slash|p)>>1, (bslash|p)<<1);
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		answer = 0;
		cnt = 0;
		nq(0, 0, 0, 0);
		System.out.println(answer);
	}
}

// 주석 달아놓은 코드
//public class Main {
//	static int N;
//	static int answer, cnt;
//	public static void nq(int row, int c, int slash, int bslash) {
//		if(row == N) { //행 끝까지 오면 cnt 증가
//			cnt++;
//			return;
//		}
//		if(row > 0) {
//			int available = ((1<<N)-1) & ~(c|slash|bslash);
//			//N=4일 때를 예시로 설명
//			//(1<<N)-1 => 1111
//			//~(c|slash|bslash) => 열, 대각선, 역대각선 3가지에 다 속하지 않는 것을 비트로 표현
//			//즉, 열, 대각선, 역대각선을 OR연산해서 열,대각선,역대각선에 걸리는 지점을 모으고, NOT연산을 통해 가능한 곳만 1로 되게 설정
//			//그리고 N개의 비트만큼 1로 설정된 값과 & 연산해서 N비트 내의 값만 1로 설정
//			//즉, available 변수는 말 그대로 N개의 자리 중 가능한 곳만 1으로 나타내는 비트값
//			//앞에 (1<<N)-1 이랑 & 연산을 하는 이유는 bslash의 경우 시프트를 앞으로 하기 때문에 필요없는 값이 포함되어 있고
//			//그렇게 되면, 아래 available>0 조건에 문제가 생기므로 N비트 내로 한정 지어준 것
//			while(available > 0) { //available이 0보다 크다는 것은 아직 가능한 1값이 있다는 것
//				int position = available & -available;
//				//position 값은 내가 놓을 수 있는 1값을 찾는 것
//				//available & -available은 가장 마지막 1을 찾는 연산
//				//EX) 10 & -10
//				// 00001010
//				// 11110110
//				// 00000010
//				//위와 같이 마지막 1값을 찾을 수 있음
//				//비트의 -연산이 원래 양수값의 비트를 반전시키고 1을 더한 값이기 때문에 이런 식으로 나옴
//				available -= position;
//				//available에서 빼줌으로써 마지막 1값을 없애줌
//				//이런식으로 1을 없애가면서 모든 1을 쓸 때까지 돔 -> 모든 1을 쓰면 available이 0 되므로 반복문을 돌지 않음
//				nxtNQ(row, c, slash, bslash, position);
//				//다음 함수 호출
//			}
//		} else { //첫 시작 때, 함수 실행 설정
//			for(int i = 0; i < N/2; i++) { //첫반과 나머지 반이 대칭이므로 반만 돔
//				nxtNQ(row, c, slash, bslash, 1<<i);
//			}
//			answer += cnt; //첫 반절 더해주기
//			if(N % 2 == 1) { //홀수면, 중간에 1번을 추가로 돌아줌
//				nxtNQ(row, c, slash, bslash, (1<<(N/2)));
//			}
//			answer += cnt; //홀수 있으면 중간 1번 추가하고 없으면 그냥 나머지 반절 더해주기
//		}
//	}
//	
//	public static void nxtNQ(int row, int c, int slash, int bslash, int p) {
//		nq(row+1, (c|p), (slash|p)>>1, (bslash|p)<<1);
//		//row+1 : 다음 행
//		// N=4일 때, 2번째 자리에 Queen을 놓았다면
//		// 아래값들을 이용해 놓을 수 없는 곳을 체크 (놓을 수 없는 곳에 1로 체크)
//		// c|p : 열체크  (0100)
//		// (slash|p)>>1 : 대각선 체크 (0010)
//		// (bslash|p)<<1 : 역대각선 체크 (1000)
//		// 따라서 이 3개를 OR연산 하면 1110 이고 이를 NOT연산 하면 0001 따라서 1이 있는 위치가 퀸을 놓을 수 있는 위치
//	}
//	
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		N = Integer.parseInt(br.readLine());
//		answer = 0;
//		cnt = 0;
//		nq(0, 0, 0, 0);
//		System.out.println(answer);
//	}
//}
