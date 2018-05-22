package items;

import interfaces.*;

public class EquipmentRandomizer {

	protected int ran_num;
	protected Multiplier[] multiplier;
	protected AttributeBonusGenerator range_table;
	

	public EquipmentRandomizer() {
		Multiplier common_m = new Multiplier(ItemRarity.COMMON, 0.5, 1.0);
		Multiplier rare_m = new Multiplier(ItemRarity.RARE, 1.1, 2.5);
		Multiplier legendary_m = new Multiplier(ItemRarity.COMMON, 2.5, 8.0);
		Multiplier[] multiplier = {common_m, rare_m, legendary_m};
		this.multiplier = multiplier;
		range_table = new AttributeBonusGenerator();

	}


	protected class Multiplier {
		protected ItemRarity rarity;
		protected double max;
		protected double min;
		protected Multiplier(ItemRarity t, double min, double max) {
			this.rarity = t;
			this.min = min;
			this.max = max;
		}
	}

	//makeRandomEquipment creates a random equipment, with a random rarity, random type, and randomized equipment range according to a level field
	public Equipment makeRandomEquipment(int level) {

		int ran_num = RandomModule.RandomBetween(110, 189);

		int max_bonuses;
		if ((ran_num%100)%10 == ItemRarity.LEGENDARY.id){
			int ran_num2 = RandomModule.RandomBetween(0, 1000);
			if (ran_num2 > 900) {
				max_bonuses = 7;
			} else {
				return makeRandomEquipment();
			}
		} else if ((ran_num%100)%10 >= ItemRarity.RARE.id) {
			max_bonuses = 4;
		} else {
			max_bonuses = 1;
		}

		Equipment equip = new Equipment(ran_num);


		double base_def = RandomModule.RandomBetweenGaussian(1, 70);
		double max, min;
		if ((ran_num%100)%10 == ItemRarity.LEGENDARY.id){
			max = multiplier[2].max;
			min = multiplier[2].min;
		} else if ((ran_num%100)%10 >= ItemRarity.RARE.id) {
			max = multiplier[1].max;
			min = multiplier[1].min;
		} else {
			max = multiplier[0].max;
			min = multiplier[0].min;
		}
		
		int isMade = 0;
		do {
			AttributeBonus new_bonus = range_table.generateRandomAttributeBonus(level);
			if (new_bonus != null) {
				new_bonus.changeBonusValueBy(RandomModule.RandomBetweenGaussian(min, max), true);
			}
				equip.addAttributeBonus(new_bonus);
				isMade++;
		} while (isMade < max_bonuses);
		
		double base_defense_stat = RandomModule.RandomBetweenGaussian(base_def*min, base_def*max);
		AttributeBonus new_bonus = new AttributeBonus(AttributeType.DEFENCE, base_defense_stat, false);
		equip.addAttributeBonus(new_bonus);
		return equip;
	}
	
	//this makes a random equipment with a random level field between 0-50 (should not be used lightly)
	public Equipment makeRandomEquipment() {
		int l = RandomModule.RandomBetween(0, 50);
		return makeRandomEquipment(l);
	}
	
	//this makes a random equipment by rarity 
	public Equipment makeRandomEquipmentByRarity(ItemRarity i, int level) {
		Equipment equip = makeRandomEquipment(level);
		if (equip.getItemRarity() != i){
			return makeRandomEquipmentByRarity(i, level);
		} else {
			return equip;
		}
	}

	//this makes a random equipment by rarity (Overloaded for random)
		public Equipment makeRandomEquipmentByRarity(ItemRarity i) {
			Equipment equip = makeRandomEquipment();
			if (equip.getItemRarity() != i){
				return makeRandomEquipmentByRarity(i);
			} else {
				return equip;
			}
		}
	//this makes a random equipment by type
	public Equipment makeRandomEquipByType(int EQUIPTYPE, int level) {
		Equipment equip = makeRandomEquipment();
		if ((equip.itemID/10)%10 != EQUIPTYPE){
			return makeRandomEquipByType(EQUIPTYPE, level);
		} else {
			return equip;
		}
	}
	

	//this makes a random equipment by type (Overloaded for random)
	public Equipment makeRandomEquipByType(int EQUIPTYPE) {
		Equipment equip = makeRandomEquipment();
		if ((equip.itemID/10)%10 != EQUIPTYPE){
			return makeRandomEquipByType(EQUIPTYPE);
		} else {
			return equip;
		}
	}
}


