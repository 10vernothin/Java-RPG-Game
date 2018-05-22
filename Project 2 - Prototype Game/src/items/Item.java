package items;
import interfaces.*;

public abstract class Item {

	
	protected int itemID; 
	
	public ItemRarity getItemRarity() {
		if ((itemID%100)%10 == ItemRarity.LEGENDARY.id) {
			return ItemRarity.LEGENDARY;
		} else if ((itemID%100)%10 >= ItemRarity.RARE.id) {
			return ItemRarity.RARE;
		} else {
			return ItemRarity.COMMON;
		}
	}
	
	public ItemType getItemType() {
		for (ItemType t: ItemType.values()) {
			if (t.TypeID == (itemID/100)*100) {
				return t;
			}
		}
		return null;
	}
	
	public int getItemID() {
		return itemID;
	}
	
	public abstract String getItemName();
	
	protected Item(int itemID) {
		this.itemID = itemID;
	}
	
	public static Item returnNullItem(Object o) {
		Item i = (Item)o;
		i.itemID = 0;
		return i;
	}
	
}
