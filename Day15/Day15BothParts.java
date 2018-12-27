import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Day15BothParts {

	public static String path = "C:\\Users\\aidan\\Desktop\\";
	public static int weapon = 3;
//	public static int weapon = 30;

	public static void main(String args[]) throws FileNotFoundException {
//		PrintStream out = new PrintStream(new FileOutputStream(path +"output.txt"));
//		System.setOut(out);

		System.out.println("Part 1 = " + Part1());
//		System.out.println("Part 2 = " + Part2());
	}

	public static int Part1() throws FileNotFoundException {
		char[][] data = toData("Input.txt");
		int[][] entities = toEntities(data);
		int round = 0;
		printData(data, entities);
		Boolean endG = true;
		Boolean endE = true;
		while (endG && endE) {
			Boolean nextRound = true;
			round++;
//			printData(data, entities);
			System.out.println("round = " + round);
			int count = 0;
			for (int e = 0; e < entities.length; e++)
				entities[e][4] = 0;
			out: while (nextRound) {
				int current = entities.length;
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][4] == 0 && entities[e][2] > 0) {
						current = e;
						break;
					}
				}
				if (current == entities.length) {
					endG = false;
					endE = false;
					nextRound = false;
					for (int e = 0; e < entities.length; e++) {
						if (entities[e][2] > 0 && entities[e][3] == 0) {
							endE = true;
						} else if (entities[e][2] > 0 && entities[e][3] == 1) {
							endG = true;
						}
					}
					break out;
				}
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][4] == 0 && entities[e][2] > 0) {
						if ((entities[e][1] == entities[current][1] && entities[e][0] < entities[current][0])
								|| (entities[e][1] < entities[current][1])) {
							current = e;
						}
					}
				}

				move(data, entities, current);
//				System.out.println(count+ "/" + entities.length);
				count++;

			}
		}
		int entitiesLeft = 0;
		for (int e = 0; e < entities.length; e++) {
			if (entities[e][2] >= 0) {
				entitiesLeft += entities[e][2];
			}
		}

		return (round - 1) * entitiesLeft;
	}

	public static int Part2() throws FileNotFoundException {
		char[][] data = toData("Input.txt");
		int[][] entities = toEntities(data);
		int round = 0;
		printData(data, entities);
		Boolean endG = true;
		Boolean endE = true;
		while (endG && endE) {
			Boolean nextRound = true;
			round++;
			printData(data, entities);
			System.out.println("round = " + round);
			for (int e = 0; e < entities.length; e++)
				entities[e][4] = 0;
			int count = 1;
			out: while (nextRound) {

				int current = entities.length;
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][4] == 0 && entities[e][2] > 0) {
						current = e;
						break;
					}
				}
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][2] <= 0 && entities[e][3] == 0) {
						endE = false;
						break out;
					}
				}
				if (current == entities.length) {
					endG = false;
					endE = false;
					nextRound = false;
					for (int e = 0; e < entities.length; e++) {
						if (entities[e][2] > 0 && entities[e][3] == 0) {
							endE = true;
						} else if (entities[e][2] > 0 && entities[e][3] == 1) {
							endG = true;
						}
					}
					break out;
				}
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][4] == 0 && entities[e][2] > 0) {
						if ((entities[e][1] == entities[current][1] && entities[e][0] < entities[current][0])
								|| (entities[e][1] < entities[current][1])) {
							current = e;
						}
					}
				}

				move(data, entities, current);
//				System.out.println(count+ "/" + entities.length + " " + entities[current][3]);
//				count++;

			}
		}
		int entitiesLeft = 0;
		for (int e = 0; e < entities.length; e++) {
			if (entities[e][2] >= 0) {
				entitiesLeft += entities[e][2];
			}
		}

		System.out.println(entitiesLeft);
		return (round - 1) * entitiesLeft;
	}

	public static void move(char[][] data, int[][] entities, int current) {
		int counter = destination(data, entities, current);
		clearData(data, ' ');
		if (counter >= 1) {
			int[] bestDest = getBestDest(data);
			int minx = entities[current][0] - bestDest[0];
			int miny = entities[current][1] - bestDest[1];
			int minN;
			int minS;
			int minE;
			int minW;
			if (minx > 0) {
				minW = Math.abs(minx);
				minE = 0;
			} else {
				minW = 0;
				minE = Math.abs(minx);
			}
			if (miny > 0) {
				minN = Math.abs(miny);
				minS = 0;
			} else {
				minN = 0;
				minS = Math.abs(miny);
			}
			String path = path(data, entities[current][0], entities[current][1], counter, 0, minN, minS, minE, minW);
			clearData(data, '+');
			char[] patharr = path.toCharArray();
			int direction = patharr[patharr.length - 1] - 48;
			movement(data, entities, current, direction);
		}
		if (counter <= 1) {
			if (entities[current][3] == 0) {
				data[entities[current][0]][entities[current][1]] = 'E';
			} else {
				data[entities[current][0]][entities[current][1]] = 'G';
			}
			Boolean checker = false;
			int currentHealth = 201;
			int recieveingEntity = 0;
			for (int y = 0; y < data[0].length; y++) {
				for (int x = 0; x < data.length; x++) {
					for (int e = 0; e < entities.length; e++) {
						if (entities[e][0] == x && entities[e][1] == y) {
							if (e == current) {
								continue;
							}
							if (((entities[e][0] - 1 == entities[current][0] && entities[e][1] == entities[current][1])
									|| (entities[e][0] + 1 == entities[current][0]
											&& entities[e][1] == entities[current][1])
									|| (entities[e][0] == entities[current][0]
											&& entities[e][1] - 1 == entities[current][1])
									|| (entities[e][0] == entities[current][0]
											&& entities[e][1] + 1 == entities[current][1]))
									&& entities[current][3] != entities[e][3] && entities[e][2] > 0) {

								if (entities[e][2] < currentHealth) {
									currentHealth = entities[e][2];
									checker = true;
									recieveingEntity = e;
								}

							}
						} else {
							continue;
						}
					}
				}
			}
			if (checker) {
				if (entities[current][3] == 0) {
					entities[recieveingEntity][2] -= weapon;
				} else {
					entities[recieveingEntity][2] -= 3;
				}

				if (entities[recieveingEntity][2] <= 0) {
					data[entities[recieveingEntity][0]][entities[recieveingEntity][1]] = '.';
				}
			}
		}
		entities[current][4] = 1;
	}

	private static int[] getBestDest(char[][] data) {
		int bestx = 0;
		int besty = 0;
		out: for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '+') {
					bestx = x;
					besty = y;
					clearData(data, '+');
					break out;
				}
			}
		}
		data[bestx][besty] = '+';
		int[] bestxy = new int[2];
		bestxy[0] = bestx;
		bestxy[1] = besty;
		return bestxy;

	}

	public static void movement(char[][] data, int[][] entities, int current, int direction) {
		if (direction == 1) {
			entities[current][1]--;
			if (entities[current][3] == 0) {
				data[entities[current][0]][entities[current][1]] = 'E';
			} else {
				data[entities[current][0]][entities[current][1]] = 'G';
			}
			return;
		} else if (direction == 2) {
			entities[current][0]--;
			if (entities[current][3] == 0) {
				data[entities[current][0]][entities[current][1]] = 'E';
			} else {
				data[entities[current][0]][entities[current][1]] = 'G';
			}
			return;
		} else if (direction == 3) {
			entities[current][0]++;
			if (entities[current][3] == 0) {
				data[entities[current][0]][entities[current][1]] = 'E';
			} else {
				data[entities[current][0]][entities[current][1]] = 'G';
			}
			return;
		} else if (direction == 4) {
			entities[current][1]++;
			if (entities[current][3] == 0) {
				data[entities[current][0]][entities[current][1]] = 'E';
			} else {
				data[entities[current][0]][entities[current][1]] = 'G';
			}
			return;
		} else {
			return;
		}
	}

	public static String path(char[][] data, int currentx, int currenty, int limit, int direction, int minN, int minS,
			int minE, int minW) {
		String output = "";
		if (limit == 0 || ((minN > 0 ? minN : 0) + (minE > 0 ? minE : 0) + (minS > 0 ? minS : 0)
				+ (minW > 0 ? minW : 0)) > limit) {
			return "";
		}
		if (!output.equals("")) {
			return (output);
		}
		if ((data[currentx][currenty - 1] == '$' && data[currentx + 1][currenty] == '$')
				|| (data[currentx][currenty - 1] == '$' && data[currentx - 1][currenty] == '$')
				|| (data[currentx][currenty + 1] == '$' && data[currentx + 1][currenty] == '$')
				|| (data[currentx][currenty + 1] == '$' && data[currentx - 1][currenty] == '$')
				|| (data[currentx][currenty + 1] == '$' && data[currentx][currenty - 1] == '$')
				|| (data[currentx + 1][currenty] == '$' && data[currentx - 1][currenty] == '$')) {
			return "";
		}
		if (data[currentx][currenty - 1] == '+') {
			return "1";
		}
		if (data[currentx - 1][currenty] == '+') {
			return "2";
		}
		if (data[currentx + 1][currenty] == '+') {
			return "3";
		}
		if (data[currentx][currenty + 1] == '+') {
			return "4";
		}
		if (data[currentx][currenty - 1] == '.' && direction != 4) {
			data[currentx][currenty - 1] = '$';
			minN--;
			Boolean opposite = false;
			if (minN < 0) {
				opposite = true;
				minS++;
			}
			output += path(data, currentx, currenty - 1, limit - 1, 1, minN, minS, minE, minW);
			minN++;
			if (opposite) {
				opposite = false;
				minS--;
			}
			data[currentx][currenty - 1] = '.';
			if (!output.equals("")) {
				return (output + "1");
			}
		}
		if (data[currentx - 1][currenty] == '.' && direction != 3) {
			data[currentx - 1][currenty] = '$';
			minW--;
			Boolean opposite = false;
			if (minW < 0) {
				opposite = true;
				minE++;
			}
			output += path(data, currentx - 1, currenty, limit - 1, 2, minN, minS, minE, minW);
			minW++;
			if (opposite) {
				opposite = false;
				minE--;
			}
			data[currentx - 1][currenty] = '.';
			if (!output.equals("")) {
				return (output + "2");
			}
		}
		if (data[currentx + 1][currenty] == '.' && direction != 2) {
			data[currentx + 1][currenty] = '$';
			minE--;
			Boolean opposite = false;
			if (minE < 0) {
				opposite = true;
				minW++;
			}
			output += path(data, currentx + 1, currenty, limit - 1, 3, minN, minS, minE, minW);
			minE++;
			if (opposite) {
				opposite = false;
				minW--;
			}
			data[currentx + 1][currenty] = '.';
			if (!output.equals("")) {
				return (output + "3");
			}
		}
		if (data[currentx][currenty + 1] == '.' && direction != 1) {
			data[currentx][currenty + 1] = '$';
			minS--;
			Boolean opposite = false;
			if (minS < 0) {
				opposite = true;
				minN++;
			}
			output += path(data, currentx, currenty + 1, limit - 1, 4, minN, minS, minE, minW);
			minS++;
			if (opposite) {
				opposite = false;
				minN--;
			}
			data[currentx][currenty + 1] = '.';
			if (!output.equals("")) {
				return (output + "4");
			}
		}
		return "";
	}

	public static int destination(char[][] data, int[][] entities, int current) {
		data[entities[current][0]][entities[current][1]] = '!';
		int counter = 0;
		while (counter < data.length + data[0].length) {

			Boolean checker = true;
			for (int y = 0; y < data[0].length; y++) {
				for (int x = 0; x < data.length; x++) {
					if (data[x][y] == '!') {
						for (int e = 0; e < entities.length; e++) {
							if (e == current) {
								continue;
							}
							if (((entities[e][0] - 1 == x && entities[e][1] == y)
									|| (entities[e][0] + 1 == x && entities[e][1] == y)
									|| (entities[e][0] == x && entities[e][1] - 1 == y)
									|| (entities[e][0] == x && entities[e][1] + 1 == y))
									&& entities[current][3] != entities[e][3] && entities[e][2] > 0) {

								data[x][y] = '+';
//								printData(data, entities);
								checker = false;
							}
						}
					}
				}
			}
			if (checker) {
				flood(data, entities);
				counter++;
			} else {
				return counter;
			}
		}
		return -1;
	}

	public static void flood(char[][] data, int[][] entities) {
//		printData(data, entities);
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '!') {
					if (data[x - 1][y] == '.') {
						data[x - 1][y] = '/';
					}
					if (data[x + 1][y] == '.') {
						data[x + 1][y] = '/';
					}
					if (data[x][y - 1] == '.') {
						data[x][y - 1] = '/';
					}
					if (data[x][y + 1] == '.') {
						data[x][y + 1] = '/';
					}
					data[x][y] = '*';
				}
			}
		}
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '/') {
					data[x][y] = '!';
				}
			}
		}

	}

	public static void clearData(char[][] data, char input) {
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (data[x][y] == '*' || data[x][y] == '!' || data[x][y] == input) {
					data[x][y] = '.';
				}
			}
		}
	}

	public static void printData(char[][] data, int[][] entities) {
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				System.out.print(data[x][y]);
			}
			System.out.print("   ");
			for (int x = 0; x < data.length; x++) {
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][1] == y && entities[e][0] == x && entities[e][2] > 0) {
						if (entities[e][3] == 0) {
							System.out.print("E(" + entities[e][2] + "), ");
						} else {
							System.out.print("G(" + entities[e][2] + "), ");
						}
					}
				}
			}
			System.out.println();
		}

	}

	public static int[][] toEntities(char[][] data) {
		int noOfEntities = 0;
		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[0].length; x++) {
				if (data[x][y] == 'E' || data[x][y] == 'G') {
					noOfEntities++;
				}
			}
		}
		int[][] entities = new int[noOfEntities][5];
		/*
		 * [0] = x coordinate [1] = y coordinate [2] = health; [3] = Elf or Goblin. E =
		 * 0 G = 1 [4] = Has attacked?
		 */
		int count = 0;
		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[0].length; x++) {
				if (data[x][y] == 'E' || data[x][y] == 'G') {
					entities[count][0] = x;
					entities[count][1] = y;
					entities[count][2] = 200;
					if (data[x][y] == 'E') {
						entities[count][3] = 0;
					} else {
						entities[count][3] = 1;
					}
					count++;
				}
			}
		}
		return entities;
	}

	public static char[][] toData(String input) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + input));
		int xsize;
		int ysize = 0;
		String tempx = sc.nextLine();
		xsize = tempx.length();
		sc = new Scanner(new File(path + input));
		while (sc.hasNextLine()) {
			sc.nextLine();
			ysize++;
		}
		sc = new Scanner(new File(path + input));
		sc.useDelimiter("");
		char[][] data = new char[xsize][ysize];
		for (int y = 0; y < ysize; y++) {
			String temp = sc.nextLine();
			char[] tempy = temp.toCharArray();
			for (int x = 0; x < xsize; x++) {
				data[x][y] = tempy[x];
			}
		}
		sc.close();
		return data;
	}
}
