
public class Main {

	static int N;
	static int K;
	static boolean[] alphabet;
	static String[] word;
	static int max;

	public static void main(String[] args) throws IOException {
		/**
		 * K개의 글자를 가르침
		 * K개의 글자로만 이루어진 단어를 읽을수 있음
		 * K개의 글자로 읽을수 있는 최대
		 * a n t r c i
		 * a n t h e l i
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		word = new String[N];
		alphabet = new boolean[26];
		if (K < 5) {//anta,tica의 공통 알파벳인 acitn의 5개 미만이면 못읽는다.
			System.out.println(0);
			return;
		} else if (K == 26) {//전체 알파벳을 다 배운 똘똘한 경우
			System.out.println(N);
			return;
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			word[i] = s.substring(4, s.length() - 4);
		}

		alphabet['a' - 'a'] = true;
		alphabet['c' - 'a'] = true;
		alphabet['i' - 'a'] = true;
		alphabet['n' - 'a'] = true;
		alphabet['t' - 'a'] = true;

		combination(0, 5);
		System.out.println(max);

	}

	static void combination(int start, int cnt) {
		if (cnt == K) {
			int wordCount = 0;
			for (int i = 0; i < N; i++) {
				int strLen = word[i].length();
				boolean check = true;
				for (int j = 0; j < strLen; j++) {
					if (alphabet[word[i].charAt(j) - 'a'] == false) {
						check = false;
						break;
					}
				}
				if (check) {
					wordCount++;
				}
			}

			max=Math.max(max, wordCount);
			return;
		}
		for (int i = start; i < 26; i++) {
			if (!alphabet[i]) { // 이미 배운 글자는 제외

				alphabet[i] = true;
				combination(i + 1, cnt + 1);
				alphabet[i] = false;
			}
		}
	}
}
