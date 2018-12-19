import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

// With help from Reddit user /u/Philboyd_Studge
public class Day9 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("No of players: ");
		int players = sc.nextInt();
		System.out.print("No of marbles: ");
		int marbles = sc.nextInt();
		System.out.println("Part 1 = " + Part1(players, marbles));
		System.out.println("Part 2 = " + Part1(players, (marbles * 100)));
		sc.close();
	}

	private static long Part1(int players, int marbles) {
		ArrayDeque<Integer> circle = new ArrayDeque<>();
		circle.addFirst(0);
		long[] elves = new long[players];
		for (int i = 1; i <= marbles; i++) {
			if (i % 23 == 0) {
				for (int j = 0; j < Math.abs(-7) - 1; j++) {
					int t = circle.remove();
					circle.addLast(t);
				}
				elves[i % players] += i + circle.pop();

			} else {
				for (int j = 0; j < 2; j++) {
					int t = circle.removeLast();
					circle.addFirst(t);
				}
				circle.addLast(i);
			}
		}
		return Arrays.stream(elves).max().getAsLong();
	}

}
