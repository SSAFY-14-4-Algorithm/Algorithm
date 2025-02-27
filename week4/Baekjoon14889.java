
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static boolean[] visited;
	static int N;
	static int min=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		arr=new int[N][N];
		visited=new boolean[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++){
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		combination(0,0,visited);

		// 4개를 조합으로 뽑는 경우를 하고
		// 2개씩 확인
		System.out.println(min);


	}

	private static void combination(int start, int cnt, boolean[] visited) {
		if(cnt==N/2){
			min=Math.min(cal(),min);
			return;
		}

		for(int i=start; i<N;i++){
			visited[i]=true;
			combination(i+1,cnt+1,visited);
			visited[i]=false;

		}

	}
	static int cal(){
		int startTeamSum=0;
		int linkTeamSum=0;

		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				if(visited[i]==true&&visited[j]==true){
					startTeamSum=startTeamSum+arr[i][j];
					startTeamSum=startTeamSum+arr[j][i];
				}else if(visited[i]==false&&visited[j]==false){
					linkTeamSum=linkTeamSum+arr[i][j];
					linkTeamSum=linkTeamSum+arr[j][i];
				}
			}
		}
		return Math.abs(startTeamSum-linkTeamSum);
	}


	//기저조건
	//cnt==4일떄

	//반복문
	//다음조합을 선택
//    static void combination(int start,int cnt,int[][] result){
//        if(cnt==N){
//            return;
//        }
//        for(int i=0;i<N;i++){
//            for(int j=start;j<N;j++){
////                System.out.println("i = "+i+" j = "+j);
//                if(i==j)
//                    continue;
//                result[cnt][0]=i;
//                result[cnt][1]=j;
//                combination(j+1,cnt+1,result);
//                System.out.println();
//            }
//        }
//    }
}