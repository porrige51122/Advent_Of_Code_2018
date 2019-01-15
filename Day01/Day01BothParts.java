import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day01BothParts {
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1 = " + part1());
		System.out.println("Part 2 = " + part2());
	}

	private static int part1() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		int x = 0;
		while (sc.hasNextLine()) {
			x += sc.nextInt();
		}
		sc.close();
		return x;
	}

	private static int part2() throws FileNotFoundException {
		int x = 0;
		ArrayList<Integer> y = new ArrayList<>();
		while (true) {
			Scanner sc = new Scanner(new File(path + "input.txt"));
			while (sc.hasNextLine()) {
				x += sc.nextInt();
				for (int i = 0; i < y.size(); i++) {
					if (x == y.get(i)) {
						sc.close();
						return x;
					}
				}
				y.add(x);
			}
			sc.close();
		}
	}
}
