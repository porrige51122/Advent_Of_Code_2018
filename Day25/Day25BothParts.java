import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day25BothParts {
	private static final String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1 = " + part1());
	}

	private static int part1() throws FileNotFoundException {
		ArrayList<int[]> data = parse("Input.txt");
		int constellation = 0;
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i)[4] == 1) {
				continue;
			}
			data.get(i)[4] = 1;
			check(data, i, data.get(i)[0], data.get(i)[1], data.get(i)[2], data.get(i)[3]);
			constellation++;
		}
		return constellation;
	}

	private static void check(ArrayList<int[]> data, int current, int a1, int b1, int c1, int d1) {
		for (int j = 0; j < data.size(); j++) {
			if (data.get(j)[4] == 1) {
				continue;
			}
			if (constellation(a1, b1, c1, d1, data.get(j)[0], data.get(j)[1], data.get(j)[2], data.get(j)[3])) {
				data.get(j)[4] = 1;
				check(data, j, data.get(j)[0], data.get(j)[1], data.get(j)[2], data.get(j)[3]);
			}
		}
	}

	private static Boolean constellation(int a1, int b1, int c1, int d1, int a2, int b2, int c2, int d2) {
		int distance = Math.abs(a1 - a2) + Math.abs(b1 - b2) + Math.abs(c1 - c2) + Math.abs(d1 - d2);
		if (distance <= 3) {
			return true;
		}
		return false;
	}

	private static ArrayList<int[]> parse(String input) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + input));
		ArrayList<int[]> data = new ArrayList<>();
		while (sc.hasNextLine()) {
			int[] temp = new int[5];
			String[] l = sc.nextLine().split(",");
			temp[0] = Integer.parseInt(l[0]);
			temp[1] = Integer.parseInt(l[1]);
			temp[2] = Integer.parseInt(l[2]);
			temp[3] = Integer.parseInt(l[3]);
			data.add(temp);
		}
		return data;
	}
}
