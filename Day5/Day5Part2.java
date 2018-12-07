import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day5Part2 {
	public static void main(String args[]) throws FileNotFoundException {
		File file = new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day5\\input.txt");
		Scanner sc = new Scanner(file);
		String[] compare = new String[] { "aA", "bB", "cC", "dD", "eE", "fF", "gG", "hH", "iI", "jJ", "kK", "lL", "mM",
				"nN", "oO", "pP", "qQ", "rR", "sS", "tT", "uU", "vV", "wW", "xX", "yY", "zZ", "Aa", "Bb", "Cc", "Dd",
				"Ee", "Ff", "Gg", "Hh", "Ii", "Jj", "Kk", "Ll", "Mm", "Nn", "Oo", "Pp", "Qq", "Rr", "Ss", "Tt", "Uu",
				"Vv", "Ww", "Xx", "Yy", "Zz" };
		String temp = sc.nextLine();
		String[] stringarr = new String[temp.length()];
		stringarr = temp.split("");
		Boolean looper = true;
		int next2 = 1;
		int finalScore = 0;
		String[] comparesplit = new String[2];
		for (int k = 0; k < (compare.length / 2); k++) {
			stringarr = temp.split("");
			finalScore = 0;
			comparesplit = compare[k].split("");
			for (int p=0; p<temp.length();p++) {
				if (stringarr[p].equals(comparesplit[0]) || stringarr[p].equals(comparesplit[1])) {
					stringarr[p] = "";
				}
			}
			looper = true;
			while (looper) {
				looper = false;
				for (int i = 0; i < temp.length() - 1; i++) {
					if (stringarr[i].equals("")) {
						continue;
					}
					next2 = 1;
					while (stringarr[i + next2].equals("")) {
						if (next2 + i == temp.length()-1) {
							break;
						}
						next2++;
					}
					String temp2 = stringarr[i] + stringarr[i + next2];
					for (int j = 0; j < compare.length; j++) {
						if (temp2.equals(compare[j])) {
							stringarr[i] = "";
							stringarr[i + next2] = "";
							looper = true;
						}
					}
				}
			}
			for (int i = 0; i < 50000; i++) {

				if (stringarr[i].equals("")) {
				} else {
					finalScore++;
				}
			}
			System.out.println("There are only " + finalScore + " units Left out of a possible " + temp.length()
					+ " units starting with the pair " + compare[k]);

		}
		sc.close();
	}}
