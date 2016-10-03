package player;

import items.*;

public class Inventory {
	protected Item[] inventory;
	public final int MAX_AMOUNT;
	
	public Inventory(int MAX_AMOUNT) {
		this.MAX_AMOUNT = MAX_AMOUNT;
	}
	
	
	public void addItemToInventory(Item i) {
			if (inventory == null || inventory.length ==  0) {
				Item[] new_inventory = { i};
				this.inventory = new_inventory;
			} else {
				if (inventory.length < MAX_AMOUNT ) {
				Item[] new_inv = new Item[inventory.length+1];
				for (int x = 0; x < inventory.length; x++) {
					new_inv[x] = inventory[x];
				}
				new_inv[inventory.length] = i;
				this.inventory = new_inv;
				}
			}
	}
	
	public void removeItemFromInventory(int i) {
		if (inventory != null || inventory.length !=  0) {
			Item[] new_inv = new Item[inventory.length-1];
			for (int x = 0; x < inventory.length; x++) {
				if (x < i) {
					new_inv[x] = inventory[x];
				} else if (x > i) {
					new_inv[x-1] = inventory[x];
				}
			}
			this.inventory = new_inv;
		}
	}
	
	public Item getIteminInventory(int i) {
		if (inventory != null &&  i < inventory.length) {
					return inventory[i];
		} else {
			return Item.returnNullItem(new Equipment(103));
		}
	}
	
	
}
