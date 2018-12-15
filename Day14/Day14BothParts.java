import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Day14BothParts {
	public static void main(String args[]) {
		int input = 637061;
		System.out.println("Part1 = " + Part1(input));
		System.out.println("Part2 = " + Part2(input));
	}

	public static String Part1(int input) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		data.add(3);
		data.add(7);
		int elf1 = 0;
		int elf2 = 1;
		for (int l = 0; l < input + 15; l++) {
			int total = data.get(elf1) + data.get(elf2);
			if (total >= 10) {
				data.add(total / 10);
				data.add(total % 10);
			} else {
				data.add(total);
			}
			elf1 = elf1 + 1 + data.get(elf1);
			if (elf1 >= data.size()) {
				elf1 = elf1 % data.size();
			}

			elf2 = elf2 + 1 + data.get(elf2);
			if (elf2 >= data.size()) {
				elf2 = elf2 % data.size();
			}
		}
		int fsize = input;
		return "" + data.get(fsize) + data.get(fsize + 1) + data.get(fsize + 2) + data.get(fsize + 3)
				+ data.get(fsize + 4) + data.get(fsize + 5) + data.get(fsize + 6) + data.get(fsize + 7)
				+ data.get(fsize + 8) + data.get(fsize + 9);
	}

	public static int Part2(int input) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		int first =(input / 100000);
		int second =((input / 10000) - (first * 10));
		int third =((input / 1000) - (second * 10) - (first *100));
		int fourth =((input / 100) - (third * 10) - (second * 100) - (first *1000));
		int fifth =((input / 10) - (fourth * 10) - (third * 100) - (second * 1000) - (first *10000));
		int sixth =(input - (fifth * 10) -(fourth * 100) - (third * 1000) - (second * 10000) - (first *100000));
		data.add(3);
		data.add(7);
		int elf1 = 0;
		int elf2 = 1;
		for (int l = 0; l < 60000000; l++) {
			int total = data.get(elf1) + data.get(elf2);
			if (total < 10) {
				data.add(total);
			} else {
				data.add(1);
				data.add((total % 10));
			}
			elf1 += 1 + data.get(elf1);
			if (elf1 >= data.size()) {
				elf1 = elf1 % data.size();
			}

			elf2 += 1 + data.get(elf2);
			if (elf2 >= data.size()) {
				elf2 = elf2 % data.size();
			}
			
			if (data.get(l) == first) {
				if (data.get(l+1) == second) {
					if (data.get(l+2) == third) {
						if (data.get(l+3) == fourth) {
							if (data.get(l+4) == fifth) {
								if (data.get(l+5) == sixth) {
									return l;
								}
							}
						}
					}
				}
			}
		}
		return 0;

	}
}
