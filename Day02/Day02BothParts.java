import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day02BothParts {
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1 = " + part1());
		System.out.println("Part 2 = " + part2());
	}

	private static int part1() throws FileNotFoundException {
		int[] c = new int['z' - 'a' + 1];
		Scanner sc = new Scanner(new File(path + "input.txt"));
		int d = 0;
		int t = 0;
		Boolean db = false;
		Boolean tb = false;
		while (sc.hasNextLine()) {
			char[] l = sc.nextLine().toCharArray();
			for (int i = 0; i < l.length; i++) {
				c[l[i] - 'a']++;
			}
			for (int i = 0; i < c.length; i++) {
				if (c[i] == 2) {
					db = true;
				} else if (c[i] == 3) {
					tb = true;
				}
				c[i] = 0;
			}
			if (db) {
				db = false;
				d++;
			}
			if (tb) {
				tb = false;
				t++;
			}
		}
		sc.close();
		return d * t;
	}

	private static String part2() throws FileNotFoundException {
		Scanner a = new Scanner(new File(path + "input.txt"));
		ArrayList<char[]> l = new ArrayList<>();
		while (a.hasNextLine()) {
			l.add(a.nextLine().toCharArray());
		}
		int position = 0;
		String output = "";
		for (int i = 0; i < l.size(); i++) {
			for (int j = i + 1; j < l.size(); j++) {
				int count = 0;
				for (int k = 0; k < l.get(0).length; k++) {
					if (l.get(i)[k] != l.get(j)[k]) {
						position = k;
						count++;
					}
					if (count > 1) {
						break;
					}
					if (k == (l.get(0).length - 1)) {
						for (int o = 0; o < l.get(i).length; o++) {
							if (o != position)
								output += l.get(i)[o];
						}
						a.close();
						return output;
					}
				}
			}
		}
		a.close();
		return "ERROR";
	}
}
