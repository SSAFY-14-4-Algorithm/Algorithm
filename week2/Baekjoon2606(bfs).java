public class Main {
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visit;
	static int ans=0;
	
	public static void dfs(int node) {
		visit[node]=true;
		for(int next: graph.get(node)) {
			if(!visit[next]) {
				ans++;
				dfs(next);
			}
		}
	}
	
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		
		
		for(int i=0;i<N+1;i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			String[] input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			graph.get(a).add(b);
			graph.get(b).add(a);
			
		}
		
		//dfs
		
		visit = new boolean[N+1];
		dfs(1);
		
		System.out.println(ans);
	
		
		
		
	}
	
	

}
