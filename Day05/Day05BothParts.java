import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day05BothParts {
	public static String path = "C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day5\\";

	public static void main(String args[]) throws FileNotFoundException {
//		long atime = System.currentTimeMillis();
		System.out.println("Part 1 = " + part1());
//		long btime = System.currentTimeMillis();
		System.out.println("Part 2 = " + part2());
//		long ctime = System.currentTimeMillis();
//		System.out.println("Part 1 time = " + (btime - atime));
//		System.out.println("Part 2 time = " + (ctime - btime));
	}

	private static int part1() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		sc.useDelimiter("");
		ArrayList<Character> data = new ArrayList<>();
		while (sc.hasNext())
			data.add(sc.next().charAt(0));
		for (int i = 1; i < data.size(); i++)
			if (data.get(i) == (data.get(i - 1) - ('A' - 'a')) || data.get(i) == (data.get(i - 1) - ('a' - 'A'))) {
				data.remove(i);
				data.remove(i - 1);
				i = i <= 2 ? 0 : i - 2;
			}
		return data.size();
	}

	private static int part2() throws FileNotFoundException {
		int least = Integer.MAX_VALUE;
		for (int l = 'A'; l <= 'Z'; l++) {
			Scanner sc = new Scanner(new File(path + "input.txt"));
			sc.useDelimiter("");
			ArrayList<Character> data = new ArrayList<>();
			while (sc.hasNext()) {
				char temp = sc.next().charAt(0);
				if (temp != l && temp != l - ('A' - 'a'))
					data.add(temp);
			}
			for (int i = 1; i < data.size(); i++)
				if (data.get(i) == (data.get(i - 1) - ('A' - 'a')) || data.get(i) == (data.get(i - 1) + ('A' - 'a'))) {
					data.remove(i);
					data.remove(i - 1);
					i = i <= 2 ? 0 : i - 2;
				}
			least = Math.min(data.size(), least);
		}
		return least;
	}
}
