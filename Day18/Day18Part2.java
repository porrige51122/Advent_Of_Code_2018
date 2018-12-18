import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day18Part2 {
	private static String path = "";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 2 = " + Part2());
	}

	public static int Part2() throws FileNotFoundException {
		int size = 50;
		HashMap<Integer, Character> data = toData("Input.txt", size);
		int[] repetition = new int[0];
		int initialRepeatRound = 0;
		int round = 0;
		Boolean Checkera = true;
		Boolean Checkerb = true;
		Boolean Checkerc = true;
		out: for (int i = 0; i < 10000; i++) {
			round++;
			HashMap<Integer, Character> tempdata = new HashMap<Integer, Character>();
			data = update(data, tempdata, size);
			int wooded = 0;
			int lumberyard = 0;
			for (int y = 0; y < size * size; y += size) {
				for (int x = 0; x < size; x++) {
					if (data.get(x + y) == '|') {
						wooded++;
					} else if (data.get(x + y) == '#') {
						lumberyard++;
					}
				}
			}
			Boolean Checker = true;
			for (int k = 0; k < repetition.length; k++) {
				if (lumberyard * wooded == repetition[k]) {
					initialRepeatRound = i;
					Checker = false;
					if (Checkera) {
						Checkera = false;
					} else if (Checkerb) {
						Checkerb = false;
					} else if (Checkerc) {
						Checkerc = false;
					} else {
						break out;
					}
					
				}
			}
			
			if (Checker) {
				Checkera = true;
				Checkerb = true;
				Checkerc = true;
				int[] temp = new int[repetition.length + 1];
				for (int j = 0; j < repetition.length; j++) {
					temp[j] = repetition[j];
				}
				temp[repetition.length] = wooded * lumberyard;
				repetition = temp;
			}
		}
		int firstRepeatRound = 0;
		int[] repetition2 = new int[0];
		out: for (int i = 0; i < 10000; i++) {
			round++;
			HashMap<Integer, Character> tempdata = new HashMap<Integer, Character>();
			data = update(data, tempdata, size);
			int wooded = 0;
			int lumberyard = 0;
			for (int y = 0; y < size * size; y += size) {
				for (int x = 0; x < size; x++) {
					if (data.get(x + y) == '|') {
						wooded++;
					} else if (data.get(x + y) == '#') {
						lumberyard++;
					}
				}
			}
			Boolean Checker = true;
			for (int k = 0; k < repetition2.length; k++) {
				if (lumberyard * wooded == repetition2[k]) {
					firstRepeatRound = i;
					Checker = false;
					break out;
					
				}
			}
			if (Checker) {
				int[] temp = new int[repetition2.length + 1];
				for (int j = 0; j < repetition2.length; j++) {
					temp[j] = repetition2[j];
				}
				temp[repetition2.length] = wooded * lumberyard;
				repetition2 = temp;
			}
		}
		int a = (1000000000 - round) % firstRepeatRound;
		for (int i = 0; i < a; i++) {
			HashMap<Integer, Character> tempdata = new HashMap<Integer, Character>();
			data = update(data, tempdata, size);
		}
		int wooded = 0;
		int lumberyard = 0;
		for (int y = 0; y < size * size; y += size) {
			for (int x = 0; x < size; x++) {
				if (data.get(x + y) == '|') {
					wooded++;
				} else if (data.get(x + y) == '#') {
					lumberyard++;
				}
			}
		}
		return wooded * lumberyard;
	}

	public static HashMap<Integer, Character> update(HashMap<Integer, Character> data,
			HashMap<Integer, Character> tempdata, int size) {
		for (int y = 0; y < size * size; y += size) {
			if (y == 0) {
				for (int x = 0; x < size; x++) {
					if (x == 0) {
						tempdata.put(x + y, check(data.get(x + y), '!', '!', '!', '!', data.get(x + 1 + y), '!',
								data.get(x + (y + size)), data.get(x + 1 + (y + size))));
					} else if (x == size - 1) {
						tempdata.put(x + y, check(data.get(x + y), '!', '!', '!', data.get(x - 1 + y), '!',
								data.get(x - 1 + (y + size)), data.get(x + (y + size)), '!'));
					} else {
						tempdata.put(x + y,
								check(data.get(x + y), '!', '!', '!', data.get(x - 1 + y), data.get(x + 1 + y),
										data.get(x - 1 + (y + size)), data.get(x + (y + size)),
										data.get(x + 1 + (y + size))));
					}
				}
			} else if (y == (size * size) - size) {
				for (int x = 0; x < size; x++) {
					if (x == 0) {
						tempdata.put(x + y, check(data.get(x + y), '!', data.get(x + (y - size)),
								data.get(x + 1 + (y - size)), '!', data.get(x + 1 + y), '!', '!', '!'));
					} else if (x == size - 1) {
						tempdata.put(x + y, check(data.get(x + y), data.get(x - 1 + (y - size)),
								data.get(x + (y - size)), '!', data.get(x - 1 + y), '!', '!', '!', '!'));
					} else {
						tempdata.put(x + y,
								check(data.get(x + y), data.get(x - 1 + (y - size)), data.get(x + (y - size)),
										data.get(x + 1 + (y - size)), data.get(x - 1 + y), data.get(x + 1 + y), '!',
										'!', '!'));
					}
				}
			} else {
				for (int x = 0; x < size; x++) {
					if (x == 0) {
						tempdata.put(x + y,
								check(data.get(x + y), '!', data.get(x + (y - size)), data.get(x + 1 + (y - size)), '!',
										data.get(x + 1 + y), '!', data.get(x + (y + size)),
										data.get(x + 1 + (y + size))));
					} else if (x == size - 1) {
						tempdata.put(x + y,
								check(data.get(x + y), data.get(x - 1 + (y - size)), data.get(x + (y - size)), '!',
										data.get(x - 1 + y), '!', data.get(x - 1 + (y + size)),
										data.get(x + (y + size)), '!'));
					} else {
						tempdata.put(x + y,
								check(data.get(x + y), data.get(x - 1 + (y - size)), data.get(x + (y - size)),
										data.get(x + 1 + (y - size)), data.get(x - 1 + y), data.get(x + 1 + y),
										data.get(x - 1 + (y + size)), data.get(x + (y + size)),
										data.get(x + 1 + (y + size))));
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

	public static void printData(HashMap<Integer, Character> data, int size) {
		for (int y = 0; y < size * size; y += size) {
			for (int x = 0; x < size; x++) {
				System.out.print(data.get(x + y));
			}
			System.out.println();
		}
	}

	public static HashMap<Integer, Character> toData(String filename, int size) throws FileNotFoundException {
		HashMap<Integer, Character> data = new HashMap<Integer, Character>();
		Scanner sc = new Scanner(new File(path + filename));
		for (int y = 0; y < size * size; y += size) {
			String temp = sc.nextLine();
			char[] temparr = temp.toCharArray();
			for (int x = 0; x < size; x++) {
				data.put(y + x, temparr[x]);
			}
		}
		return data;
	}
}
