package interfaces;

public enum AttributeType {
	
	//10000 ~ 2000 basic attribute
	//30000 ~ primary attribute
	//40000 ~ secondary attribute
	//50000 ~ damage range
	
	NONE("Invalid Type", 0),
	M_HP("Max Health", 11000),
	M_MP("Max Mana", 21000),
	STR("Strength", 31000),
	DEX("Dexterity", 32000),
	WIS("Intelligence", 33000),
	LUK("Luck", 34000),
	DODGE("Dodge", 41000),
	CRIT("Critical Bonus", 42000),
	D_RANGE("Damage Range", 43000),
	MIN_ATT("Minimum Damage", 44000),
	DEFENCE("Defence", 45000),
	CHR("Charisma", 46000),
	RANGE("Attack Range", 51000);
	
	private final int AttributeID;
	private final String AttributeName;
	
	
	public int getAttributeID() {
		return AttributeID;
	}
	public String getAttributeName() {
		return AttributeName;
	}
	
	AttributeType(String name, int ID) {
		this.AttributeName = name;
		this.AttributeID = ID;
	}
	
}