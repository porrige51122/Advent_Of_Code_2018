import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day08 {

	public static int counter = 0;
	public static String path = "C:\\Users\\Smithers-HP\\Documents\\AdventOfCode\\Day8\\Input.txt";

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1 = " + Part1());
		System.out.println("Part 2 = " + Part2());
	}

	public static int Part1() throws FileNotFoundException {
		int output = 0;
		Scanner sc = new Scanner(new File(path));
		counter = 0;
		while (sc.hasNextInt()) {
			sc.nextInt();
			counter++;
		}
		sc.close();
		int[] data = new int[counter];
		counter = 0;
		Scanner sc2 = new Scanner(new File(path));
		while (sc2.hasNextInt()) {
			data[counter] = sc2.nextInt();
			counter++;
		}
		sc2.close();
		counter = 0;
		output = ChildrenPart1(data);
		return output;
	}

	public static int Part2() throws FileNotFoundException {
		int output = 0;
		Scanner sc = new Scanner(new File(path));
		counter = 0;
		while (sc.hasNextInt()) {
			sc.nextInt();
			counter++;
		}
		sc.close();
		int[] data = new int[counter];
		counter = 0;
		Scanner sc2 = new Scanner(new File(path));
		while (sc2.hasNextInt()) {
			data[counter] = sc2.nextInt();
			counter++;
		}
		sc2.close();
		counter = 0;
		output = ChildrenPart2(data);
		return output;
	}

	public static int ChildrenPart1(int[] data) {
		int output = 0;
		int noOfMetadata;
		int noOfChildren;
		noOfChildren = data[counter];
		counter++;
		noOfMetadata = data[counter];
		counter++;
		for (int i = noOfChildren; i > 0; i--)
			output += ChildrenPart1(data);
		for (int i = noOfMetadata; i > 0; i--)
			output += data[counter];
		counter++;
		return output;
	}

	public static int ChildrenPart2(int[] data) {
		int output = 0;
		int noOfMetadata;
		int noOfChildren;
		noOfChildren = data[counter];
		counter++;
		noOfMetadata = data[counter];
		counter++;
		int[] children = new int[noOfChildren];
		for (int i = noOfChildren; i > 0; i--)
			children[noOfChildren - i] = ChildrenPart2(data);
		if (noOfChildren == 0)
			for (int i = noOfMetadata; i > 0; i--) {
				output += data[counter];
				counter++;
			}
		else
			for (int i = noOfMetadata; i > 0; i--)
				if (data[counter] > noOfChildren)
					counter++;
				else {
					output += children[data[counter] - 1];
					counter++;
				}
		return output;
	}

}
