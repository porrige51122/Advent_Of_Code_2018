import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Day21BothParts {
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String[] args) throws FileNotFoundException {
		PrintStream out = new PrintStream(new FileOutputStream(path +"output.txt"));
		System.setOut(out);
		System.out.println("Part1 = " + Part1());
	}

	public static int Part1() throws FileNotFoundException {
		for (int i = 0; i < 1; i++) {
			Scanner sc = new Scanner(new File(path + "Input.txt"));
			ArrayList<String> data = new ArrayList<>();
			long[] register = new long[6];
			register[0] = 0;
			sc.next();
			int ip = sc.nextInt();
			sc.nextLine();
			while (sc.hasNextLine()) {
				data.add(sc.nextLine());
			}

			int count = 0;
			while (register[ip] < data.size()) {
				count++;
				String[] tempdata = data.get((int) register[ip]).split(" ");
				test2(register, tempdata[0], Long.parseLong(tempdata[1]), Long.parseLong(tempdata[2]),
						Long.parseLong(tempdata[3]));
				register[ip]++;
				if (register[ip] == 28) {
					System.out.println(register[0] + " " + register[1] + " " + register[2] + " " + register[3] + " "
							+ register[4] + " " + register[5]);
				}
			}
			System.out.println(count + " " + 0);
		}
		return 5;
	}

	public static long[] test2(long[] register, String p, long l, long m, long n) {
		switch (p) {
		case "mulr":
			register[(int) n] = register[(int) l] * register[(int) m];
			break;
		case "eqri":
			register[(int) n] = eq2(register[(int) l], m);
			break;
		case "setr":
			register[(int) n] = register[(int) l];
			break;
		case "eqrr":
			register[(int) n] = eq2(register[(int) l], register[(int) m]);
			break;
		case "gtrr":
			register[(int) n] = gt2(register[(int) l], register[(int) m]);
			break;
		case "muli":
			register[(int) n] = register[(int) l] * m;
			break;
		case "borr":
			register[(int) n] = register[(int) l] | register[(int) m];
			break;
		case "bani":
			register[(int) n] = register[(int) l] & m;
			break;
		case "addr":
			register[(int) n] = register[(int) l] + register[(int) m];
			break;
		case "banr":
			register[(int) n] = register[(int) l] & register[(int) m];
			break;
		case "eqir":
			register[(int) n] = eq2(l, register[(int) m]);
			break;
		case "gtir":
			register[(int) n] = gt2(l, register[(int) m]);
			break;
		case "addi":
			register[(int) n] = register[(int) l] + m;
			break;
		case "gtri":
			register[(int) n] = gt2(register[(int) l], m);
			break;
		case "seti":
			register[(int) n] = l;
			break;
		case "bori":
			register[(int) n] = register[(int) l] | m;
			break;
		default:
			System.out.println("ERROR with value = " + p);
			break;
		}
		return register;
	}

	public static long gt2(long A, long B) {
		return A > B ? 1 : 0;
	}

	public static long eq2(long A, long B) {
		return A == B ? 1 : 0;
	}
}
