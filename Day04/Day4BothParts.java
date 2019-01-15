import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day4BothParts {
	public static String path = "C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day4\\";
	public static Pattern p = Pattern.compile("\\W\\d++-(\\d++)-(\\d++) (\\d++):(\\d++)\\W (.*)");
	public static ArrayList<String> o = new ArrayList<>();
	public static ArrayList<String> g = new ArrayList<>();

	public static void main(String args[]) throws FileNotFoundException {
		sort();
		guards();
		main();
	}

	private static void main() {
		int[][] slp = new int[g.size()][60];
		int cur = 0;
		int time = 0;
		for (int i = 0; i < o.size(); i++) {
			Matcher m = p.matcher(o.get(i));
			m.matches();
			String a = m.group(5);
			if (!a.equals("falls asleep") && !a.equals("wakes up"))
				for (int j = 0; j < g.size(); j++)
					if (a.equals(g.get(j))) {
						cur = j;
						break;
					}
			if (a.equals("falls asleep"))
				time = Integer.parseInt(m.group(4));
			if (a.equals("wakes up"))
				for (int j = time; j < Integer.parseInt(m.group(4)); j++)
					slp[cur][j]++;
		}
		int larT = 0;
		int p1 = 0;
		int larM = 0;
		int p2 = 0;
		for (int i = 0; i < slp.length; i++) {
			int s = 0;
			int l1 = 0;
			int l3 = 0;
			for (int j = 0; j < 60; j++) {
				s += slp[i][j];
				if (slp[i][j] > l1) {
					l1 = slp[i][j];
					l3 = j;
				}
				if (slp[i][j] > larM) {
					larM = slp[i][j];
					p2 = j * Integer.parseInt(g.get(i).split(" ")[1].split("#")[1]);
				}
			}
			if (s > larT) {
				larT = s;
				p1 = l3 * Integer.parseInt(g.get(i).split(" ")[1].split("#")[1]);
			}
		}
		System.out.println("Part 1 = " + p1);
		System.out.println("Part 2 = " + p2);
	}

	private static void guards() {
		for (int i = 0; i < o.size(); i++) {
			Matcher b = p.matcher(o.get(i));
			b.matches();
			String a = b.group(5);
			if (!a.equals("falls asleep") && !a.equals("wakes up")) {
				if (i == 0)
					g.add(a);
				else
					for (int j = 0; j < g.size(); j++)
						if (a.equals(g.get(j)))
							break;
						else if (j == g.size() - 1)
							g.add(a);
			}
		}
	}

	private static void sort() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		o.add(sc.nextLine());
		while (sc.hasNextLine()) {
			Matcher a = p.matcher(sc.nextLine());
			a.matches();
			for (int i = 0; i < o.size(); i++) {
				Matcher b = p.matcher(o.get(i));
				b.matches();
				int ta = Integer.parseInt(a.group(4)) + (Integer.parseInt(a.group(3)) * 100)
						+ (Integer.parseInt(a.group(2)) * 10000) + (Integer.parseInt(a.group(1)) * 1000000);
				int tb = Integer.parseInt(b.group(4)) + (Integer.parseInt(b.group(3)) * 100)
						+ (Integer.parseInt(b.group(2)) * 10000) + (Integer.parseInt(b.group(1)) * 1000000);
				if (ta < tb || i == o.size() - 1) {
					o.add(i, a.group(0));
					break;
				}
			}
		}
		sc.close();
	}
}
