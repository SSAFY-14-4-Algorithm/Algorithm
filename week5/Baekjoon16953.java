import java.io.IOException;

public class Baekjoon16953 {
	public static void main(String[] args) throws IOException {
		int A = readInt();
		int B = readInt();
		int count = 1;
		while (A != B) {
			if (B < A) {
				count = -1;
				break;
			}
			if (B % 10 == 1) {
				B /= 10;
			} else if (B % 2 == 0) {
				B >>= 1;
			} else {
				count = -1;
				break;
			}
			count++;
		}
		System.out.print(count);
	}

	private static int readInt() throws IOException {
		int c;
		int n = System.in.read() & 15;
		while ((c = System.in.read()) >= 48)
			n = (n * 10) + (c & 15);
		// if (c == 13)
		// System.in.read();
		return n;
	}
}