
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {

	/**
	 * 암호는 서로다른 L개의 알파벳 소문자
	 * 최소 모음 (a e i o u) 한개와 2개의 자음
	 * 암호는 암호에서 증가하는 순서로 배열
	 * 암호는 C
	 * C가 가능성있는 암호
	 *
	 * L 알파벳 선택개수
	 * C 알파벳 개수
	 */
	static int L;
	static int C;
	static char[] result;
	static char[] arr;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException {

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());

		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		result=new char[L];
		st=new StringTokenizer(br.readLine());
		arr=new char[C];
		for(int i=0;i<C;i++){
			arr[i]=st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		combination(0,0);
		System.out.println(sb);
	}

	static void combination(int start,int cnt){
		if(cnt==L){
			if(isValidate()){
				for(char c:result)
					sb.append(c);
				sb.append("\n");
			}

			return;
		}
		for(int i=start;i<C;i++){
			result[cnt]=arr[i];
			combination(i+1,cnt+1);
		}
	}

	static boolean isValidate(){

		//모음수 확인
		int mo=0;
		int ja=0;
		for(char c:result){
			if(c=='a'||c=='e'||c=='i'||c=='o'||c=='u')
				mo++;
			else
				ja++;
		}
		if(mo>=1&&ja>=2) {
			return true;
		}
		return false;
	}
}
