public class Main {
	
	static StringBuilder sb = new StringBuilder();

		
	static void hanoi(int n,int s, int f, int t) { // s: start, f: final, t: through
		if(n==1) {
			sb.append(s).append(" ").append(f).append("\n");
			return;
		}
		hanoi(n-1,s,t,f);
		sb.append(s).append(" ").append(f).append("\n");
		hanoi(n-1,t,f,s);

	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		BigInteger ans = BigInteger.TWO.pow(N).subtract(BigInteger.ONE);
		
		if(N<=20) {
			System.out.println(ans);
			hanoi(N,1,3,2);
			System.out.print(sb);
		} else {
			System.out.print(ans);
		}
	}
}
