package interfaces;

/*
 * 
 * AttributeBonus is a class of "bonus" stats that can be found on equipments, usable objects, or classes
 * It consists of the type that it will be applied to, the value, and whether it is multiplicative
 */

public class AttributeBonus {
	
		protected AttributeType bonus_type;
		protected double value;
		protected boolean is_multiplicative;
			
		
		
		public AttributeBonus(AttributeType t, double add_bonus, boolean is_multi) {
			this.bonus_type = t;
			this.value = add_bonus;
			this.is_multiplicative = is_multi;
		}
		
		public AttributeBonus() {
			this.bonus_type = AttributeType.NONE;
			this.value = 0;
			this.is_multiplicative = false;
		}
		
		
		public AttributeBonus getThis() {
			return this;
		}
		
		public AttributeType getType() {
			return bonus_type;
		}
		
		public void changeBonusValueBy(double bonus, boolean isMulti) {
			if (isMulti) {
				this.value *= bonus;
			} else {
				this.value += bonus;
			}
		}
		
		public double getValue() {
			return value;
		}
		
		public boolean isMulti() {
			return is_multiplicative;
		}
		
		public static AttributeBonus returnNoBonus() {
			return null;
		}
}
