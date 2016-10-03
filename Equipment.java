package items;

import interfaces.*;

public class Equipment extends Item {
	//All equipments have an objID of 1xy 
	//where x is the equip type and y is its rarity
	
	//-- Fields
	protected AttributeBonus[] all_bonuses;
	protected String name;
	protected static final int EQUIP_HAT = 1;
	protected static final int EQUIP_TOP = 2;
	protected static final int EQUIP_BOTTOM = 3;
	protected static final int EQUIP_SHOES = 4;
	protected static final int EQUIP_SWORD = 5;
	protected static final int EQUIP_SHIELD = 6;
	protected static final int EQUIP_GLOVES = 7;
	protected static final int EQUIP_RING = 8;
	protected String equipment_type;
	
	//-- Constructor
	public Equipment(int ID) {
		super(ID);
		switch ((itemID - 100)/10) {
		case (EQUIP_HAT):
			equipment_type = "Hat";
		break;
		case (EQUIP_TOP):
			equipment_type = "Top";
		break;
		case (EQUIP_BOTTOM):
			equipment_type = "Bottom";
		break;
		case (EQUIP_SHOES):
			equipment_type = "Shoes";
		break;
		case (EQUIP_SWORD):
			equipment_type = "Sword";
		break;
		case (EQUIP_SHIELD):
			equipment_type = "Shield";
		break;
		case (EQUIP_GLOVES):
			equipment_type = "Gloves";
		break;
		case (EQUIP_RING):
			equipment_type = "RING";
		break;
		default:
			equipment_type = "Shield";
		}
	}
	
	public void addAttributeBonus(AttributeBonus b) {
		if (this.all_bonuses != null) {
			AttributeBonus[] new_bonus_arr = new AttributeBonus[all_bonuses.length+1];
			for (int i = 0; i < this.all_bonuses.length; i++) {
				new_bonus_arr[i] = this.all_bonuses[i];
			}
			new_bonus_arr[all_bonuses.length] = b;
			this.all_bonuses = new_bonus_arr;
		} else {
			AttributeBonus[] new_bonus_arr = {b};
			this.all_bonuses = new_bonus_arr;
		}
	}
	
	public String getItemName() {
		if (super.itemID == 0) {
			return "EMPTY";
		} else {
			return (this.getItemRarity().identifier + " " + equipment_type);
		}
	}

}