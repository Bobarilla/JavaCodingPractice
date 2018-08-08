import java.io.*;
import java.util.Scanner;
/*
26 letters in the alphabet
A=shift 0, Z=shift 25
*/
public class Encryption {
	public static void main(String[] args) throws IOException {
		//FileWriter write = new FileWriter("./txt/encrypted.txt", true);
		FileWriter enc = new FileWriter("./txt/encrypted.txt", true);//encryptor
		FileWriter dec = new FileWriter("./txt/decrypted.txt", true);//decryptor
		char[] msg={}, key, keyMsg, encr;
		int ln1=1, ln2=2;
		clearFile("./txt/encrypted.txt");
		clearFile("./txt/decrypted.txt");

		System.out.println("\n./txt/unencrypted.txt");
		Tools.PrintFile("./txt/unencrypted.txt");

		while (true) {
			try {
				msg = toCharArr(Tools.ReadLn("./txt/unencrypted.txt", ln1));
			} catch (java.util.NoSuchElementException e) {
				break;
			}
			key = toCharArr(Tools.ReadLn("./txt/unencrypted.txt", ln2));
			keyMsg = new char[msg.length];//create keMmsg
			for (int i=0; i<key.length; i++) {
				keyMsg[i]=key[i];
			}
			for (int i=key.length; i<msg.length; i++) {
				keyMsg[i]=msg[i-key.length];
			}
			encr = encrypt(msg, keyMsg);//encrypt message
			for (char c : encr) {//write to encrypted.txt
				enc.write(c);
			}
			enc.write("\n");
			for (char c : key) {
				enc.write(c);
			}
			enc.write("\n");
			ln1+=2;
			ln2+=2;
		}
		enc.flush();//no idea what this does...
		enc.close();
		ln1=1;
		ln2=2;
		System.out.println("\n./txt/encrypted.txt");
		Tools.PrintFile("./txt/encrypted.txt");
		while (true) {
			try {
				encr = toCharArr(Tools.ReadLn("./txt/encrypted.txt", ln1));
			} catch (java.util.NoSuchElementException e) {
				break;
			}
			key = toCharArr(Tools.ReadLn("./txt/encrypted.txt", ln2));
			msg = decrypt(encr, key);//decrypt message
			for (char c : msg) {//write to decrypted.txt
				dec.write(c);
			}
			dec.write("\n");
			for (char c : key) {
				dec.write(c);
			}
			dec.write("\n");
			ln1+=2;
			ln2+=2;
		}
		dec.flush();
		dec.close();
		System.out.println("\n./txt/decrypted.txt");
		Tools.PrintFile("./txt/decrypted.txt");
	}

	static char[] toCharArr(String ln) throws IOException {
		//Read a line as char[]
		char[] msg = new char[ln.length()];
		int i=0;
		for (char ch : msg) {
			msg[i]=ln.charAt(i);
			i++;
		}
		return msg;
	}

	static char[] encrypt(char[] msg, char[] keyMsg) {
		int m;
		int km;
		for (int i=0; i<msg.length; i++) {
			m=(int) msg[i]; //get msg[i]'s ASCII Decimal Value from 65-90
			km=(int) keyMsg[i]-65; //get shift from 0-25;
			if (m+km>90) {
				msg[i]=(char) (m+km-26);
			} else {
				msg[i]=(char) (m+km);
			}
		}
		return msg;
	}

	static char[] decrypt(char[] encr, char[] key) {
		// 3
		// encrencrencr
		//-keymsg0msg1msg2
		//----------------
		// msg0msg1msg2msg3
		// not mult of 3: remander of: 1 or 2
		int e, km, m, r=encr.length%3;
		for (int i=0; i<encr.length; i++) {
			e=(int) encr[i]; //get encr[i] ASCII Decimal Value from 65-90
			//System.out.println("e: "+e+" "+encr[i]);
			km=(int) key[i%3]-65; //get shift from 0-25 //key[cycle 0, 1, 2]
			//System.out.println("km: "+km);
			if (e-km<65) {
				encr[i]=(char) (26+e-km);//(90-(65-(e-km))) = (26+e-km)
			}
			else {
				encr[i]=(char) (e-km);
			}
			if ((i+1)%3==0) { // changes on end of every third
				for (int j=0; j<3; j++) { //gets last three values
					key[j]=encr[(i-2+j)]; // sets as key
					//System.out.print(key[j]);
				}
				//System.out.println();
			}
		}
		return encr;
	}

	static void clearFile(String filePath) throws IOException {
		FileWriter justCreatedToClearFile = new FileWriter(filePath);
	}
}