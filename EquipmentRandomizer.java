package items;

import interfaces.*;

public class EquipmentRandomizer {
	
	public int ran_num;
	public EquipBonusRange[] range_table;
	public Multiplier[] multiplier;
	
	public EquipmentRandomizer() {
		EquipBonusRange m_hp = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange m_hpX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange m_mp = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange m_mpX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange str = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange strX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange dex = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange dexX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange wis = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange wisX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange luk = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange lukX = new EquipBonusRange(AttributeType.M_HP, 50, 200, true);
		EquipBonusRange dod = new EquipBonusRange(AttributeType.M_HP, 50, 200, false);
		EquipBonusRange crit = new EquipBonusRange(AttributeType.CRIT, 0.01, 0.1, false);
		EquipBonusRange chr = new EquipBonusRange(AttributeType.CHR, 0.04, 0.08, false);
		EquipBonusRange min_att = new EquipBonusRange(AttributeType.MIN_ATT, 1.1, 1.8, true);
		EquipBonusRange min_attX = new EquipBonusRange(AttributeType.MIN_ATT, 10, 1000, false);
		EquipBonusRange def = new EquipBonusRange(AttributeType.DEFENCE, 10, 30, false);
		EquipBonusRange defX = new EquipBonusRange(AttributeType.DEFENCE, 1.02, 1.10, true);
		EquipBonusRange d_range = new EquipBonusRange(AttributeType.D_RANGE, 100, 1000, false);
		EquipBonusRange d_rangeX = new EquipBonusRange(AttributeType.D_RANGE, 100, 1000, true);
		EquipBonusRange[] range_table = {m_hp, m_hpX, m_mp, m_mpX, str, strX, dex, dexX, wis, wisX, luk,
				lukX, dod, crit, chr, min_att, min_attX, def, defX, d_range, d_rangeX};
		this.range_table = range_table;
		Multiplier common_m = new Multiplier(ItemRarity.COMMON, 0.7, 2.5);
		Multiplier rare_m = new Multiplier(ItemRarity.RARE, 1.6, 6.5);
		Multiplier legendary_m = new Multiplier(ItemRarity.COMMON, 5.5, 8.0);
		Multiplier[] multiplier = {common_m, rare_m, legendary_m};
		this.multiplier = multiplier;
	}
	
	
	protected class EquipBonusRange {
		protected AttributeType type;
		protected double max;
		protected double min;
		protected boolean isMulti;
		protected EquipBonusRange(AttributeType t, double m, double max, boolean is) {
			this.type = t;
			this.min = m;
			this.max = max;
			this.isMulti = is;
		}
	}
	
	protected class Multiplier {
		protected ItemRarity rarity;
		protected double max;
		protected double min;
		protected Multiplier(ItemRarity t, double m, double max) {
			this.rarity = t;
			this.min = m;
			this.max = max;
		}
	}
	
	

	public Equipment makeRandomEquipment() {
		
		int ran_num = RandomModule.RandomBetween(110, 189);
		
		int max_bonuses;
		if ((ran_num%100)%10 == ItemRarity.LEGENDARY.id){
			int ran_num2 = RandomModule.RandomBetween(0, 1000);
			if (ran_num2 > 900) {
				max_bonuses = 6;
			} else {
				return makeRandomEquipment();
			}
		} else if ((ran_num%100)%10 >= ItemRarity.RARE.id) {
			max_bonuses = 3;
		} else {
			max_bonuses = 1;
		}
		
		Equipment equip = new Equipment(ran_num);
		
		int isMade = 0;
		do {
				int ran_num_attr = RandomModule.RandomBetween(0, range_table.length-1);
				boolean ran_is_multi = RandomModule.ran.nextBoolean();
				if (range_table[ran_num_attr].isMulti == ran_is_multi) {
					double ran_base = RandomModule.RandomBetweenGaussian(range_table[ran_num_attr].min, range_table[ran_num_attr].max);
					double ran_multiplier = RandomModule.RandomBetweenGaussian(2.5, 7.0);
					AttributeBonus new_bonus = new AttributeBonus(range_table[ran_num_attr].type, (ran_base*ran_multiplier),ran_is_multi);
					equip.addAttributeBonus(new_bonus);
					isMade++;
				}
			} while (isMade < max_bonuses);
		
		double base_def = RandomModule.RandomBetweenGaussian(10, 100);
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
		double total_defence = RandomModule.RandomBetweenGaussian(base_def*min, base_def*max);
		AttributeBonus new_bonus = new AttributeBonus(AttributeType.DEFENCE, total_defence, false);
		equip.addAttributeBonus(new_bonus);
		
		return equip;
	}
	
	public Equipment makeRandomEquipmentByRarity(ItemRarity i) {
		
		Equipment equip = makeRandomEquipment();
		
		if (equip.getItemRarity() != i){
			return makeRandomEquipmentByRarity(i);
		} else {
			return equip;
		}
	}
	
	public Equipment makeRandomEquipByType(int EQUIPTYPE) {
		
		Equipment equip = makeRandomEquipment();
		
		if ((equip.itemID/10)%10 != EQUIPTYPE){
			return makeRandomEquipByType(EQUIPTYPE);
		} else {
			return equip;
		}
}
}


