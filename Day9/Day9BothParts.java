import java.util.Scanner;

public class Day9BothParts {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("No of players: ");
		int players = sc.nextInt();
		System.out.print("No of marbles: ");
		int marbles = sc.nextInt();
		System.out.println("Part 1 = " + Part1(players, marbles));
		System.out.println("Part 2 = " + Part1(players, (marbles*100)));
		sc.close();
	}

	private static int Part1(int players, int marbles) {
		int output = 0;
		int[] player = new int[players];
		int[] data1 = new int[2];
		data1[0] = 0;
		data1[1] = 1;
		int currentMarble = 2;
		int currentPlayer = 2;
		for (int i = 2; i <= marbles; i++) {
			if (i % 10000 == 0) {
				System.out.println(i);
			}
			if (i % 23 == 0) {
				int[] data2 = new int[data1.length - 1];
				data2[0] = 0;
				if (data1[data1.length - 1] == i - 1) {
					for (int j = 1; j < data1.length - 8; j++) {
						data2[j] = data1[j];
					}
					for (int j = data1.length - 8; j < data2.length; j++) {
						data2[j] = data1[j + 1];
					}
					currentMarble = data1[data1.length - 7] + 1;
					player[currentPlayer-1] += data1[data1.length - 8] + i;
				} else {
					int jtemp = -7;
					for (int j = 1; j < data2.length; j++) {
						jtemp = j - 7;
						data2[j] = data1[j];
						if (data1[j] == i - 1) {
							break;
						}
					}
					if (jtemp <= 0) {
						currentMarble = data1[data1.length+jtemp+1] + 1;
						player[currentPlayer-1] += data1[data1.length + jtemp] + i;
						for (int j = 1; j < data2.length; j++) {
							if (j == data1.length+jtemp) {
								break;
							}
							data2[j] = data1[j];
						}
						for (int j = data2.length+jtemp; j < data2.length;j++) {
							data2[j] = data1[j+1];
						}
					} else {
						currentMarble = data1[jtemp + 1] + 1;
						player[currentPlayer-1] += data1[jtemp] + i;
						for (int j = jtemp; j < data2.length; j++) {
							data2[j] = data1[j + 1];
						}
					}

				}
				data1 = data2;
			} else {
				int[] data2 = new int[data1.length + 1];
				data2[0] = 0;
				if (data1[data1.length - 1] == currentMarble - 1) {
					data2[1] = i;
					for (int j = 2; j < data2.length; j++) {
						data2[j] = data1[j - 1];
					}
				} else {
					int jtemp = 2;
					for (int j = 1; j < data1.length; j++) {
						jtemp = j;
						data2[j] = data1[j];
						if (data1[j - 1] == currentMarble - 1) {
							data2[j + 1] = i;
							break;
						}
					}
					for (int j = jtemp + 2; j < data2.length; j++) {
						data2[j] = data1[j - 1];
					}
				}
				currentMarble = i + 1;
				data1 = data2;
			}
/*
			System.out.print("[" + currentPlayer + "] ");
			for (int j = 0; j < data1.length; j++) {
				System.out.print(data1[j] + " ");
			}
			System.out.println();*/
			currentPlayer = ((i % players) + 1);

		}
		for (int i = 0; i < player.length; i++) {
			if (output < player[i]) {
				output = player[i];
			}
		}
		return output;
	}
	
}
