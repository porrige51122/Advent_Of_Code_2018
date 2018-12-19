import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Day19BothParts {
	public static String path = "C:\\Users\\aidan\\Desktop\\";

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Example = " + Example());
		System.out.println("Part1 = " + Part1());
		System.out.println("Part2 = " + Part2());
	}

	public static int Example() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "Example.txt"));
		ArrayList<String> data = new ArrayList<>();
		int[] register = new int[6];
		sc.next();
		int ip = sc.nextInt();
		sc.nextLine();
		while (sc.hasNextLine()) {
			data.add(sc.nextLine());
		}
		while (register[ip] < data.size()) {
			String[] tempdata = data.get(register[ip]).split(" ");
			test2(register, tempdata[0], Integer.parseInt(tempdata[1]), Integer.parseInt(tempdata[2]),
					Integer.parseInt(tempdata[3]));
			register[ip]++;
		}
		register[ip]--;
		return register[0];
	}

	public static int Part1() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "Input.txt"));
		ArrayList<String> data = new ArrayList<>();
		int[] register = new int[6];
		sc.next();
		int ip = sc.nextInt();
		sc.nextLine();
		while (sc.hasNextLine()) {
			data.add(sc.nextLine());
		}
		while (register[ip] < data.size()) {
			String[] tempdata = data.get(register[ip]).split(" ");
			test2(register, tempdata[0], Integer.parseInt(tempdata[1]), Integer.parseInt(tempdata[2]),
					Integer.parseInt(tempdata[3]));
			register[ip]++;
		}
		return register[0];
	}

	public static int Part2() throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + "Input.txt"));
		ArrayList<String> data = new ArrayList<>();
		sc.next();
		sc.nextLine();
		while (sc.hasNextLine()) {
			data.add(sc.nextLine());
		}
		int a = Integer.parseInt(data.get(21).split(" ")[2]);
		int b = Integer.parseInt(data.get(23).split(" ")[2]);
		int c = 836 + a * 22 + b + 10550400;
		int output = 0;
		for (int i = 1; i <= c; i++) {
			if (c % i == 0) {
				output += i;
			}
		}
		return output;
	}

	public static int[] test2(int[] register, String p, int a, int b, int c) {
		switch (p) {
		case "mulr":
			register[c] = register[a] * register[b];
			break;
		case "eqri":
			register[c] = eq2(register[a], b);
			break;
		case "setr":
			register[c] = register[a];
			break;
		case "eqrr":
			register[c] = eq2(register[a], register[b]);
			break;
		case "gtrr":
			register[c] = gt2(register[a], register[b]);
			break;
		case "muli":
			register[c] = register[a] * b;
			break;
		case "borr":
			register[c] = register[a] | register[b];
			break;
		case "bani":
			register[c] = register[a] & b;
			break;
		case "addr":
			register[c] = register[a] + register[b];
			break;
		case "banr":
			register[c] = register[a] & register[b];
			break;
		case "eqir":
			register[c] = eq2(a, register[b]);
			break;
		case "gtir":
			register[c] = gt2(a, register[b]);
			break;
		case "addi":
			register[c] = register[a] + b;
			break;
		case "gtri":
			register[c] = gt2(register[a], b);
			break;
		case "seti":
			register[c] = a;
			break;
		case "bori":
			register[c] = register[a] | b;
			break;
		default:
			System.out.println("ERROR with value = " + p);
			break;
		}
		return register;
	}

	public static int gt2(int A, int B) {
		return A > B ? 1 : 0;
	}

	public static int eq2(int A, int B) {
		return A == B ? 1 : 0;
	}
}
