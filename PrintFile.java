import java.io.*;
public class Tools {
	static void PrintLns(String filePath, int lnStart, int lnEnd) throws IOException {
		Scanner read = new Scanner(new FileReader(filePath));
		for (int i=0; i<lnStart; i++) {
			read.nextLine();
		}
	}
	static void PrintFile(String filePath) {
		Scanner read = new Scanner(new FileReader(filePath));
		While (true) {
			try {
				System.out.nextLine();
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
		}
	}
}