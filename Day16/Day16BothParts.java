import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day16BothParts {
	public static String path = "";

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "input.txt"));
		System.out.println("part1 = " + part1(sc));
		System.out.println("part2 = " + part2(sc));
	}

	public static int part1(Scanner sc) throws FileNotFoundException {
		int count = 0;
		int[][] opcode = new int[16][16];
		for (int i = 0; i < 812; i++) {
			String temp = sc.nextLine();
			char[] line1 = temp.toCharArray();
			int i0 = line1[9] - 48;
			int i1 = line1[12] - 48;
			int i2 = line1[15] - 48;
			int i3 = line1[18] - 48;

			int p = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();

			sc.nextLine();
			temp = sc.nextLine();
			char[] line3 = temp.toCharArray();
			int o0 = line3[9] - 48;
			int o1 = line3[12] - 48;
			int o2 = line3[15] - 48;
			int o3 = line3[18] - 48;
			sc.nextLine();
			if (test(a, b, c, i0, i1, i2, i3, o0, o1, o2, o3, opcode, p)) {
				count++;
			}
		}
		/*for (int k = 0; k <= opcode.length; k++) {
			for (int i = 0; i < opcode.length; i++) {
				int counter = 0;
				for (int j = 0; j < opcode.length; j++) {
					if (opcode[j][i] > 0) {
						counter++;
					}
				}
				if (counter == 1) {
					int currentj = 0;
					for (int j = 0; j < opcode.length; j++) {
						if (opcode[j][i] > 0) {
							opcode[j][i] = -1;
							currentj = j;
							break;
						}
					}
					for (int j = 0; j < opcode.length; j++) {
						if (opcode[currentj][j] > 0) {
							opcode[currentj][j] = 0;
						}
					}


				}

			}
		}
		for (int i = 0; i < opcode.length; i++) {
			for (int j = 0; j < opcode.length; j++) {
				if (opcode[i][j] == -1) {
					System.out.println((j+1) + " relates to " + i);
				}
			}
		}*/
		sc.nextLine();
		sc.nextLine();
		return count;
	}

	public static int part2(Scanner sc) throws FileNotFoundException {
		int[] data = new int[4];
		while (sc.hasNextLine()) {
			int p = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();
			int c = sc.nextInt();
			data = test2(data, p, a, b, c);
		}
		return data[0];
	}

	public static int[] test2(int[] data, int p, int a, int b, int c) {
		switch (p) {
		case 0:
			data[c] = mul2(data[a], data[b]);
			break;
		case 1:
			data[c] = eq2(data[a], b);
			break;
		case 2:
			data[c] = set2(data[a]);
			break;
		case 3:
			data[c] = eq2(data[a], data[b]);
			break;
		case 4:
			data[c] = gt2(data[a], data[b]);
			break;
		case 5:
			data[c] = mul2(data[a], b);
			break;
		case 6:
			data[c] = bor2(data[a], data[b]);
			break;
		case 7:
			data[c] = ban2(data[a], b);
			break;
		case 8:
			data[c] = add2(data[a], data[b]);
			break;
		case 9:
			data[c] = ban2(data[a], data[b]);
			break;
		case 10:
			data[c] = eq2(a, data[b]);
			break;
		case 11:
			data[c] = gt2(a, data[b]);
			break;
		case 12:
			data[c] = add2(data[a], b);
			break;
		case 13:
			data[c] = gt2(data[a], b);
			break;
		case 14:
			data[c] = set2(a);
			break;
		case 15:
			data[c] = bor2(data[a], b);
			break;
		default:
			System.out.println("ERROR");
			break;
		}
		return data;
	}

	public static boolean test(int a, int b, int c, int i0, int i1, int i2, int i3, int o0, int o1, int o2, int o3,
			int[][] opcode, int p) {
		int irega = getRegister(a, i0, i1, i2, i3);
		int iregb = getRegister(b, i0, i1, i2, i3);
		int oregc = getRegister(c, o0, o1, o2, o3);
		int count = 0;
		if (add(irega, iregb, oregc)) {
			opcode[p][0]++;
			count++;
		}
		if (add(irega, b, oregc)) {
			opcode[p][1]++;
			count++;
		}
		if (mul(irega, iregb, oregc)) {
			opcode[p][2]++;
			count++;
		}
		if (mul(irega, b, oregc)) {
			opcode[p][3]++;
			count++;
		}
		if (ban(irega, iregb, oregc)) {
			opcode[p][4]++;
			count++;
		}
		if (ban(irega, b, oregc)) {
			opcode[p][5]++;
			count++;
		}
		if (bor(irega, iregb, oregc)) {
			opcode[p][6]++;
			count++;
		}
		if (bor(irega, b, oregc)) {
			opcode[p][7]++;
			count++;
		}
		if (set(irega, oregc)) {
			opcode[p][8]++;
			count++;
		}
		if (set(a, oregc)) {
			opcode[p][9]++;
			count++;
		}
		if (gt(a, iregb, oregc)) {
			opcode[p][10]++;
			count++;
		}
		if (gt(irega, b, oregc)) {
			opcode[p][11]++;
			count++;
		}
		if (gt(irega, iregb, oregc)) {
			opcode[p][12]++;
			count++;
		}
		if (eq(a, iregb, oregc)) {
			opcode[p][13]++;
			count++;
		}
		if (eq(irega, b, oregc)) {
			opcode[p][14]++;
			count++;
		}
		if (eq(irega, iregb, oregc)) {
			opcode[p][15]++;
			count++;
		}

		if (count >= 3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean add(int A, int B, int C) {
		if ((A + B) == C) {
			return true;
		}
		return false;
	}

	public static boolean mul(int A, int B, int C) {
		if ((A * B) == C) {
			return true;
		}
		return false;
	}

	public static boolean ban(int A, int B, int C) {
		if ((A & B) == C) {
			return true;
		}
		return false;
	}

	public static boolean bor(int A, int B, int C) {
		if ((A | B) == C) {
			return true;
		}
		return false;
	}

	public static boolean set(int A, int C) {
		if (A == C) {
			return true;
		}
		return false;
	}

	public static boolean gt(int A, int B, int C) {
		if ((A > B && C == 1) || (A <= B && C == 0)) {
			return true;
		}
		return false;
	}

	public static boolean eq(int A, int B, int C) {
		if ((A == B && C == 1) || (A != B && C == 0)) {
			return true;
		}
		return false;
	}

	public static int add2(int A, int B) {
		return (A + B);
	}

	public static int mul2(int A, int B) {
		return (A * B);
	}

	public static int ban2(int A, int B) {
		return (A & B);
	}

	public static int bor2(int A, int B) {
		return (A | B);
	}

	public static int set2(int A) {
		return A;
	}

	public static int gt2(int A, int B) {
		if (A > B) {
			return 1;
		}
		return 0;
	}

	public static int eq2(int A, int B) {
		if (A == B) {
			return 1;
		}
		return 0;
	}

	public static int getRegister(int input, int reg0, int reg1, int reg2, int reg3) {
		if (input == 0) {
			return reg0;
		} else if (input == 1) {
			return reg1;
		} else if (input == 2) {
			return reg2;
		} else {
			return reg3;
		}
	}
}
