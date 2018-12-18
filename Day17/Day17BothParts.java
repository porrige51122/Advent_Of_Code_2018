import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day17BothParts {

	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		int[][] data = toData();
		System.out.println("part1 = " + part1(data));
		System.out.println("part2 = " + part2(data));
	}

	public static int part1(int[][] data) {
		int currentx = 500;
		int currenty = 0;
		data[currentx][currenty] = 1;
		fall(data, currentx, currenty);
		int count = 0;

		for (int y = 4; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == 1 || data[x][y] == 3) {
					count++;
				}
			}
		}

		return count;
	}

	public static int part2(int[][] data) {

		for (int y = 4; y < data[0].length - 1; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == 3 && (data[x+1][y - 1] == 1 ||data[x-1][y - 1] == 1 )) {
					int x1 = x;
					int y1 = y - 1;
					data[x][y1] = 3;
					if (data[x1 + 1][y1] != 1) {
						while (data[x1 - 1][y1] == 1) {
							data[x1 - 1][y1] = 3;
							x1--;
						}
					} else {
						while (data[x1 + 1][y1] == 1) {
							data[x1 + 1][y1] = 3;
							x1++;
						}
					}
				}
			}
		}
//		for (int y = 1500; y < data[0].length; y++) {
//			for (int x = 300; x < data.length; x++) {
//				if (data[x][y] == 0) {
//					System.out.print(".");
//				} else if (data[x][y] == 1) {
//					System.out.print("~");
//				} else if (data[x][y] == 2) {
//					System.out.print("#");
//				} else if (data[x][y] == 3) {
//					System.out.print("|");
//				} else {
//					System.out.print(data[x][y]);
//				}
//			}
//			System.out.println();
//		}
		int count = 0;
		for (int y = 4; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	public static void fall(int[][] data, int currentx, int currenty) {
		if (currenty + 1 >= data[0].length) {
		} else if (data[currentx][currenty + 1] != 2) {
			data[currentx][currenty + 1] = 3;
			fall(data, currentx, currenty + 1);
		} else {
			Boolean a, b;
			do {
				data[currentx][currenty] = 1;
				a = fillLeft(data, currentx, currenty);
				b = fillRight(data, currentx, currenty);
				currenty--;
			} while (a && b);
			currenty++;
		}
	}

	public static boolean fillLeft(int[][] data, int currentx, int currenty) {
		if (currentx + 1 >= data.length || currentx <= 0) {
			return true;
		}
		if (data[currentx - 1][currenty] == 2) {
			return true;
		} else {
			if (data[currentx][currenty + 1] != 0) {
				data[currentx - 1][currenty] = 1;
				return fillLeft(data, currentx - 1, currenty);
			} else {
				if (data[currentx + 1][currenty + 1] == 2) {
					data[currentx][currenty] = 1;
					fall(data, currentx, currenty);
					return false;
				} else {
					data[currentx][currenty] = 0;
					return false;
				}

			}
		}
	}

	public static boolean fillRight(int[][] data, int currentx, int currenty) {
		if (currentx + 1 >= data.length || currentx - 1 < 0) {
			return true;
		}
		if (data[currentx + 1][currenty] == 2) {
			return true;
		} else {
			if (data[currentx][currenty + 1] != 0) {
				data[currentx + 1][currenty] = 1;
				return fillRight(data, currentx + 1, currenty);
			} else {
				if (data[currentx - 1][currenty + 1] == 2) {
					data[currentx][currenty] = 1;
					data[currentx - 1][currenty] = 1;
					fall(data, currentx, currenty);
					return false;
				} else {
					data[currentx][currenty] = 0;
					return false;
				}

			}
		}
	}

	public static int[][] toData() throws FileNotFoundException {
		int[][] data = new int[600][1623];
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				data[x][y] = 0;
			}
		}
		Scanner sc = new Scanner(new File(path + "input.txt"));
		while (sc.hasNextLine()) {
			String temp = sc.nextLine();
			String[] A = temp.split(", ");
			String[] B = A[0].split("=");
			String[] C = A[1].split("=");
			String[] D = C[1].split("\\.\\.");
			String xOry = B[0];
			int a = Integer.parseInt(B[1]);
			int b = Integer.parseInt(D[0]);
			int c = Integer.parseInt(D[1]);
			data = plot(data, xOry, a, b, c);
		}
		sc.close();
		return data;
	}

	public static int[][] plot(int[][] data, String xOry, int a, int b, int c) {
		if (xOry.equals("x")) {
			for (int i = b; i <= c; i++) {
				data[a][i] = 2;
			}
		} else {
			for (int i = b; i <= c; i++) {
				data[i][a] = 2;
			}
		}
		return data;
	}
}
