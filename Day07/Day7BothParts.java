import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day7BothParts {
	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Example1 = " + Example1());
		System.out.println("Part1 = " + Part1());
		System.out.println("Example2 = " + Example2());
		System.out.println("Part1 = " + Part2());

	}

	public static String Example1() throws FileNotFoundException {
		String output = "";
		Scanner sc = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Example1.txt"));
		char[][][] data = new char[7][2][2];
		for (int i = 0; i < 7; i++) {
			String temp = sc.nextLine();
			char[] temparr = temp.toCharArray();
			data[i][0][0] = temparr[5];
			data[i][1][0] = temparr[36];
		}
		int counter = 0;
		char second = ' ';
		while (counter < 7) {
			char first = 'z';
			int firstint = 6;
			boolean checker = false;
			for (int i = 0; i < 7; i++) {
				checker = true;
				if (data[i][0][1] == 'z') {
					continue;
				}
				for (int j = 0; j < 7; j++) {
					if (data[j][1][1] == 'z') {
						continue;
					}
					if (data[i][0][0] == data[j][1][0]) {
						checker = false;
					}

				}
				if (checker == true) {
					second = data[i][0][0];
					if (second < first) {
						first = second;
						firstint = i;
					}
				}
			}
			if (first == 'z') {
				break;
			}

			for (int i = 0; i < 7; i++) {
				if (data[i][0][0] == data[firstint][0][0]) {
					data[i][0][1] = 'z';
					data[i][1][1] = 'z';
				}
			}
			output += first;
			counter++;
		}

		for (int i = 0; i < 7; i++) {
			if (data[i][0][0] == second) {
				output += data[i][1][0];
				break;
			}
		}
		sc.close();
		return output;
	}

	public static String Part1() throws FileNotFoundException {
		String output = "";
		Scanner sc = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Input1.txt"));
		int lineCount = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			lineCount++;
		}
		sc.close();

		Scanner sc2 = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Input1.txt"));

		char[][][] data = new char[lineCount][2][2];
		for (int i = 0; i < lineCount; i++) {
			String temp = sc2.nextLine();
			char[] temparr = temp.toCharArray();
			data[i][0][0] = temparr[5];
			data[i][1][0] = temparr[36];
		}
		sc2.close();
		int counter = 0;
		char second = ' ';
		while (counter < lineCount) {
			char first = 'z';
			int firstint = 6;
			boolean checker = false;
			for (int i = 0; i < lineCount; i++) {
				checker = true;
				if (data[i][0][1] == 'z') {
					continue;
				}
				for (int j = 0; j < lineCount; j++) {
					if (data[j][1][1] == 'z') {
						continue;
					}
					if (data[i][0][0] == data[j][1][0]) {
						checker = false;
					}

				}
				if (checker == true) {
					second = data[i][0][0];
					if (second < first) {
						first = second;
						firstint = i;
					}
				}
			}
			if (first == 'z') {
				break;
			}

			for (int i = 0; i < lineCount; i++) {
				if (data[i][0][0] == data[firstint][0][0]) {
					data[i][0][1] = 'z';
					data[i][1][1] = 'z';
				}
			}
			output += first;
			counter++;
		}

		for (int i = 0; i < lineCount; i++) {
			if (data[i][0][0] == second) {
				output += data[i][1][0];
				break;
			}
		}
		return output;
	}

	public static int Example2() throws FileNotFoundException {
		int seconds = 0;
		Scanner sc = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Example1.txt"));
		int lineCount = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			lineCount++;
		}
		sc.close();

		Scanner sc2 = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Example1.txt"));
		char[][][] data = new char[lineCount][2][2];
		for (int i = 0; i < lineCount; i++) {
			String temp = sc2.nextLine();
			char[] temparr = temp.toCharArray();
			data[i][0][0] = temparr[5];
			data[i][1][0] = temparr[36];
		}
		int[] elf = new int[2];
		char[] elf2 = new char[2];
		sc2.close();
		f: while (true) {
			boolean checker = false;

			for (int e = 0; e < elf.length; e++) {
				if (elf[e] > 0) {
					elf[e]--;
				}
			}
			int lazyElf = 6;
			for (int e = elf.length - 1; e >= 0; e--) {
				if (elf[e] <= 0) {
					lazyElf = e;
					for (int i = 0; i < lineCount; i++) {
						if (elf2[e] == data[i][0][0]) {
							data[i][1][1] = 'z';
						}
					}
				}
			}

			if (lazyElf == 6) {
				seconds++;
				continue f;
			}

			char def = 'z';
			for (int i = 0; i < lineCount; i++) {
				checker = true;
				if (data[i][0][1] == 'z') {
					continue;
				}
				for (int j = 0; j < lineCount; j++) {
					if (data[j][1][1] == 'z') {
						continue;
					}
					if (data[i][0][0] == data[j][1][0]) {
						checker = false;
					}
				}
				if (checker) {
					def = data[i][0][0];
					elf[lazyElf] = data[i][0][0] - ('A' - 1);
					elf2[lazyElf] = data[i][0][0];
					for (int c = 0; c < lineCount; c++) {
						if (data[i][0][0] == data[c][0][0]) {
							data[c][0][1] = 'z';
						}
					}
					lazyElf = 6;
					for (int e = elf.length - 1; e >= 0; e--) {
						if (elf[e] <= 0) {
							lazyElf = e;
							for (int f = 0; f < lineCount; f++) {
								if (elf2[e] == data[i][0][0]) {
									data[i][1][1] = 'z';
								}
							}
							elf2[e] = 'z';
						}
					}

					if (lazyElf == 6) {
						seconds++;
						continue f;
					}
				}
			}

			checker = true;
			for (int e = 0; e < elf.length; e++) {
				if (elf[e] > 0) {
					checker = false;
				}
			}

			if (def == 'z' && checker) {
				break;
			}
			seconds++;
		}
		for (int i = 0; i < lineCount; i++) {
			if (data[i][0][0] == elf2[0]) {
				seconds += data[i][1][0] - ('A' - 1);
				break;
			}
		}
		return seconds;
	}
	
	public static int Part2() throws FileNotFoundException {
		int seconds = 0;
		Scanner sc = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Input1.txt"));
		int lineCount = 0;
		while (sc.hasNextLine()) {
			sc.nextLine();
			lineCount++;
		}
		sc.close();

		Scanner sc2 = new Scanner(new File("C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day7\\Input1.txt"));
		char[][][] data = new char[lineCount][2][2];
		for (int i = 0; i < lineCount; i++) {
			String temp = sc2.nextLine();
			char[] temparr = temp.toCharArray();
			data[i][0][0] = temparr[5];
			data[i][1][0] = temparr[36];
		}
		sc2.close();
		int[] elf = new int[5];
		char[] elf2 = new char[5];
		f: while (true) {
			boolean checker = false;

			for (int e = 0; e < elf.length; e++) {
				if (elf[e] > 0) {
					elf[e]--;
				}
			}
			int lazyElf = 6;
			for (int e = elf.length - 1; e >= 0; e--) {
				if (elf[e] <= 0) {
					lazyElf = e;
					for (int i = 0; i < lineCount; i++) {
						if (elf2[e] == data[i][0][0]) {
							data[i][1][1] = 'z';
						}
					}
				}
			}

			if (lazyElf == 6) {
				seconds++;
				continue f;
			}

			char def = 'z';
			for (int i = 0; i < lineCount; i++) {
				checker = true;
				if (data[i][0][1] == 'z') {
					continue;
				}
				for (int j = 0; j < lineCount; j++) {
					if (data[j][1][1] == 'z') {
						continue;
					}
					if (data[i][0][0] == data[j][1][0]) {
						checker = false;
					}
				}
				if (checker) {
					def = data[i][0][0];
					elf[lazyElf] = data[i][0][0] - ('A' - 1) + 60;
					elf2[lazyElf] = data[i][0][0];
					for (int c = 0; c < lineCount; c++) {
						if (data[i][0][0] == data[c][0][0]) {
							data[c][0][1] = 'z';
						}
					}
					lazyElf = 6;
					for (int e = elf.length - 1; e >= 0; e--) {
						if (elf[e] <= 0) {
							lazyElf = e;
							for (int f = 0; f < lineCount; f++) {
								if (elf2[e] == data[i][0][0]) {
									data[i][1][1] = 'z';
								}
							}
							elf2[e] = 'z';
						}
					}

					if (lazyElf == 6) {
						seconds++;
						continue f;
					}
				}
			}

			checker = true;
			for (int e = 0; e < elf.length; e++) {
				if (elf[e] > 0) {
					checker = false;
				}
			}

			if (def == 'z' && checker) {
				break;
			}
			seconds++;
		}
		for (int i = 0; i < lineCount; i++) {
			if (data[i][0][0] == elf2[0]) {
				seconds += data[i][1][0] - ('A' - 1) + 60;
				break;
			}
		}
		return seconds;
		
	}
}
