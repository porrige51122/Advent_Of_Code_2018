import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day18Part1 {
	private static String path = "";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Example = " + Example());
		System.out.println("Example = " + Part1());
	}

	public static int Example() throws FileNotFoundException {
		char[][] data = toData("Example.txt", 10);
		for (int i = 0; i < 10; i++) {
			char[][] tempdata = new char[10][10];
			data = update(data, tempdata);
//			printData(data);
//			System.out.println();
		}
		int wooded = 0;
		int lumberyard = 0;
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '|') {
					wooded++;
				} else if (data[x][y] == '#') {
					lumberyard++;
				}
			}
		}
		return wooded * lumberyard;
	}
	
	public static int Part1() throws FileNotFoundException {
		char[][] data = toData("Input.txt", 50);
		for (int i = 0; i < 10; i++) {
			char[][] tempdata = new char[50][50];
			data = update(data, tempdata);
//			printData(data);
//			System.out.println();
		}
		int wooded = 0;
		int lumberyard = 0;
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '|') {
					wooded++;
				} else if (data[x][y] == '#') {
					lumberyard++;
				}
			}
		}
		return wooded * lumberyard;
	}
	
	public static char[][] update(char[][] data, char[][] tempdata) {
		for (int y = 0; y < data[0].length; y++) {
			if (y == 0) {
				for (int x = 0; x < data.length; x++) {
					if (x == 0) {
						tempdata[x][y] = check(data[x][y], '!', '!', '!', '!', data[x+1][y], '!', data[x][y+1], data[x+1][y+1]);
					} else if (x == data.length - 1) {
						tempdata[x][y] = check(data[x][y], '!', '!', '!', data[x-1][y], '!', data[x-1][y+1], data[x][y+1], '!');
					} else {
						tempdata[x][y] = check(data[x][y], '!', '!', '!', data[x-1][y], data[x+1][y], data[x-1][y+1], data[x][y+1], data[x+1][y+1]);
					}
				}
			} else if (y == data[0].length - 1) {
				for (int x = 0; x < data.length; x++) {
					if (x == 0) {
						tempdata[x][y] = check(data[x][y], '!', data[x][y-1], data[x+1][y-1], '!', data[x+1][y], '!', '!', '!');
					} else if (x == data.length - 1) {
						tempdata[x][y] = check(data[x][y], data[x-1][y-1], data[x][y-1], '!', data[x-1][y],'!' , '!', '!', '!');
					} else {
						tempdata[x][y] = check(data[x][y], data[x-1][y-1], data[x][y-1], data[x+1][y-1], data[x-1][y], data[x+1][y], '!', '!', '!');
					}
				}
			} else {
				for (int x = 0; x < data.length; x++) {
					if (x == 0) {
						tempdata[x][y] = check(data[x][y], '!', data[x][y-1], data[x+1][y-1], '!', data[x+1][y], '!', data[x][y+1], data[x+1][y+1]);
					} else if (x == data.length - 1) {
						tempdata[x][y] = check(data[x][y], data[x-1][y-1], data[x][y-1], '!' , data[x-1][y], '!', data[x-1][y+1], data[x][y+1], '!');
					} else {
						tempdata[x][y] = check(data[x][y], data[x-1][y-1], data[x][y-1],  data[x+1][y-1] , data[x-1][y], data[x+1][y], data[x-1][y+1], data[x][y+1], data[x+1][y+1]);
					}
				}
			}
		}
		return tempdata;
	}

	public static char check(char current, char a, char b, char c, char d, char e, char f, char g, char h) {
		int wood = 0;
		int lumber = 0;
		if (a == '|') {
			wood++;
		} else if (a == '#') {
			lumber++;
		}
		if (b == '|') {
			wood++;
		} else if (b == '#') {
			lumber++;
		}
		if (c == '|') {
			wood++;
		} else if (c == '#') {
			lumber++;
		}
		if (d == '|') {
			wood++;
		} else if (d == '#') {
			lumber++;
		}
		if (e == '|') {
			wood++;
		} else if (e == '#') {
			lumber++;
		}
		if (f == '|') {
			wood++;
		} else if (f == '#') {
			lumber++;
		}
		if (g == '|') {
			wood++;
		} else if (g == '#') {
			lumber++;
		}
		if (h == '|') {
			wood++;
		} else if (h == '#') {
			lumber++;
		}
		if (current == '.') {
			if (wood >= 3) {
				return '|';
			} else {
				return '.';
			}
		} else if (current == '|') {
			if (lumber >= 3) {
				return '#';
			} else {
				return '|';
			}
		} else {
			if (lumber > 0 && wood > 0) {
				return '#';
			} else {
				return '.';
			}
		}
		
		
	}
	public static void printData(char[][] data) {
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				System.out.print(data[x][y]);
			}
			System.out.println();
		}
	}

	public static char[][] toData(String filename, int size) throws FileNotFoundException {
		char[][] data = new char[size][size];
		Scanner sc = new Scanner(new File(path + filename));
		for (int y = 0; y < size; y++) {
			String temp = sc.nextLine();
			char[] temparr = temp.toCharArray();
			for (int x = 0; x < size; x++) {
				data[x][y] = temparr[x];
			}
		}
		return data;
	}
}
