import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day15 {

	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Example = " + Game());
	}

	public static int Game() throws FileNotFoundException {
		// From txt to char array
		char[][] data = toData("Example.txt");
		// takes E and G and converts them to entities
		int[][] entities = toEntities(data);
		int round = 0;
		printData(data, entities);
		Boolean endG = true;
		Boolean endE = true;
		while (endG && endE) {
			Boolean nextRound = true;
			round++;
			System.out.println("round = " + round);
//			printData(data, entities);
//			Scanner sc2 = new Scanner(System.in);
//			int wow2 = sc2.nextInt();
			for (int e = 0; e < entities.length; e++)
				entities[e][4] = 0;
			int count = 1;
			out: while (nextRound) {
				System.out.println(count+ "/" + entities.length);
				count++;
				// Chooses creature which is the next to move.
				// if none apply ends round
				// if no creatures are alive, ends program
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
						if (entities[e][2] >= 0 && entities[e][3] == 0) {
							endE = true;
						} else if (entities[e][2] >= 0 && entities[e][3] == 1) {
							endG = true;
						}
					}
					break out;
				}
				// selects current
				for (int e = 0; e < entities.length; e++) {
					if (entities[e][4] == 0 && entities[e][2] > 0) {
						if ((entities[e][1] == entities[current][1] && entities[e][0] < entities[current][0])
								|| (entities[e][1] < entities[current][1])) {
							current = e;
						}
					}
				}

				move(data, entities, current);

				entities[current][4] = 1;
				for (int y = 0; y < data[0].length; y++) {
					for (int x = 0; x < data.length; x++) {
						if (data[x][y] == '0' || data[x][y] == '1' || data[x][y] == '+' || data[x][y] == '*'
								|| data[x][y] == '2' || data[x][y] == '3' || data[x][y] == '4' || data[x][y] == '5'
								|| data[x][y] == '6' || data[x][y] == '7' || data[x][y] == '8' || data[x][y] == '9') {
							data[x][y] = '.';
						}
					}
				}
			}
		}
		int entitiesLeft = 0;
		for (int e = 0; e < entities.length; e++) {
			if (entities[e][2] >= 0) {
				entitiesLeft += entities[e][2];
			}
		}
		printData(data, entities);
		System.out.println(round);
		return (round - 1) * entitiesLeft;
	}

	public static void move(char[][] data, int[][] entities, int current) {
		int counter = destination(data, entities, current);
		clearData(data, ' ');
		if (counter >= 1) {
			String path = path(data, entities[current][0], entities[current][1], counter, 0);
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
				entities[recieveingEntity][2] -= 3;
				if (entities[recieveingEntity][2] <= 0) {
					data[entities[recieveingEntity][0]][entities[recieveingEntity][1]] = '.';
				}
			}
		}
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

	public static String path(char[][] data, int currentx, int currenty, int limit, int direction) {
		String output = "";
		if (limit == 0) {
			return "";
		}
		if (!output.equals("")) {
			return (output);
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
			output += path(data, currentx, currenty - 1, limit - 1, 1);
			if (!output.equals("")) {
				return (output + "1");
			}
		}
		if (data[currentx - 1][currenty] == '.' && direction != 3) {
			output += path(data, currentx - 1, currenty, limit - 1, 2);
			if (!output.equals("")) {
				return (output + "2");
			}
		}
		if (data[currentx + 1][currenty] == '.' && direction != 2) {
			output += path(data, currentx + 1, currenty, limit - 1, 3);
			if (!output.equals("")) {
				return (output + "3");
			}
		}
		if (data[currentx][currenty + 1] == '.' && direction != 1) {
			output += path(data, currentx, currenty + 1, limit - 1, 4);
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
								checker = false;
							}
						}
					}
				}
			}
			if (checker) {
				flood(data);
				counter++;
			} else {
				return counter;
			}
		}
		return -1;
	}

	public static void flood(char[][] data) {
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
