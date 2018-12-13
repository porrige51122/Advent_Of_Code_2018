import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day13BothParts {
	private static String path = "C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day13\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Example 1 = " + Example1());
		System.out.println("Part 1 = " + Part1());
		System.out.println("Part 2 = " + Part2());
	}

	public static String Example1() throws FileNotFoundException {
		String output = "";

		Scanner sc = new Scanner(new File(path + "example.txt"));
		String[][] data = new String[13][6];
		String[][] minecart = new String[13][6];
		sc.useDelimiter("");
		for (int y = 0; y < 6; y++) {
			String temp = sc.nextLine();
			String[] temparr = temp.split("");
			for (int x = 0; x < 13; x++) {
				data[x][y] = temparr[x];
			}
		}
		sc.close();

		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 13; x++) {
				minecart[x][y] = "";
			}
		}
		int minecartCount = 0;
		for (int y = 0; y < 6; y++) {
			for (int x = 0; x < 13; x++) {
				if (data[x][y].equals("^") || data[x][y].equals("v")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "|";
					minecartCount++;
				} else if (data[x][y].equals(">") || data[x][y].equals("<")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "-";
					minecartCount++;
				}

			}
		}
		int[][] mcCoords = new int[minecartCount][2];
		int seconds = 0;

		Boolean checker = true;
		while (checker) {
			int curCart = 0;
			for (int y = 0; y < 6; y++) {
				for (int x = 0; x < 13; x++) {
					if (!minecart[x][y].equals("")) {
						int[] temp = minecartLocation(data, x, y, seconds, minecart[x][y]);
						mcCoords[curCart][0] = temp[0];
						mcCoords[curCart][1] = temp[1];
						for (int i = 0; i < minecartCount; i++) {
							if (curCart == i) {
								continue;
							}
							if (mcCoords[curCart][0] == mcCoords[i][0] && mcCoords[curCart][1] == mcCoords[i][1]) {
								output = "(" + mcCoords[curCart][0] + "," + mcCoords[curCart][1] + ")";
								return output;
							}

						}
						curCart++;
					}
				}
			}

			seconds++;
		}
		return output;
	}

	public static String Part1() throws FileNotFoundException {
		String output = "";

		Scanner sc = new Scanner(new File(path + "input.txt"));
		String[][] data = new String[150][150];
		String[][] minecart = new String[150][150];
		sc.useDelimiter("");
		for (int y = 0; y < 150; y++) {
			String temp = sc.nextLine();
			String[] temparr = temp.split("");
			for (int x = 0; x < 150; x++) {
				data[x][y] = temparr[x];
			}
		}
		sc.close();

		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				minecart[x][y] = "";
			}
		}

		int minecartCount = 0;
		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				if (data[x][y].equals("^") || data[x][y].equals("v")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "|";
					minecartCount++;
				} else if (data[x][y].equals(">") || data[x][y].equals("<")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "-";
					minecartCount++;
				}

			}
		}
		int[][] initmcCoords = new int[minecartCount][2];
		int count = 0;
		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				if (!minecart[x][y].equals("")) {
					initmcCoords[count][0] = x;
					initmcCoords[count][1] = y;
					count++;
				}
			}
		}

		int seconds = 0;
		Boolean checker = true;
		while (checker) {
			for (int i = 0; i < initmcCoords.length; i++) {
				if (initmcCoords[i][0] == -1) {
					continue;
				}
				for (int j = 0; j < initmcCoords.length; j++) {
					if (i == j) {
						continue;
					}
					if (initmcCoords[j][0] == -1) {
						continue;
					}
					int[] temp1 = (minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds,
							minecart[initmcCoords[i][0]][initmcCoords[i][1]]));
					int[] temp2 = (minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds + 1,
							minecart[initmcCoords[i][0]][initmcCoords[i][1]]));
					int[] temp3 = (minecartLocation(data, initmcCoords[j][0], initmcCoords[j][1], seconds,
							minecart[initmcCoords[j][0]][initmcCoords[j][1]]));
					int[] temp4 = (minecartLocation(data, initmcCoords[j][0], initmcCoords[j][1], seconds + 1,
							minecart[initmcCoords[j][0]][initmcCoords[j][1]]));
					if ((temp1[0] == temp3[0] && temp1[1] == temp3[1])) {
						int[] temp = minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds + 1,
								minecart[initmcCoords[i][0]][initmcCoords[i][1]]);
						output = "(" + temp[0] + "," + temp[1] + ")";
						return output;
					} else if (temp2[0] == temp3[0] && temp4[0] == temp1[0] && temp2[1] == temp3[1]
							&& temp4[1] == temp1[1]) {
						int[] temp = minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds,
								minecart[initmcCoords[i][0]][initmcCoords[i][1]]);
						output = "(" + temp[0] + "," + temp[1] + ")";
						return output;
					}

				}
			}
			seconds++;
		}
		return output;
	}

	public static String Part2() throws FileNotFoundException {
		String output = "";

		Scanner sc = new Scanner(new File(path + "input.txt"));
		String[][] data = new String[150][150];
		String[][] minecart = new String[150][150];
		sc.useDelimiter("");
		for (int y = 0; y < 150; y++) {
			String temp = sc.nextLine();
			String[] temparr = temp.split("");
			for (int x = 0; x < 150; x++) {
				data[x][y] = temparr[x];
			}
		}
		sc.close();

		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				minecart[x][y] = "";
			}
		}

		int minecartCount = 0;
		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				if (data[x][y].equals("^") || data[x][y].equals("v")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "|";
					minecartCount++;
				} else if (data[x][y].equals(">") || data[x][y].equals("<")) {
					minecart[x][y] = data[x][y];
					data[x][y] = "-";
					minecartCount++;
				}

			}
		}
		int[][] initmcCoords = new int[minecartCount][2];
		int count = 0;
		for (int y = 0; y < 150; y++) {
			for (int x = 0; x < 150; x++) {
				if (!minecart[x][y].equals("")) {
					initmcCoords[count][0] = x;
					initmcCoords[count][1] = y;
					count++;
				}
			}
		}

		int seconds = 0;
		Boolean checker = true;
		while (checker) {
			f: for (int i = 0; i < initmcCoords.length; i++) {
				if (initmcCoords[i][0] == -1) {
					continue;
				}
				for (int j = 0; j < initmcCoords.length; j++) {
					if (i == j) {
						continue;
					}
					if (initmcCoords[j][0] == -1) {
						continue;
					}
					int[] temp1 = (minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds,
							minecart[initmcCoords[i][0]][initmcCoords[i][1]]));
					int[] temp2 = (minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds + 1,
							minecart[initmcCoords[i][0]][initmcCoords[i][1]]));
					int[] temp3 = (minecartLocation(data, initmcCoords[j][0], initmcCoords[j][1], seconds,
							minecart[initmcCoords[j][0]][initmcCoords[j][1]]));
					int[] temp4 = (minecartLocation(data, initmcCoords[j][0], initmcCoords[j][1], seconds + 1,
							minecart[initmcCoords[j][0]][initmcCoords[j][1]]));
					if ((temp1[0] == temp3[0] && temp1[1] == temp3[1]) || (temp2[0] == temp3[0] && temp4[0] == temp1[0]
							&& temp2[1] == temp3[1] && temp4[1] == temp1[1])) {
						initmcCoords[i][0] = -1;
						initmcCoords[j][0] = -1;
						break f;
					}

				}
			}
			count = 0;
			for (int i = 0; i < minecartCount; i++) {
				if (initmcCoords[i][0] == -1) {
					continue;
				} else {
					count++;
				}
			}
			if (count == 1) {
				for (int i = 0; i < minecartCount; i++) {
					if (initmcCoords[i][0] == -1) {
						continue;
					} else {
						int[] temp = minecartLocation(data, initmcCoords[i][0], initmcCoords[i][1], seconds + 1,
								minecart[initmcCoords[i][0]][initmcCoords[i][1]]);
						output = "(" + temp[0] + "," + temp[1] + ")";
						return output;
					}
				}
			}
			seconds++;
		}
		return output;
	}

	public static int[] minecartLocation(String[][] data, int x, int y, int seconds, String direction) {
		int[] output = new int[2];
		int turnCount = 3;
		for (int s = 0; s <= seconds; s++) {
			if (direction.equals("^")) {
				if (data[x][y].equals("|")) {
					y--;
				} else if (data[x][y].equals("/")) {
					direction = ">";
					x++;
				} else if (data[x][y].equals("\\")) {
					direction = "<";
					x--;
				} else {
					if (turnCount % 3 == 0) {
						direction = "<";
						x--;
					} else if (turnCount % 3 == 1) {
						y--;
					} else {
						direction = ">";
						x++;
					}
					turnCount++;
				}
			} else if (direction.equals("v")) {
				if (data[x][y].equals("|")) {
					y++;
				} else if (data[x][y].equals("/")) {
					direction = "<";
					x--;
				} else if (data[x][y].equals("\\")) {
					direction = ">";
					x++;
				} else {
					if (turnCount % 3 == 0) {
						direction = ">";
						x++;
					} else if (turnCount % 3 == 1) {
						y++;
					} else {
						direction = "<";
						x--;
					}
					turnCount++;
				}
			} else if (direction.equals(">")) {
				if (data[x][y].equals("-")) {
					x++;
				} else if (data[x][y].equals("/")) {
					direction = "^";
					y--;
				} else if (data[x][y].equals("\\")) {
					direction = "v";
					y++;
				} else {
					if (turnCount % 3 == 0) {
						direction = "^";
						y--;
					} else if (turnCount % 3 == 1) {
						x++;
					} else {
						direction = "v";
						y++;
					}
					turnCount++;
				}
			} else if (direction.equals("<")) {
				if (data[x][y].equals("-")) {
					x--;
				} else if (data[x][y].equals("/")) {
					direction = "v";
					y++;
				} else if (data[x][y].equals("\\")) {
					direction = "^";
					y--;
				} else {
					if (turnCount % 3 == 0) {
						direction = "v";
						y++;
					} else if (turnCount % 3 == 1) {
						x--;
					} else {
						direction = "^";
						y--;
					}
					turnCount++;
				}
			} else {
				System.out.println("ERROR");
			}
		}
		output[0] = x;
		output[1] = y;
		return output;
	}
}
