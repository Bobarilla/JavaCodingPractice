public class ParsingHex {
/*
file: max 100 lines
lines: max 100 characters

Hexidecimal: Base 16: 0 1 2 3 4 5 6 7 8 8 9 a b c d e f
					  0 1 2 3 4 5 6 7 8 9 10111213141516
'0x' or '0X' // represents hexidecimal format
followed by
string of hexidecimal digits (0-9, a-f, or A-F)

hexidecimal long as possible but max is 0xffffffff 12345678
hexidecimal never adjacent to each other

OUTPUT
hexidecimal read and then digit/base10 equivilent
e.g.
0x5206aBC 86010556
0Xa8aD4 690900
0x6d3e6 447462
0xB6fc 46844

uyzrr0x5206aBCtrrwm0Xa8aD4poqwqr
pqovx0x6d3e6-+ 230xB6fcgmmm
*/
	public static void main(String[] args) throws Exception {
		String ln;
		boolean foo;
		char[] lnArr;
		char[] hex={};
		char[] hexMap = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		int lnNum=0;
		System.out.println();
		Tools.PrintFile("./txt/hex.txt");
		System.out.println();
		while (true) {
			lnNum++;
			try {
				ln = Tools.ReadLn("./txt/hex.txt", lnNum); //1 line at a time
			} catch (Exception e) {
				break;
			}
			lnArr = toCharArr(ln);
			//read 0x or 0X as start of hex
			//System.out.println(line);
			for (int i=0; i<lnArr.length-1; i++) {
				if (lnArr[i]=='0'&&(lnArr[i+1]=='x'||lnArr[i+1]=='X')) {//Order of operations?
					//Start of reading hex
					for (int j=0; j<8; j++) {
						hex[j]=lnArr[j+2];
						foo=false;
						for (char ch : hexMap) {
							if (lnArr[j+3] == ch) {
								foo = true;
							}
						}
						if (foo==false) {
							break;
						}
					}
				}
			}
			for (char ch : hex) {
				System.out.println(ch);
			}
		}
	}

	static char[] toCharArr(String ln) {
		char[] arr = new char[ln.length()];
		int i=0;
		for (char ch : arr) {
			arr[i]=ln.charAt(i);
			i++;
		}
		return arr;
	}

	static int hexToDec(char[] hex) {
		//hex string is ffffffff max 8 length
		int dec=0;
		int mult=1;
		char[] hexMap = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		int[] hexDecVal = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		for (char ch : hex) {
			for (int i=0; i<16; i++) {
				if (ch == hexMap[i]) {
					dec+=hexDecVal[i]*mult;
					break;
				}
			}
			mult*=10;
		}


		return dec;
	}
}