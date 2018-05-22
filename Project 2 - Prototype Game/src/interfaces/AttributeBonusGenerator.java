package interfaces;


public class AttributeBonusGenerator {
	
	protected final double DEF_MULTIPLIER_BROADENING_CONSTANT = 1.2;
	protected final double DEF_MULTIPLIER_SHIFT_CONSTANT = 1;
	
	private double generateDefaultMultiplier(int level) {
		double min = DEF_MULTIPLIER_BROADENING_CONSTANT*((double)level - DEF_MULTIPLIER_SHIFT_CONSTANT);
		double max = DEF_MULTIPLIER_BROADENING_CONSTANT*((double)level + DEF_MULTIPLIER_SHIFT_CONSTANT);
		if (min < 0) {
			min = 0;
		}
		return RandomModule.RandomBetweenGaussian(min, max);
	}
	
	public AttributeBonusRange[] generateRangeTable() {
		AttributeBonusRange[] range_table = {
				
			//use these instantiations to change the base 
			new AttributeBonusRange(AttributeType.M_HP, 50, 100, false),
			new AttributeBonusRange(AttributeType.M_HP, 0.01, 0.08, true),
			new AttributeBonusRange(AttributeType.M_MP, 50, 100, false),
			new AttributeBonusRange(AttributeType.M_MP, 0.01, 0.08, true),
			new AttributeBonusRange(AttributeType.STR, 1, 20, false),
			new AttributeBonusRange(AttributeType.STR, 0.01, 0.2, true),
			new AttributeBonusRange(AttributeType.DEX, 1, 20, false),
			new AttributeBonusRange(AttributeType.DEX, 0.01, 0.2, true),
			new AttributeBonusRange(AttributeType.WIS, 1, 20, false),
			new AttributeBonusRange(AttributeType.WIS, 0.01, 0.2, true),
			new AttributeBonusRange(AttributeType.LUK, 1, 20, false),
			new AttributeBonusRange(AttributeType.LUK, 0.01, 0.2, true),
			new AttributeBonusRange(AttributeType.DODGE, 0.02, 0.4, true),
			new AttributeBonusRange(AttributeType.CRIT, 0.01, 0.1, false),
			new AttributeBonusRange(AttributeType.CHR, 0.04, 0.08, false),
			new AttributeBonusRange(AttributeType.MIN_ATT, 0.1, 0.8, true),
			new AttributeBonusRange(AttributeType.MIN_ATT, 10, 100, false),
			new AttributeBonusRange(AttributeType.DEFENCE, 10, 40, false),
			new AttributeBonusRange(AttributeType.DEFENCE, 0.02, 0.10, true),
			new AttributeBonusRange(AttributeType.D_RANGE, 100, 200, false),
			new AttributeBonusRange(AttributeType.D_RANGE, 0.05, 0.3, true) 
	};
		return range_table;
	}
	
	public AttributeBonus generateRandomAttributeBonus(int level) {
		AttributeBonusRange[] range_table = generateRangeTable();
		int ran_num_attr = RandomModule.RandomBetween(0, range_table.length-1);
		boolean ran_is_multi = RandomModule.ran.nextBoolean();
		if (range_table[ran_num_attr].isMulti() == ran_is_multi) {
			double ran_base = RandomModule.RandomBetweenGaussian(range_table[ran_num_attr].getMin(), range_table[ran_num_attr].getMax());
			double ran_multiplier = 1;
			AttributeBonus new_bonus = new AttributeBonus(range_table[ran_num_attr].getType(), (ran_base*ran_multiplier),ran_is_multi);
			return new_bonus;
		} else {
			return AttributeBonus.returnNoBonus();
		}
	}
	
	public AttributeBonus generateDefaultRandomAttributeBonus(int level) {
		AttributeBonusRange[] range_table = generateRangeTable();
		int ran_num_attr = RandomModule.RandomBetween(0, range_table.length-1);
		boolean ran_is_multi = RandomModule.ran.nextBoolean();
		if (range_table[ran_num_attr].isMulti() == ran_is_multi) {
			double ran_base = RandomModule.RandomBetweenGaussian(range_table[ran_num_attr].getMin(), range_table[ran_num_attr].getMax());
			double ran_multiplier = generateDefaultMultiplier(level);
			AttributeBonus new_bonus = new AttributeBonus(range_table[ran_num_attr].getType(), (ran_base*ran_multiplier),ran_is_multi);
			return new_bonus;
		} else {
			return AttributeBonus.returnNoBonus();
		}
	}
	
	public AttributeBonus generateRandomAttributeBonus() {
		return generateRandomAttributeBonus(1);
	}
	
	
	public AttributeBonus generateAttributeBonus(AttributeType t, int level) {
		AttributeBonusRange[] range_table = generateRangeTable();
		boolean ran_is_multi = RandomModule.ran.nextBoolean();
		double ran_base = RandomModule.RandomBetweenGaussian(
				getAttributeRange(t, ran_is_multi, range_table)[0],  
				getAttributeRange(t, ran_is_multi, range_table)[1]);
		double ran_multiplier = generateDefaultMultiplier(level);
		AttributeBonus new_bonus = new AttributeBonus(t, (ran_base*ran_multiplier),ran_is_multi);
		return new_bonus;
	}
	
	public AttributeBonus generateAttributeBonus(AttributeType t) {
		return generateAttributeBonus(t, 1);
	}
	
	
	
	//this will give a range = double {min, max} 
	protected double[] getAttributeRange(AttributeType t, boolean isMulti, AttributeBonusRange[] r) {
		for (AttributeBonusRange rr: r) {
			if (rr.isMulti == isMulti && rr.type == t) {
				double max = rr.max;
				double min = rr.min;
				double[] ans = {min, max};
				return ans;
			}
		}
		double[] NONE = {0,0};
		return NONE;
	}
	
	
	protected class AttributeBonusRange {
		private AttributeType type;
		private double max;
		private double min;
		private boolean isMulti;
		protected AttributeBonusRange(AttributeType t, double m, double max, boolean is) {
			this.setType(t);
			this.setMin(m);
			this.setMax(max);
			this.setMulti(is);
		}
		public double getMax()  {
			return max;
			
		}
		private void setMax(double max) {
			this.max = max;
			
		}
		public boolean isMulti() {
			return isMulti;
		}
		public void setMulti(boolean isMulti) {
			this.isMulti = isMulti;
		}
		public double getMin() {
			return min;
		}
		public void setMin(double min) {
			this.min = min;
		}
		public AttributeType getType() {
			return type;
		}
		public void setType(AttributeType type) {
			this.type = type;
		}
	}
	
}
