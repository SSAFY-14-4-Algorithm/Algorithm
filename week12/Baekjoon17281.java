import java.util.*;
import java.io.*;
/*
 * 15248kb, 308ms
 */
public class Baekjoon17281 {
	static int N;
	static int[][] arr;
	static boolean[] selected;
	static int[] order;
	static int answer;
	public static void select(int cnt) {
		if(cnt==10) {
			//simulation
			int score = 0;
			int curP = 1;
			for(int i = 0; i < N; i++) {
				int outCnt = 0;
				int players = 0;
				while(outCnt < 3) {
					int oper = arr[i][order[curP++]];
					if(curP == 10) {
						curP = 1;
					}
					if(oper == 0) {
						outCnt++;
						continue;
					}
					players <<= oper; //주자 이동
					players |= (1<<(oper-1)); //타자 이동
					score += Integer.bitCount(players >>3); //1,2,3루 주자 제외 홈으로 들어온 인원들
					players &= 7; //1,2,3루 제외 1들을 0으로 바꿔주기, 7 = 111
				}
			}
			answer = Math.max(answer, score);
			return;
		}
		if(cnt == 4) {
			order[cnt] = 1;
			select(cnt+1);
			return;
		}
		for(int i = 2; i < 10; i++) {
			if(!selected[i]) {
				selected[i] = true;
				order[cnt] = i;
				select(cnt+1);
				selected[i] = false;
			}
		}
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][10];
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= 9; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		order = new int[10];
		selected = new boolean[10];
		selected[1] = true;
		select(1);
		System.out.println(answer);
	}
}
