import java.util.Scanner;
import java.io.*;
public class HiddenPassword {
	public static void main(String[] args) throws IOException {
		Scanner read = new Scanner(new FileReader("./txt/passwords.txt"));
		String input;
		while (true) {
			try {
				input = read.nextLine();
			} catch (java.util.NoSuchElementException e) {
				System.out.println("End");
				break;
			} catch (Exception e) {
				System.out.println(e);
				break;
			}
			System.out.print("Input:\t\t"+input+"\t");
			if (PassOrFail(input)) {
				System.out.println("Pass\n");
			} else {
				System.out.println("Fail\n");
			}
		}
	}
	static boolean PassOrFail(String input) {
		String pass = input.substring(0, input.indexOf(" "));
		String msg = input.substring(input.indexOf(" ")+1);
		System.out.println("\nPassword:\t"+pass+"\nMessage:\t"+msg);
		for (int i=0; i<msg.length(); i++) {
			//Run through {A, B, C} Remove A if found
			for (int j=0; j<pass.length(); j++) {
				if (msg.charAt(i)==pass.charAt(0)){
					pass = pass.substring(1, pass.length());
					break;
					//System.out.println("Shorten");
				} else if (msg.charAt(i)==pass.charAt(j)) {
					//System.out.println("TOO EARLY! j:"+j+", "+pass.charAt(j)+" i:"+i+", "+msg.charAt(i));
					//System.out.println(pass);
					return false;
				}
			}
			//System.out.println(pass);
		}
		if (pass.length()>0) {
			return false;
		}
		return true;
		/*
		substring(inclusive, exclusive)

		pp	ABC HAPPYBIRTHDAYCACEY
		ff	ABC TRAGICBIRTHDAYCACEY
		ff	ABC HAPPYBIRTHDAY
		ff	ABC HKDJEWFEG
		pf	SECRET SOMECHORESARETOUGH X //built for unique but two E's in SECRET
		ff	SECRET IHUKJNDFSFD
		pp	DAISY DANIELSHOTYOU
		ff	DAISY DOYOUATTACKILLSHORTYAKS
		ff	DAISY IUDFHJSD
		pp	HATS HATEISBAD
		ff	HATS HAIRSTRIMMED
		ff	HATS IUHGBDHJSFDSKL
		ff	GLOB GEOLOGYISBAD
		pp	GLOB GLOWINGBALL
		ff	GLOB HJDFSKOKI
		*/
	}
}