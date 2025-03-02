import java.math.BigInteger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		System.out.println("0부터 입력받은 값까지 팩토리얼 값을 출력해줍니다.");
		System.out.println("몇 번째까지 팩토리얼 값을 구할지 입력해주세요.\n");
		System.out.print("Input N : ");

		long j = Long.parseLong(br.readLine().trim());

		BigInteger a = BigInteger.ONE;

		bw.write("0! = " + a.toString() + "\n");
		for(int i = 1; i <= j; i++) {
			a = a.multiply(new BigInteger(String.valueOf(i)));
			bw.write(i + "! = " + a.toString() + "\n");
		}

		bw.flush();
		bw.close();
	}
}