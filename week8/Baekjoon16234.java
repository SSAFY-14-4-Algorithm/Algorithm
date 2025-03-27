import java.io.*;
import java.util.*;
/*
 * 메모리 : 295024KB
 * 시간 : 584ms
 */
public class Baekjoon16234 {
	static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	static int[][] countries;
	static int[] dx={1,-1,0,0};
	static int[] dy={0,0,1,-1};
	static boolean[][] visited;
	static int n,l,r;
	static int result;

	public static void main(String[] args)throws IOException{
		String[] s=br.readLine().split(" ");
		n=Integer.parseInt(s[0]);
		l=Integer.parseInt(s[1]);
		r=Integer.parseInt(s[2]);
		countries=new int[n][n];
		
		//나라별 인구
		for(int i=0;i<n;i++){
			String[] pop=br.readLine().split(" ");
			for(int j=0;j<n;j++){
				countries[i][j]=Integer.parseInt(pop[j]);
			}
		}
		
		while(true){
			//이동 가능한지 flag
			boolean moved=false;
			visited=new boolean[n][n];
			//모든 나라 bfs
			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if(!visited[i][j]){
						//연합인 나라 저장 리스트
						ArrayList<int[]> near=new ArrayList<>();
						int sum=bfs(i,j,near);
						//국경선 열리면 인구 재배치하기
						if(near.size()>1){
							moved=true;
							int avg=sum/near.size();
							for(int[] pos:near){
								countries[pos[0]][pos[1]]=avg;
							}
						}
					}
				}
			}
			//움직일 수 없으면 break
			if(!moved)break;
			result++;
		}
		System.out.println(result);
	}

	public static boolean isRange(int x,int y){
		return x>=0&&y>=0&&x<n&&y<n;
	}

	public static int bfs(int x,int y,ArrayList<int[]> union){
		Queue<int[]> q=new ArrayDeque<>();
		q.offer(new int[]{x,y});
		union.add(new int[]{x,y});
		visited[x][y]=true;
		int sum=countries[x][y];
		
		while(!q.isEmpty()){
			int[] tmp=q.poll();
			//4방탐색
			for(int i=0;i<4;i++){
				int nx=tmp[0]+dx[i];
				int ny=tmp[1]+dy[i];
				if(isRange(nx,ny)&&!visited[nx][ny]){
					int diff=Math.abs(countries[tmp[0]][tmp[1]]-countries[nx][ny]);
					//l이상 r이하면 연합 추가
					if(diff>=l&&diff<=r){
						visited[nx][ny]=true;
						q.offer(new int[]{nx,ny});
						union.add(new int[]{nx,ny});
						sum+=countries[nx][ny];
					}
				}
			}
		}
		return sum;
	}
}
