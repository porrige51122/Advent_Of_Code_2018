import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day4Part2{
	public static void main(String args[]) throws IOException {
		File file = new File("C:\\Users\\Smithers-HP\\Desktop\\input1.txt");
		Scanner sc = new Scanner(file);
		sc.useDelimiter("");
		String[][] data = new String[13][979];
		String[][] dataSorted = new String[2][979];
		for (int i = 0; i < 979; i++) {
			sc.next();
			data[0][i] = sc.next();
			data[1][i] = sc.next();
			data[2][i] = sc.next();
			data[3][i] = sc.next();
			sc.next();
			data[4][i] = sc.next();
			data[5][i] = sc.next();
			sc.next();
			data[6][i] = sc.next();
			data[7][i] = sc.next();
			sc.next();
			data[8][i] = sc.next();
			data[9][i] = sc.next();
			sc.next();
			data[10][i] = sc.next();
			data[11][i] = sc.next();
			sc.next();
			data[12][i] = sc.nextLine();
		}
		for (int i = 0; i < 979; i++) {
			String temp = "";
			for (int j = 4; j < 12; j++) {
				temp += data[j][i];
			}
			dataSorted[0][i] = temp;
			dataSorted[1][i] = data[12][i];

		}
		String tmp1;
		String tmp2;
		boolean sorted = false;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < 979 - 1; i++) {
				if (Integer.parseInt(dataSorted[0][i]) > Integer.parseInt(dataSorted[0][i + 1])) {
					tmp1 = dataSorted[0][i];
					tmp2 = dataSorted[1][i];
					dataSorted[0][i] = dataSorted[0][i + 1];
					dataSorted[1][i] = dataSorted[1][i + 1];
					dataSorted[0][i + 1] = tmp1;
					dataSorted[1][i + 1] = tmp2;
					sorted = false;
				}
			}
		}
		//data sorted. now we start
		/*
		 * sort guards into an array
		 * one by one on the array do all the things
		 * find their own personal largest overlap
		 * find the largest out of them
		 */
		
		String[] guardNo = new String[300];
		int counter = 0;
		for (int i = 0; i < 979; i++) {
			if (dataSorted[1][i].equals(" falls asleep")) {
			} else if (dataSorted[1][i].equals(" wakes up")) {
			} else {
				guardNo[counter] = dataSorted[1][i];
				counter++;
			}
		}
		int largestOverlap = 0;
		String[] guardLargestOverlap = new String[300];
		for (int p = 0; p < 237; p++) {
			int[] overlap = new int[60];
			boolean checker = false;
			int[] guardData = new int[900];
			counter = 0;
			for (int k = 0; k < 979; k++) {
				if (dataSorted[1][k].equals(" falls asleep")) {
					if (checker) {
						guardData[counter] = Integer.parseInt(dataSorted[0][k]);
						counter++;
					}
				} else if (dataSorted[1][k].equals(" wakes up")) {
					if (checker) {
						guardData[counter] = Integer.parseInt(dataSorted[0][k]);
						counter++;
					}
				} else {
					if (guardNo[p].equals(dataSorted[1][k])) {
						checker = true;
					} else {
						checker = false;
					}
				}
			}
			for (int i = 0; i < counter; i++) {
				guardData[i] = guardData[i] % 100;
			}
			
			for (int i = 0; i < guardData.length; i+=2) {
				for (int j = guardData[i]; j < guardData[i+1]; j++) {
					overlap[j]++;
				}
			}
			for (int i = 0; i < 60; i++) {
				if (overlap[i] > largestOverlap) {
					largestOverlap = overlap[i];
					System.out.println(largestOverlap + " "+ i + " " + guardNo[p]);
				}
			}
		}
/*		*/
	}
}
