import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day20BothParts {
	
	// With help from /u/rundavidrun as he changed me from using my current position from an
	// int array to x and y coordinates. and then everything started working.
	
	static int[][] dist = new int[250][250];
	static boolean[][] visited = new boolean[250][250];
	static char[][] map = new char[250][250];
	static char[] data;
	static int sum = 0;
	static int longest = 0;
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "Input.txt"));
		String input = sc.nextLine();
		sc.close();
		data = input.toCharArray();
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				map[x][y] = '#';
			}
		}
		int x = 125, y = 125;
		map[x][y] = 'X';
		path(x, y, 0, 0);
		printMap();
		System.out.println("Part 1 " + longest);
		System.out.println("Part 2 " + sum);
	}

	private static void path(int x, int y, int current, int distance) {
		while (current < data.length) {
			if (data[current] == 'E') {
				x++;
				map[x][y] = '|';
				x++;
				map[x][y] = '.';
			} else if (data[current] == 'W') {
				x--;
				map[x][y] = '|';
				x--;
				map[x][y] = '.';
			} else if (data[current] == 'N') {
				y--;
				map[x][y] = '-';
				y--;
				map[x][y] = '.';
			} else if (data[current] == 'S') {
				y++;
				map[x][y] = '-';
				y++;
				map[x][y] = '.';
			} else if (data[current] == '(') {
				int parenLevel = 0;
				boolean newCond = true;
				while (current < data.length) {
					current++;
					if (data[current] == '(') {
						parenLevel++;
					} else if (data[current] == ')') {
						parenLevel--;
						if (parenLevel < 0) {
							path(x, y, current + 1, distance);
							return;
						}
					} else if (data[current] == '|') {
						if (parenLevel == 0) {
							newCond = true;
						}
					} else if (parenLevel == 0) {
						if (newCond) {
							path(x, y, current, distance);
							newCond = false;
						}
					}
				}
			} else {
				return;
			}
			current++;
			distance++;
			if (distance >= 1000 && !visited[x][y]) {
				visited[x][y] = true;
				sum++;
			}
			if (dist[x][y] == 0 || dist[x][y] > distance) {
				dist[x][y] = distance;
				if (distance > longest) {
					longest = distance;
				}
			}
		}
	}

	private static void printMap() {
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				System.out.print(map[x][y]);
			}
			System.out.println();
		}
	}
}