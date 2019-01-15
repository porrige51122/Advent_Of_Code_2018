import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03BothParts {
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		byte[][] data = new byte[1000][1000];
		System.out.println("Part 1 = " + part1(data));
		System.out.println("Part 2 = " + part2(data));
	}

	private static int part1(byte[][] data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		Pattern p = Pattern.compile("#\\d++ @ (\\d++),(\\d++): (\\d++)x(\\d++)");
		while (sc.hasNextLine()) {
			Matcher m = p.matcher(sc.nextLine());
			if (m.matches())
				for (int y = Integer.parseInt(m.group(2)); y < Integer.parseInt(m.group(2))
						+ Integer.parseInt(m.group(4)); y++)
					for (int x = Integer.parseInt(m.group(1)); x < Integer.parseInt(m.group(1))
							+ Integer.parseInt(m.group(3)); x++)
						data[x][y]++;
		}
		int o = 0;
		for (int y = 0; y < data[0].length; y++)
			for (int x = 0; x < data.length; x++)
				if (data[x][y] > 1)
					o++;
		sc.close();
		return o;
	}

	private static String part2(byte[][] data) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		Pattern p = Pattern.compile("#(\\d++) @ (\\d++),(\\d++): (\\d++)x(\\d++)");
		while (sc.hasNextLine()) {
			Matcher m = p.matcher(sc.nextLine());
			if (m.matches())
				out: for (int y = Integer.parseInt(m.group(3)); y < Integer.parseInt(m.group(3))
						+ Integer.parseInt(m.group(5)); y++)
					for (int x = Integer.parseInt(m.group(2)); x < Integer.parseInt(m.group(2))
							+ Integer.parseInt(m.group(4)); x++) {
						data[x][y]++;
						if (data[x][y] != 2)
							break out;
						if (y == Integer.parseInt(m.group(3)) + Integer.parseInt(m.group(5)) - 1
								&& x == Integer.parseInt(m.group(2)) + Integer.parseInt(m.group(4)) - 1) {
							return m.group(1);
						}
					}
		}
		return null;
	}
}
