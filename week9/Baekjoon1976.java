import java.io.*;

public class Baekjoon1976 {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	private static int N,M;
	private static int [] parents;
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parents = new int[N+1];
		for(int i=0;i<=N;i++) {
			parents[i] = i;
		}
		
		for(int i=1;i<=N;i++) {
			String [] tokens = br.readLine().split(" ");
			for(int j=1;j<=N;j++) {
				int isRoad = Integer.parseInt(tokens[j-1]);
				
				if(isRoad == 1) {	//도로로 연결되어 있을 경우 유니온 연산 수행
					union(i,j);
				}
			}
		}
		
		String [] tokens = br.readLine().split(" ");
		int start = Integer.parseInt(tokens[0]);
		for(int i=1;i<M;i++) {
			//출발지와 동일한 집합이 아닐 경우 여행을 할 수 없다.
			int city = Integer.parseInt(tokens[i]);
			
			if(find(start) != find(city)) {
				bw.write("NO\n");
				bw.flush();
				bw.close();
				br.close();
				return;
			}
		}
		bw.write("YES\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	private static void union(int i,int j) {
		int rootA = find(i);
		int rootB = find(j);
		
		if(rootA != rootB) {
			if(rootA < rootB) {
				parents[rootB] = rootA;
			}else {
				parents[rootA] = rootB;
			}
		}
	}

	private static int find(int i) {
		if(i == parents[i]) return i;
		else {
			return parents[i] = find(parents[i]);
		}
	}
}
