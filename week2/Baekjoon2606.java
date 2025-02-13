public class Main {
	
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	
	
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
		
		//bfs
		boolean[] visit = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		visit[1] = true;
		int ans = 0;
		while(!q.isEmpty()) {
			int node = q.poll();
			for(int next : graph.get(node)) {
				if(!visit[next]) {
					ans+=1;
					visit[next] = true;
					q.add(next);
				}
			}
		}
		System.out.println(ans);
		
		
		
	}
	
}
