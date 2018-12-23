
public class Day22BothParts {
/*
	public static int depth = 510;
	public static int targetx = 10;
	public static int targety = 10;
*/
	public static int depth = 11541;
	public static int targetx = 14;
	public static int targety = 778;
	public static void main(String args[]) {
		System.out.println("Part 1 = " + part1());
		System.out.println("Part 2 = " + part2());
	}

	private static int part2() {
		int x = targetx * 2;
		int y = targety * 2;
		int[][] cave2 = new int[x][y];
		int[][][] times = new int[x][y][3];
		for (int i = 0; i < times.length; i++) {
			for (int j = 0; j < times[0].length; j++) {
				for (int k = 0; k < times[0][0].length; k++) {
					if (i != 0 || j != 0 || k != 0) {
						times[i][j][k] = Integer.MAX_VALUE / 2;
					}
				}
			}
		}
		erosionLevel(cave2);
		regionType(cave2);
		scan(cave2, times, x, y);
		return times[targetx][targety][0];
	}

	private static void scan(int[][] cave2, int[][][] times, int x, int y) {
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			boolean flag = false;
			for (int j = 0; j < x; j++) {
				for (int k = 0; k < y; k++) {
					for (int l = 0; l < 3; l++) {
						int min = times[j][k][l];
						min = Math.min(min, checkTool(j - 1, k, l, times, cave2));
						min = Math.min(min, checkTool(j, k + 1, l, times, cave2));
						min = Math.min(min, checkTool(j + 1, k, l, times, cave2));
						min = Math.min(min, checkTool(j, k - 1, l, times, cave2));
						for (int m = 0; m < 3; m++) {
							if (m != l && (times[j][k][m] + 7 < min) && m != (cave2[j][k] + 2) % 3) {
								min = times[j][k][m] + 7;
							}
						}
						if (times[j][k][l] != min) {
							flag = true;
						}
						times[j][k][l] = min;
					}
				}
			}
			if (!flag) {
				break;
			}
		}
	}

	public static int part1() {
		int x = targetx + 1;
		int y = depth;
		int[][] data = new int[x][y];
		erosionLevel(data);
		regionType(data);
		return getRisk(data);
	}
	
	private static int checkTool(int j, int k, int l, int[][][] times, int[][] cave2) {
		if (j < 0 || j >= times.length || k < 0 || k >= times[0].length) {
			return Integer.MAX_VALUE;
		}
		int min = (l == (cave2[j][k] + 2) % 3) ? 100000 : times[j][k][l];
		for (int m = 0; m < 3; m++) {
			if (m != l && (times[j][k][m] + 7 < min) && m != (cave2[j][k] + 2) % 3) {
				min = times[j][k][m] + 7;
			}
		}
		return (l == (cave2[j][k] + 2) % 3) ? 100000 : min + 1;
	}

	private static void printData(int[][] data) {
		for (int y = 0; y <= targety; y++) {
			for (int x = 0; x <= targetx; x++) {
				if (x == 0 && y == 0) {
					System.out.print("M");
					continue;
				} else if (x == targetx && y == targety) {
					System.out.print("T");
					continue;
				}
				if (data[x][y] == 0) {
					System.out.print(".");
				} else if (data[x][y] == 1) {
					System.out.print("=");
				} else {
					System.out.print("|");
				}
			}
			System.out.println();
		}

	}

	public static int getRisk(int[][] data) {
		int output = 0;
		for (int y = 0; y <= targety; y++) {
			for (int x = 0; x <= targetx; x++) {
				output += data[x][y];
			}
		}
		return output;
	}

	public static void erosionLevel(int[][] data) {
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				if (y == 0) {
					if (x == 0) {
						data[x][y] = Math.abs(depth % 20183);
					} else {
						data[x][y] = Math.abs(((x * 16807) + depth) % 20183);
					}
				} else {
					if (x == 0) {
						data[x][y] = Math.abs(((y * 48271) + depth) % 20183);
					} else if (x == targetx && y == targety) {
						data[x][y] = Math.abs(depth % 20183);
					} else {
						data[x][y] = Math.abs((data[x - 1][y] * data[x][y - 1] + depth) % 20183);
					}
				}
			}
		}
	}

	public static void regionType(int[][] data) {
		for (int y = 0; y < data[0].length; y++) {
			for (int x = 0; x < data.length; x++) {
				data[x][y] = Math.abs(data[x][y] % 3);
			}
		}
	}
}
