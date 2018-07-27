import java.io.*;
import java.util.Scanner;
/**
 * Description
 *
 * @author A
 * @version 1
 */
public class helloworld {
	public static void main(String[] args) throws IOException {
		FileWriter fw = new FileWriter("./txt/helloworld.txt", true);
		System.out.println("Hello World!");
		char[] ch = {'a','b','c','d','e','f','g'};//7
		for (char c : ch) {
			System.out.println(c);
			fw.write(c);
		}
		fw.flush();
		fw.close();
	}
}
