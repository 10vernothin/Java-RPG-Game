package player;

import interfaces.*;

/*
 *  PlayerAttributes is a module that contains all the attributes of a player mutable by items or class
 *  The outer class is immutable
 */

public class PlayerAttributes {
	
	//-- Fields
	
	//dummy attribute fields (They will be null pointers, for reference only)
	//- Collection of all the variables
	protected PlayerAttribute[] aListofAttributes = new PlayerAttribute[AttributeType.values().length -1];
	protected final PlayerAttribute invalid = new PlayerAttribute(AttributeType.NONE, 0);
	
	//-- Constructor
	public PlayerAttributes() {
		for (int x = 0; x < AttributeType.values().length; x++) {
			if (AttributeType.values()[x] != AttributeType.NONE) {
				if (AttributeType.values()[x].getAttributeID() < 30000) {
					this.aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 100);
				} else if (AttributeType.values()[x].getAttributeID() < 40000) {
					this.aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 10);
				} else if (AttributeType.values()[x].getAttributeID() < 50000) {
					this.aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 0.01);
				} else {
					this.aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 1);
				}
			}
		} 
	}
	
	//- invalid type (to avoid NullException)
	
	//Getters
	
	public PlayerAttributes getPlayerAttribute() {
		return this;
	}
	
	public PlayerAttribute[] getListView() {
		return aListofAttributes;
	}
	
	public PlayerAttribute getPlayerAttributebyID(int id) {
		for (PlayerAttribute a: aListofAttributes) {
			if (a.getID() == id) {
				return a;
			}
		}
		return invalid;
	}
	
	public PlayerAttribute getPlayerAttributebyTypeName(String name) {
		for (PlayerAttribute a: aListofAttributes) {
			if (a.getAttName() == name) {
				return a;
			}
		}
		return invalid;
	}
	
	public PlayerAttribute getPlayerAttributeByType(AttributeType type) {
		for (PlayerAttribute a: aListofAttributes) {
			if (a.getAttType() == type) {
				return a;
			}
		}
		return invalid;
	}
	
	/*
	 * PlayerAttribute is an inner type, and is the mutable part of (the out class is immutable).
	 * Each outer Attribute will only have one instance of PlayerAttribute with a unique AttributeType
	 * 
	 */
	
	public class PlayerAttribute {
		
		protected AttributeType atype;
		protected String name;
		protected int id;
		protected final double base_value;
		protected double value;
		
		public String getAttName(){
			return name;
		}
		
		public AttributeType getAttType(){
			return atype;
		}
		
		public int getID(){
			return id;
		}
		
		public double getValue() {
			return value;
		}
		
		public void setValue(double value) {
			this.value = value;
		}
		
		public void addtoValue(double value) {
			this.value += value;
		}
		
		public void multiplytoValue(double value) {
			this.value *= value;
		}
		
		PlayerAttribute(AttributeType t, double base_value) {
			this.name = t.getAttributeName();
			this.id = t.getAttributeID();
			this.base_value = base_value;
			this.value = this.base_value;
			this.atype = t;
		}
		
	}
	
}
