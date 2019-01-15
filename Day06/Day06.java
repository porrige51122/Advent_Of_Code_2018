import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day06 {
	public static String path = "C:\\Users\\aidan\\Desktop\\";
	public static ArrayList<int[]> input = new ArrayList<>();

	public static void main(String args[]) throws FileNotFoundException {
		parse();
		System.out.println("Part 1 = " + part1());
		System.out.println("Part 2 = " + part2());
	}

	private static void parse() throws FileNotFoundException {
		Pattern p = Pattern.compile("(\\d++), (\\d++)");
		Scanner sc = new Scanner(new File(path + "input.txt"));
		while (sc.hasNextLine()) {
			int[] a = new int[2];
			Matcher b = p.matcher(sc.nextLine());
			if (b.matches()) {
				a[0] = Integer.parseInt(b.group(1));
				a[1] = Integer.parseInt(b.group(2));
			}
			input.add(a);
		}
	}

	private static int part1() {
		int[] pointvalues1 = new int[input.size()];
		int[] pointvalues2 = new int[input.size()];
		int[] pointvalues3 = new int[input.size()];
		getSize(pointvalues1, 0, 500, 0, 500);
		getSize(pointvalues2, -10, 510, -10, 510);
		for (int i = 0; i < input.size(); i++)
			if (pointvalues1[i] == pointvalues2[i])
				pointvalues3[i] = pointvalues1[i];
		int output = 0;
		for (int i : pointvalues3)
			if (i != 0 && output < i)
				output = i;
		return output;
	}

	private static int part2() {
		int output = 0;
		int outputa = 0;
		int ymin = 0;
		int ymax = 500;
		int xmin = 0;
		int xmax = 500;
		do {
			output = outputa;
			outputa = 0;
			for (int y = ymin; y < ymax; y++)
				for (int x = xmin; x < xmax; x++)
					if (range(x, y))
						outputa++;
			ymin -= 50;
			ymax += 50;
			xmin -= 50;
			xmax += 50;
		} while (outputa != output);
		return output;
	}

	private static Boolean range(int x, int y) {
		int total = 0;
		for (int i = 0; i < input.size(); i++)
			total += distance(x, y, input.get(i)[0], input.get(i)[1]);
		if (total < 10000)
			return true;
		return false;
	}

	private static void getSize(int[] data, int xmin, int xmax, int ymin, int ymax) {
		for (int y = ymin; y < ymax; y++)
			for (int x = xmin; x < xmax; x++)
				data[closest(x, y)]++;
	}

	private static int closest(int x, int y) {
		int min = 0;
		int minD = Integer.MAX_VALUE;
		for (int i = 0; i < input.size(); i++) {
			int a = distance(x, y, input.get(i)[0], input.get(i)[1]);
			if (minD > a) {
				minD = a;
				min = i;
			}
		}
		return min;
	}

	private static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x2 - x1) + Math.abs(y2 - y1);
	}
}
