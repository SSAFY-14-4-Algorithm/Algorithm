public class Main {
	
	static int N,M,V;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visit;
	
	public static void dfs(int node) {
		visit[node] = true;
		System.out.print(node+" ");
		
		for(int next:graph.get(node)) {
			if(!visit[next]) {
				dfs(next);
			}
		}
		
		
	}
	
	public static void bfs(int V) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(V);
		visit[V] = true;
		while(!q.isEmpty()) {
			int node = q.remove();
			System.out.print(node+ " ");
			
			for(int next : graph.get(node)) {
				if(!visit[next]) {
					q.add(next);
					visit[next] = true;
				}
			}
			
			
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");
		
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		V = Integer.parseInt(input[2]);
		graph = new ArrayList<>();
		
		for(int i=0;i<=N;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<M;i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		for(int i=1;i<=N;i++) {
			Collections.sort(graph.get(i));
		}
		
		visit= new boolean[N+1];
		dfs(V);
		System.out.println();
		visit = new boolean[N+1];
		bfs(V);
		
		
		
		
	}
}

