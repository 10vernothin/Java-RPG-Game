package monsters;

import interfaces.AttributeType;
import interfaces.RandomModule;
import monsters.MonsterTable.Modifier;
import monsters.MonsterTable.MonsterType;
import player.*;

public class Monster {
	//monsterID (XYZABC)ZZ - ZZ is the Monster Type, XYZABC are the Modifiers
	int monster_ID;
	int monster_level;
	double monster_health;
	double experience_rate;
	PlayerAttributes monster_attributes;
	
	
	public Monster(int level,int ID) {
		this.monster_ID = ID;
		this.monster_level = level;
		this.monster_attributes = new PlayerAttributes();
		MonsterStatBuilder m = new MonsterStatBuilder(this);
		m.buildAttributeBonuses();
		this.monster_health = monster_attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue();
		this.experience_rate = CalculateExperience();
	}
	
	public Monster(int level) {
		int ladder = (level/5);
		int rand_num = RandomModule.RandomBetween(2+ladder, 2+ladder+1);
		int rand_id = (int)RandomModule.RandomInDigits(rand_num);
		this.monster_ID = rand_id;
		this.monster_level = level;
		this.monster_attributes = new PlayerAttributes();
		MonsterStatBuilder m = new MonsterStatBuilder(this);
		m.buildAttributeBonuses();
		this.monster_health = monster_attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue();
		this.experience_rate = CalculateExperience();
	}
	
	private double CalculateExperience() {
		return 0;
	}

	public String getMonsterName() {
		String monstertype = getMonsterType().name;
		String prefix = "";
		String modifier_names = "";
		if (monstertype != MonsterType.CHEST.name || findDigitsofID() != 2) {
			Modifier[] modifiers = getMonsterModifiers();
			int highest_repetition = 0;
			class ModifierCounter {
				int[] repetition = new int[10];
				void Tick(Modifier m) {
					repetition[m.id] += 1;
				}
			}
			ModifierCounter c = new ModifierCounter();
			int[] used = {0};
			if (modifiers != null) {
				for (Modifier m: modifiers) {
					c.Tick(m);
					for (int v = 0; v < used.length; v++) {
						if (m.id == used[v] || v < used.length-1) {
							break;
						} else {
							int[] temp_used = new int[used.length+1];
							for (int w = 0; w < used.length; w++) {
								temp_used[w] = used[w];
							}
							temp_used[used.length] = m.id;
							used = temp_used;
						}
					}
				}
			}		
			int most_relevent_type = -1;
			for (int n = 0; n < c.repetition.length; n++) {
				if (c.repetition[n] > highest_repetition) {
					highest_repetition = c.repetition[n];
					most_relevent_type = n;
				}
			}
				modifier_names = Modifier.getModifierByID(most_relevent_type).name + "";
				switch (highest_repetition) {
				case 0:
					break;
				case 1:
					break;
				case 2:
					prefix = "Uncommonly ";
					break;
				case 3:
					prefix = "Very ";
					break;
				default:
					prefix = "Extremely ";
				}
		} else {
		return monstertype;
	}
	return prefix + modifier_names + monstertype;
}

	private int findDigitsofID() {
		//this finds the numerical order of the ID
		int monster_id = monster_ID;
		int n = 1;
		while (monster_id/10 != 0) {
			n++;
			monster_id = monster_id/10;
		}
		return n;
	}
	
	private int findDigitofID(int n) {
		//this finds the value of the nth digit of the ID or 0 if it is not 
		int highest_digit = findDigitsofID();
		if (n > highest_digit) {
			return 0;
		}
		return (monster_ID/((int)Math.pow(10, n-1)))%10;
	}
	
	public MonsterType getMonsterType() {
		for (MonsterType m: MonsterType.values()){
			if (m.id == (findDigitofID(2)*10 + findDigitofID(1))){
				return m;
			} else if (m.id == (findDigitofID(2)*10)){
				return m;
			}
		}
		return MonsterType.NONE;
	}
	
	public Modifier[] getMonsterModifiers() {
		int num_of_digits = findDigitsofID();
		if ((num_of_digits-2) != 0) {
			Modifier[] descriptors = new Modifier[num_of_digits-2];
			for (int n = 3; n <= num_of_digits; n++) {
				descriptors[n-3] = Modifier.getModifierByID(findDigitofID(n));
			}
			return descriptors;
		} else {
			return null;
		}
	}
	
	
	public int getLevel() {
		return monster_level;
	};
	
	public double getHealth() {
		return monster_health;
	}
	
}
