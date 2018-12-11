import java.util.Scanner;

public class Day11BothParts {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is the serial number? ");
		int serial = sc.nextInt();
		sc.close();
		System.out.println("The top left fuel cell of the 3x3 square is: " + part1(serial));
		System.out.println("The top left fuel cell of any square is: " + part2(serial));
	}

	public static String part1(int serial) {
		String output;

		int[][] data = new int[301][301];
		for (int x = 1; x < data.length; x++) {
			for (int y = 1; y < data.length; y++) {
				int rackID = x + 10;
				int powerLevel = rackID * y;
				int step3 = powerLevel + serial;
				int step4 = step3 * rackID;
				int step5 = (int) (Math.floor(step4 / 100) % 10);
				data[x][y] = step5 - 5;
			}
		}

		int[] largestCoord = new int[2];
		int largestSize = 0;
		for (int x1 = 1; x1 < data.length - 2; x1++) {
			for (int y1 = 1; y1 < data.length - 2; y1++) {
				int currentSize = 0;
				for (int x2 = 0; x2 < 3; x2++) {
					for (int y2 = 0; y2 < 3; y2++) {
						currentSize += data[x2 + x1][y2 + y1];
					}
				}
				if (currentSize >= largestSize) {
					largestSize = currentSize;
					largestCoord[0] = x1;
					largestCoord[1] = y1;
				}
			}
		}

		output = largestCoord[0] + "," + largestCoord[1];
		System.out.println("Power level: " + largestSize);
		return output;
	}

	public static String part2(int serial) {
		String output;

		int[][] data = new int[301][301];
		for (int x = 1; x < data.length; x++) {
			for (int y = 1; y < data.length; y++) {
				int rackID = x + 10;
				int powerLevel = rackID * y;
				int step3 = powerLevel + serial;
				int step4 = step3 * rackID;
				int step5 = (int) (Math.floor(step4 / 100) % 10);
				data[x][y] = step5 - 5;
			}
		}

		int[] largestCoord = new int[2];
		int largestSize = 0;
		int gridSize = 0;
		for (int size = 1; size < data.length; size++) {
			for (int x1 = 1; x1 < data.length - size+1; x1++) {
				for (int y1 = 1; y1 < data.length - size+1; y1++) {
					int currentSize = 0;
					for (int x2 = 0; x2 < size; x2++) {
						for (int y2 = 0; y2 < size; y2++) {
							currentSize += data[x2 + x1][y2 + y1];
						}
					}
					if (currentSize >= largestSize) {
						largestSize = currentSize;
						largestCoord[0] = x1;
						largestCoord[1] = y1;
						gridSize = size;
					}
				}
			}
		}

		output = largestCoord[0] + "," + largestCoord[1] + "," + gridSize;
		System.out.println("Power level: " + largestSize);
		return output;
	}
}
