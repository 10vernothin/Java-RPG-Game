package interfaces;

public enum ItemType {
	EQUIPMENT(100),
	USEFUL_POTION(200),
	HARMFUL_POTION(300),
	SCROLL(400),
	ETC(500);
	public int TypeID;
	ItemType(int TypeID) {
		this.TypeID = TypeID;
	}
}
