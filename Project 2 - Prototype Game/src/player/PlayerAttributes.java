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
	protected PlayerAttribute[] aListofAttributes;
	protected PlayerAttribute[] aListofBaseAttributes;
	protected final PlayerAttribute invalid;
	
	//-- Constructor
	public PlayerAttributes() {
		PlayerAttribute[] aListofAttributes = new PlayerAttribute[AttributeType.values().length -1];
		for (int x = 0; x < AttributeType.values().length; x++) {
			if (AttributeType.values()[x] != AttributeType.NONE) {
				if (AttributeType.values()[x].getAttributeID() < 30000) {
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 100);
				} else if (AttributeType.values()[x].getAttributeID() < 40000) {
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 4);
				} else if (AttributeType.values()[x].getAttributeID() < 50000) {
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 0.01);
				} else if (AttributeType.values()[x].getAttributeID() < 60000){
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 1);
				} else if (AttributeType.values()[x].getAttributeID() < 70000){
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 5);
				} else {
					aListofAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 10);
				}
			}
		} 
		PlayerAttribute[] aListofBaseAttributes = new PlayerAttribute[AttributeType.values().length -1];
		for (int x = 0; x < AttributeType.values().length; x++) {
			if (AttributeType.values()[x] != AttributeType.NONE) {
				if (AttributeType.values()[x].getAttributeID() < 30000) {
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 100);
				} else if (AttributeType.values()[x].getAttributeID() < 40000) {
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 4);
				} else if (AttributeType.values()[x].getAttributeID() < 50000) {
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 0.01);
				} else if (AttributeType.values()[x].getAttributeID() < 60000){
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 1);
				} else if (AttributeType.values()[x].getAttributeID() < 70000){
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 0);
				} else {
					aListofBaseAttributes[x-1] = new PlayerAttribute(AttributeType.values()[x], 10);
				}
			}
		} 
		this.aListofAttributes = aListofAttributes;
		this.aListofBaseAttributes = aListofBaseAttributes;
		this.invalid = new PlayerAttribute(AttributeType.NONE, 0);
	}
	
	
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
			if (a.atype == type) {
				return a;
			}
		}
		return invalid;
	}
	
	//base attribute getters
	
	public PlayerAttribute[] getListViewofBase() {
		return aListofBaseAttributes;
	}
	
	public PlayerAttribute getPlayerBaseAttributebyID(int id) {
		for (PlayerAttribute a: aListofBaseAttributes) {
			if (a.getID() == id) {
				return a;
			}
		}
		return invalid;
	}
	
	public PlayerAttribute getPlayerBaseAttributebyTypeName(String name) {
		for (PlayerAttribute a: aListofBaseAttributes) {
			if (a.getAttName() == name) {
				return a;
			}
		}
		return invalid;
	}
	
	public PlayerAttribute getPlayerBaseAttributeByType(AttributeType type) {
		for (PlayerAttribute a: aListofBaseAttributes) {
			if (a.atype == type) {
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
		
		private AttributeType atype;
		protected String name;
		protected int id;
		protected final double base_value;
		protected double value;
		
		public String getAttName(){
			return name;
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
		
		//the value is a multiplier from 0.0
		public void multiplytoValue(double value) {
			this.value *= (1 + value);
		}
		
		PlayerAttribute(AttributeType t, double base_value) {
			this.name = t.getAttributeName();
			this.id = t.getAttributeID();
			this.base_value = base_value;
			this.value = this.base_value;
			this.setAtype(t);
		}

		public AttributeType getAtype() {
			return atype;
		}

		public void setAtype(AttributeType atype) {
			this.atype = atype;
		}
		
	}
	
}
