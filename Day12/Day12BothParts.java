import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day12BothParts {
	public static String path = "C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day12\\";

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Example 1 = " + Example1());
		System.out.println("Part 1 = " + Part1());
		System.out.println("Part 2 = " + Part2());
	}

	public static int Example1() throws FileNotFoundException {
		int output = 0;
		String[] first = new String[45];
		String[] current = new String[45];
		Scanner sc = new Scanner(new File(path + "example.txt"));
		sc.useDelimiter("#");
		sc.next();
		sc.useDelimiter("");
		for (int i = 0; i < first.length; i++) {
			first[i] = ".";
		}
		for (int i = 5; i < 30; i++) {
			first[i] = sc.next();
		}
		String[][] patterns = new String[14][2];
		sc.next();
		sc.next();
		sc.next();
		sc.next();
		for (int i = 0; i < patterns.length; i++) {
			sc.useDelimiter(" ");
			patterns[i][0] = sc.next();
			sc.next();
			sc.useDelimiter("");
			sc.next();
			sc.useDelimiter("");
			patterns[i][1] = sc.next();
		}
		for (int g = 0; g < 20; g++) {
			for (int i = 0; i < current.length; i++) {
				current[i] = ".";
			}

			for (int i = 2; i < first.length - 2; i++) {
				String temp = "";
				temp = first[i - 2] + first[i - 1] + first[i] + first[i + 1] + first[i + 2];
				for (int j = 0; j < patterns.length; j++) {
					if (patterns[j][0].equals(temp)) {
						current[i] = patterns[j][1];
						break;
					}
				}
			}

			for (int i = 0; i < first.length; i++) {
				first[i] = current[i];
			}
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i].equals("#")) {
				output += i-5;
			}
		}
		sc.close();
		return output;
	}
	public static int Part1() throws FileNotFoundException {
		int output = 0;
		String[] first = new String[150];
		String[] current = new String[150];
		Scanner sc = new Scanner(new File(path + "part1.txt"));
		sc.useDelimiter("#");
		sc.next();
		sc.useDelimiter("");
		for (int i = 0; i < first.length; i++) {
			first[i] = ".";
		}
		for (int i = 5; i < 99; i++) {
			first[i] = sc.next();
		}
		String[][] patterns = new String[32][2];
		sc.next();
		sc.next();
		sc.next();
		sc.next();
		for (int i = 0; i < patterns.length; i++) {
			sc.useDelimiter(" ");
			patterns[i][0] = sc.next();
			sc.next();
			sc.useDelimiter("");
			sc.next();
			sc.useDelimiter("");
			patterns[i][1] = sc.next();
		}
		for (int g = 0; g < 20; g++) {
			for (int i = 0; i < current.length; i++) {
				current[i] = ".";
			}

			for (int i = 2; i < first.length - 2; i++) {
				String temp = "";
				temp = first[i - 2] + first[i - 1] + first[i] + first[i + 1] + first[i + 2];
				for (int j = 0; j < patterns.length; j++) {
					if (patterns[j][0].equals(temp)) {
						current[i] = patterns[j][1];
						break;
					}
				}
			}

			for (int i = 0; i < first.length; i++) {
				first[i] = current[i];
			}
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i].equals("#")) {
				output += i-5;
			}
		}
		sc.close();
		return output;
	}
	
	public static double Part2() throws FileNotFoundException {
		double output = 0;
		String[] first = new String[15000];
		String[] current = new String[15000];
		Scanner sc = new Scanner(new File(path + "part1.txt"));
		sc.useDelimiter("#");
		sc.next();
		sc.useDelimiter("");
		for (int i = 0; i < first.length; i++) {
			first[i] = ".";
		}
		for (int i = 5; i < 99; i++) {
			first[i] = sc.next();
		}
		String[][] patterns = new String[32][2];
		sc.next();
		sc.next();
		sc.next();
		sc.next();
		for (int i = 0; i < patterns.length; i++) {
			sc.useDelimiter(" ");
			patterns[i][0] = sc.next();
			sc.next();
			sc.useDelimiter("");
			sc.next();
			sc.useDelimiter("");
			patterns[i][1] = sc.next();
		}
		for (int g = 0; g <= 5000; g++) {
			for (int i = 0; i < current.length; i++) {
				current[i] = ".";
			}

			for (int i = 2; i < first.length - 2; i++) {
				String temp = "";
				temp = first[i - 2] + first[i - 1] + first[i] + first[i + 1] + first[i + 2];
				for (int j = 0; j < patterns.length; j++) {
					if (patterns[j][0].equals(temp)) {
						current[i] = patterns[j][1];
						break;
					}
				}
			}

			for (int i = 0; i < first.length; i++) {
				first[i] = current[i];
			}
		}
		for (int i = 0; i < first.length; i++) {
			if (first[i].equals("#")) {
				output += i-5;
			}
		}
		output *= 10000000;
		sc.close();
		return output;
	}
}
