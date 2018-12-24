import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day23BothParts {
	private static final String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1: " + part1());
		System.out.println("Part 2: " + part2());
	}

	private static int part2() throws FileNotFoundException {
		ArrayList<String> data = toData("input.txt");
		int scale = 10000000;
		int maxx = 0, maxy = 0, maxz = 0;
		int minx = Integer.MAX_VALUE, miny = Integer.MAX_VALUE, minz = Integer.MAX_VALUE;
		for (int i = 0; i < data.size(); i++) {
			String[] temp1 = data.get(i).split("r=");
			String[] temp2 = data.get(i).split("<");
			String[] temp3 = temp2[1].split(">");
			String[] xyz = temp3[0].split(",");
			int curx = Integer.parseInt(xyz[0]) / scale;
			int cury = Integer.parseInt(xyz[1]) / scale;
			int curz = Integer.parseInt(xyz[2]) / scale;
			int curr = Integer.parseInt(temp1[1]) / scale;
			if (curx + curr > maxx) {
				maxx = curx + curr;
			}
			if (cury + curr > maxy) {
				maxy = cury + curr;
			}
			if (curz + curr > maxz) {
				maxz = curz + curr;
			}
			if (curx - curr < minx) {
				minx = curx - curr;
			}
			if (cury - curr < miny) {
				miny = cury - curr;
			}
			if (curz - curr < minz) {
				minz = curz - curr;
			}
		}
		int highestCount = 0, bestx = 0, besty = 0, bestz = 0;
		int output = 0;
		while (scale >= 1) {
			highestCount = 0;
			bestx = 0;
			besty = 0;
			bestz = 0;
			for (int z = minz; z < maxz; z++) {
				System.out.println(z+ " out of " +maxz);
				for (int y = miny; y < maxy; y++) {
					for (int x = minx; x < maxx; x++) {
						int count = 0;
						for (int i = 0; i < data.size(); i++) {
							
							String[] temp1 = data.get(i).split("r=");
							String[] temp2 = data.get(i).split("<");
							String[] temp3 = temp2[1].split(">");
							String[] xyz = temp3[0].split(",");
							int curx = Integer.parseInt(xyz[0]) / scale;
							int cury = Integer.parseInt(xyz[1]) / scale;
							int curz = Integer.parseInt(xyz[2]) / scale;
							int curr = Integer.parseInt(temp1[1]) / scale;
							if (range(curx, cury, curz, curr, x, y, z)) {
								count++;
							}
						}
						if (count > highestCount) {
							highestCount = count;
							bestx = x;
							besty = y;
							bestz = z;
						}
					}
				}
			}
			System.out.println(highestCount);
			System.out.println(bestx + " " + besty + " " + bestz);
			maxx = bestx + 5;
			minx = bestx - 5;
			maxy = besty + 5;
			miny = besty - 5;
			maxz = bestz + 5;
			minz = bestz - 5;
			output += (scale * bestx) + (scale * besty) + (scale * bestz);
			System.out.println((scale * bestx) + "," + (scale * besty) + "," + (scale * bestz));
			scale /= 10;
		}
		return output;
	}

	private static Boolean range(int x1, int y1, int z1, int r, int x2, int y2, int z2) {
		int distance = Math.abs(x1 - x2) + Math.abs(y1 - y2) + Math.abs(z1 - z2);
		if (distance <= r) {
			return true;
		}
		return false;
	}

	private static int part1() throws FileNotFoundException {
		ArrayList<String> data = toData("input.txt");
		int strongest = 0, strongestr = 0;
		for (int i = 0; i < data.size(); i++) {
			String[] temp = data.get(i).split("r=");
			if (Integer.parseInt(temp[1]) >= strongestr) {
				strongestr = Integer.parseInt(temp[1]);
				strongest = i;
			}
		}
		String[] stronga = data.get(strongest).split("<");
		String[] strongb = stronga[1].split(">");
		String[] c = strongb[0].split(",");
		int inRange = 0;
		for (int i = 0; i < data.size(); i++) {
			String[] temp1 = data.get(i).split("<");
			String[] temp2 = temp1[1].split(">");
			String[] a = temp2[0].split(",");
			if (range(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]), strongestr,
					Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]))) {
				inRange++;
			}
		}
		return inRange;
	}

	private static ArrayList<String> toData(String file) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + file));
		ArrayList<String> data = new ArrayList<>();
		while (sc.hasNext()) {
			data.add(sc.nextLine());
		}
		sc.close();
		return data;
	}

}
