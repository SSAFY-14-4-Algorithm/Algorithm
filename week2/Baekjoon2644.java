public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String[] input = br.readLine().split(" ");
		int x = Integer.parseInt(input[0]);
		int y = Integer.parseInt(input[1]);
		
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		boolean[] visit = new boolean[n+1];
		int[] ans = new int[n+1];
		
		for(int i=0;i<=n;i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0;i<m;i++) {
			input = br.readLine().split(" ");
			int a = Integer.parseInt(input[0]);
			int b = Integer.parseInt(input[1]);
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(x);
		visit[x] = true;
		while(!q.isEmpty()) {
			int node = q.remove();
			for(int next : graph.get(node)) {
				if(!visit[next]) {
					q.add(next);
					visit[next] = true;
					ans[next] = ans[node]+1;
					
				}
			}
		}
		if(ans[y]==0) {
			System.out.println(-1);
		} else {
			System.out.println(ans[y]);
		}
		
		
	}
	
}
