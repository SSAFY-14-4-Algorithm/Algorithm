import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {


	static int N;
	static int L;
	static int R;
	static int X;
	static int[] arr;
	static int count;
	static int[] result;

	public static void main(String[] args) throws IOException {
		/**
		 * 문제를 N개 가짐
		 * i번째 문제의 난이도는 Aj
		 * 문제는 2문제 이상
		 * R>= 문제 난이도 합 >=L ,
		 * 가장 어려운 문제 - 가장쉬운 문제 >= X
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		//1 2
		//2 3
		//1 3
		//1 2 3
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for(int i=2;i<=N;i++){
			combination(0,i,0,result=new int[i]);
		}
		System.out.println(count);


	}

	static void combination(int start,int r,int cnt,int[] result) {
		if (cnt == r) {
			if(validate()){
				count++;
			};
			return;
		}


		for(int i=start;i<N;i++){
			result[cnt]=arr[i];
			combination(i+1,r,cnt+1,result);
		}
	}

	static boolean validate(){
		int sum=0;
		for(int i:result){
			sum=sum+i;
		}
		if(R<sum||L>sum||(result[result.length-1]-result[0])<X)
		{return false;}
		else {

			return true;}


	}



}
