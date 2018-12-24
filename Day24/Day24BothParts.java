import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day24BothParts {
	private static class army {
		int size;
		int hitpoints;
		ArrayList<String> weakness;
		ArrayList<String> immune;
		int damage;
		String damageType;
		int initiative;
		Boolean attacked = false;

		public Boolean getAttacked() {
			return attacked;
		}

		public void setAttacked(Boolean attacked) {
			this.attacked = attacked;
		}

		public army(int size, int hitpoints, ArrayList<String> weakness, ArrayList<String> immune, int damage,
				String damageType, int initiative) {
			this.size = size;
			this.hitpoints = hitpoints;
			this.weakness = weakness;
			this.immune = immune;
			this.damage = damage;
			this.damageType = damageType;
			this.initiative = initiative;
		}

		public ArrayList<String> getImmune() {
			return immune;
		}

		public int getSize() {
			return size;
		}

		public void setSize(int size) {
			this.size = size;
		}

		public int getHitpoints() {
			return hitpoints;
		}

		public int getEffectivePower() {
			return size * damage;
		}

		public ArrayList<String> getWeakness() {
			return weakness;
		}

		public String getDamageType() {
			return damageType;
		}

		public int getInitiative() {
			return initiative;
		}
	}

	private static final String path = "C:\\Users\\aidan\\Desktop\\";
	private static ArrayList<army> immuneSystem = new ArrayList<>();
	private static ArrayList<army> infection = new ArrayList<>();

	public static void main(String args[]) throws FileNotFoundException {
		System.out.println("Part 1 = " + part1());
		System.out.println("Part 2 = " + part2());
	}
	
	private static int part2() throws FileNotFoundException {
		for (int boost = 30; boost < Integer.MAX_VALUE; boost++) {
			clearArray();
			parse("input.txt", boost);
			while (immuneSystem.size() != 0 && infection.size() != 0) {
				int[] order1 = targetOrder();
				int[] attackingWho = new int[immuneSystem.size() + infection.size()];
				for (int i = 0; i < attackingWho.length; i++) {
					attackingWho[order1[i]] = targeting(order1[i]);
				}
				int[] order2 = initiativeOrder();
				for (int i = 0; i < attackingWho.length; i++) {
					if (attackingWho[order2[i]] == 200) {
						continue;
					}
					attack(order2[i], attackingWho[order2[i]]);
				}
				cleanUpDead();
				resetAttack();
			}
			int output = 0;
			if (immuneSystem.size() == 0) {
				for (int i = 0; i < infection.size(); i++) {
					output += infection.get(i).getSize();
				}
			} else {
				for (int i = 0; i < immuneSystem.size(); i++) {
					output += immuneSystem.get(i).getSize();
				}
				return output;
			}
			System.out.println(output);
		}
		return 0;
	}
	
	private static int part1() throws FileNotFoundException {
		parse("input.txt", 0);
		while (immuneSystem.size() != 0 && infection.size() != 0) {
			int[] order1 = targetOrder();
			int[] attackingWho = new int[immuneSystem.size() + infection.size()];
			for (int i = 0; i < attackingWho.length; i++) {
				attackingWho[order1[i]] = targeting(order1[i]);
			}
			int[] order2 = initiativeOrder();
			for (int i = 0; i < attackingWho.length; i++) {
				if (attackingWho[order2[i]] == 200) {
					continue;
				}
				attack(order2[i], attackingWho[order2[i]]);
			}
			cleanUpDead();
			resetAttack();
		}
		int output = 0;
		if (immuneSystem.size() == 0) {
			for (int i = 0; i < infection.size(); i++) {
				output += infection.get(i).getSize();
			}
		} else {
			for (int i = 0; i < immuneSystem.size(); i++) {
				output += immuneSystem.get(i).getSize();
			}
		}
		return output;
	}
	
	private static void clearArray() {
		for (int i = 0; i < immuneSystem.size(); i++) {
			immuneSystem.remove(i);
			i--;
		}
		for (int i = 0; i < infection.size(); i++) {
			infection.remove(i);
			i--;
		}

	}

	private static void resetAttack() {
		for (int i = 0; i < immuneSystem.size(); i++) {
			if (immuneSystem.get(i).getAttacked()) {
				immuneSystem.get(i).setAttacked(false);
			}
		}
		for (int i = 0; i < infection.size(); i++) {
			if (infection.get(i).getAttacked()) {
				infection.get(i).setAttacked(false);
			}
		}
		
	}

	private static void cleanUpDead() {
		for (int i = 0; i < immuneSystem.size(); i++) {
			if (immuneSystem.get(i).getSize() <= 0) {
				immuneSystem.remove(i);
				i--;
			}
		}
		for (int i = 0; i < infection.size(); i++) {
			if (infection.get(i).getSize() <= 0) {
				infection.remove(i);
				i--;
			}
		}

	}

	private static void attack(int attacker, int defender) {
		if (attacker < immuneSystem.size()) {
			if (immuneSystem.get(attacker).getSize() <= 0) {
				return;
			} else {
				int attackPower = immuneSystem.get(attacker).getEffectivePower();
				String damagetype = immuneSystem.get(attacker).getDamageType();
				for (int j = 0; j < infection.get(defender - immuneSystem.size()).getWeakness().size(); j++) {
					if (damagetype.equals(infection.get(defender - immuneSystem.size()).getWeakness().get(j))) {
						attackPower *= 2;
						break;
					}
				}
				for (int j = 0; j < infection.get(defender - immuneSystem.size()).getImmune().size(); j++) {
					if (damagetype.equals(infection.get(defender - immuneSystem.size()).getImmune().get(j))) {
						attackPower = 0;
						break;
					}
				}
				int killed = attackPower / infection.get(defender - immuneSystem.size()).getHitpoints();
				infection.get(defender - immuneSystem.size())
						.setSize(infection.get(defender - immuneSystem.size()).getSize() - killed);
			}
		} else {
			if (infection.get(attacker - immuneSystem.size()).getSize() <= 0) {
				return;
			} else {
				int attackPower = infection.get(attacker - immuneSystem.size()).getEffectivePower();
				String damagetype = infection.get(attacker - immuneSystem.size()).getDamageType();
				for (int j = 0; j < immuneSystem.get(defender).getWeakness().size(); j++) {
					if (damagetype.equals(immuneSystem.get(defender).getWeakness().get(j))) {
						attackPower *= 2;
						break;
					}
				}
				for (int j = 0; j < immuneSystem.get(defender).getImmune().size(); j++) {
					if (damagetype.equals(immuneSystem.get(defender).getImmune().get(j))) {
						attackPower = 0;
						break;
					}
				}
				int killed = attackPower / immuneSystem.get(defender).getHitpoints();
				immuneSystem.get(defender).setSize(immuneSystem.get(defender).getSize() - killed);
			}
		}
	}

	private static int[] initiativeOrder() {
		int[] originalOrder = new int[immuneSystem.size() + infection.size()];
		for (int i = 0; i < immuneSystem.size(); i++) {
			originalOrder[i] = immuneSystem.get(i).getInitiative();
		}
		for (int i = immuneSystem.size(); i < originalOrder.length; i++) {
			originalOrder[i] = infection.get(i - immuneSystem.size()).getInitiative();
		}
		int[] newOrder = new int[immuneSystem.size() + infection.size()];
		for (int i = 0; i < newOrder.length; i++) {
			newOrder[i] = i;
		}
		int temp;
		int temp2;
		for (int i = 1; i < originalOrder.length; i++) {
			for (int j = i; j > 0; j--) {
				if (originalOrder[j] > originalOrder[j - 1]) {
					temp = originalOrder[j];
					originalOrder[j] = originalOrder[j - 1];
					originalOrder[j - 1] = temp;
					temp2 = newOrder[j];
					newOrder[j] = newOrder[j - 1];
					newOrder[j - 1] = temp2;
				}
			}
		}
		return newOrder;
	}

	private static int targeting(int army) {
		Boolean immune;
		Boolean weak;
		if (army < immuneSystem.size()) {
			String damagetype = immuneSystem.get(army).getDamageType();
			int[] damage = new int[infection.size()];
			for (int i = 0; i < infection.size(); i++) {
				immune = false;
				weak = false;
				for (int j = 0; j < infection.get(i).getWeakness().size(); j++) {
					if (damagetype.equals(infection.get(i).getWeakness().get(j))) {
						weak = true;
					}
				}
				for (int j = 0; j < infection.get(i).getImmune().size(); j++) {
					if (damagetype.equals(infection.get(i).getImmune().get(j))) {
						immune = true;
					}
				}
				if (immune) {
					damage[i] = 0;
				} else if (infection.get(i).getAttacked()) {
					damage[i] = 0;
				} else if (weak) {
					damage[i] = immuneSystem.get(army).getEffectivePower() * 2;
				} else {
					damage[i] = immuneSystem.get(army).getEffectivePower();
				}
			}
			int mostDamage = 0;
			int attackingWho = 200;
			for (int i = 0; i < damage.length; i++) {
				if (damage[i] == 0)
					continue;
				if (damage[i] > mostDamage || (damage[i] == mostDamage
						&& infection.get(i).getEffectivePower() > infection.get(attackingWho).getEffectivePower())
						|| (damage[i] == mostDamage
								&& infection.get(i).getEffectivePower() == infection.get(attackingWho)
										.getEffectivePower()
								&& infection.get(i).getInitiative() > infection.get(attackingWho).getInitiative())) {
					mostDamage = damage[i];
					
					attackingWho = i;
				}
			}
			if (attackingWho != 200) {
				infection.get(attackingWho).setAttacked(true);
			} else {
				return attackingWho;
			}
			return attackingWho + immuneSystem.size();
		} else {
			String damagetype = infection.get(army - immuneSystem.size()).getDamageType();
			int[] damage = new int[immuneSystem.size()];
			for (int i = 0; i < immuneSystem.size(); i++) {
				immune = false;
				weak = false;
				for (int j = 0; j < immuneSystem.get(i).getWeakness().size(); j++) {
					if (damagetype.equals(immuneSystem.get(i).getWeakness().get(j))) {
						weak = true;
					}
				}
				for (int j = 0; j < immuneSystem.get(i).getImmune().size(); j++) {
					if (damagetype.equals(immuneSystem.get(i).getImmune().get(j))) {
						immune = true;
					}
				}
				if (immune) {
					damage[i] = 0;
				} else if (immuneSystem.get(i).getAttacked()) {
					damage[i] = 0;
				} else if (weak) {
					damage[i] = infection.get(army - immuneSystem.size()).getEffectivePower() * 2;
				} else {
					damage[i] = infection.get(army - immuneSystem.size()).getEffectivePower();
				}
			}
			int mostDamage = 0;
			int attackingWho = 200;
			for (int i = 0; i < damage.length; i++) {
				if (damage[i] == 0)
					continue;
				if (damage[i] > mostDamage || (damage[i] == mostDamage
						&& immuneSystem.get(i).getEffectivePower() > immuneSystem.get(attackingWho).getEffectivePower())
						|| (damage[i] == mostDamage
								&& immuneSystem.get(i).getEffectivePower() == immuneSystem.get(attackingWho)
										.getEffectivePower()
								&& immuneSystem.get(i).getInitiative() > immuneSystem.get(attackingWho)
										.getInitiative())) {
					mostDamage = damage[i];
					attackingWho = i;
				}
			}
			if (attackingWho != 200) {
				immuneSystem.get(attackingWho).setAttacked(true);
			}
			return attackingWho;
		}
	}

	private static int[] targetOrder() {
		int[][] originalOrder = new int[immuneSystem.size() + infection.size()][2];
		for (int i = 0; i < immuneSystem.size(); i++) {
			originalOrder[i][0] = immuneSystem.get(i).getEffectivePower();
			originalOrder[i][1] = immuneSystem.get(i).getInitiative();
		}
		for (int i = immuneSystem.size(); i < originalOrder.length; i++) {
			originalOrder[i][0] = infection.get(i - immuneSystem.size()).getEffectivePower();
			originalOrder[i][1] = infection.get(i - immuneSystem.size()).getInitiative();
		}
		int[] newOrder = new int[immuneSystem.size() + infection.size()];
		for (int i = 0; i < newOrder.length; i++) {
			newOrder[i] = i;
		}
		int[] temp;
		int temp2;
		for (int i = 1; i < originalOrder.length; i++) {
			for (int j = i; j > 0; j--) {
				if (originalOrder[j][0] > originalOrder[j - 1][0] || (originalOrder[j][0] == originalOrder[j - 1][0]
						&& originalOrder[j][1] > originalOrder[j - 1][1])) {
					temp = originalOrder[j];
					originalOrder[j] = originalOrder[j - 1];
					originalOrder[j - 1] = temp;
					temp2 = newOrder[j];
					newOrder[j] = newOrder[j - 1];
					newOrder[j - 1] = temp2;
				}
			}
		}
		return newOrder;
	}

	private static void parse(String input, int boost) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(path + input));
		Boolean whichArmy = false;
		while (sc.hasNextLine()) {
			String[] l = sc.nextLine().split(" ");
			if (l[0].equals("Immune") || l[0].equals("Infection:") || l[0].equals("")) {
				if (l[0].equals("Infection:")) {
					whichArmy = true;
				}
			} else {
				int size = Integer.parseInt(l[0]);
				int hp = Integer.parseInt(l[4]);
				ArrayList<String> weak = new ArrayList<>();
				ArrayList<String> immune = new ArrayList<>();
				int damage = 0;
				String damageType = "";
				int initiative = 0;
				for (int i = 5; i < l.length; i++) {
					if (l[i].equals("weak") || l[i].equals("(weak")) {
						weak.add(l[i + 2].substring(0, l[i + 2].length() - 1));
						if (l[i + 2].endsWith(",")) {
							weak.add(l[i + 3].substring(0, l[i + 3].length() - 1));
						}
					}
					if (l[i].equals("immune") || l[i].equals("(immune")) {
						immune.add(l[i + 2].substring(0, l[i + 2].length() - 1));
						if (l[i + 2].endsWith(",")) {
							immune.add(l[i + 3].substring(0, l[i + 3].length() - 1));
						}
						if (l[i + 3].endsWith(",")) {
							immune.add(l[i + 4].substring(0, l[i + 4].length() - 1));
						}
					}
					if (l[i].equals("with")) {
						damage = Integer.parseInt(l[i + 5]);
						damageType = l[i + 6];
						initiative = Integer.parseInt(l[i + 10]);
						break;
					}
				}
				if (!whichArmy) {
					immuneSystem.add(new army(size, hp, weak, immune, boost+ damage, damageType, initiative));
				} else {
					infection.add(new army(size, hp, weak, immune, damage, damageType, initiative));
				}
			}

		}
		sc.close();
	}
}
