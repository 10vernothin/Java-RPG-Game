package player;

import items.Equipment;

public class PlayerEquipped {
	
	protected Equipment hat;
	protected Equipment top;
	protected Equipment bottom;
	protected Equipment weapon;
	protected Equipment shield;
	
	protected PlayerEquipped() {
		hat = new Equipment(0);
		top = new Equipment(0);
		bottom = new Equipment(0);
		weapon = new Equipment(0);
		shield = new Equipment(0);
	}
	
	
}
