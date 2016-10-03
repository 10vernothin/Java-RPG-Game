package interfaces;

public enum ItemType {
	EQUIPMENT(100),
	USABLE(200),
	ETC(300);
	public int TypeID;
	ItemType(int TypeID) {
		this.TypeID = TypeID;
	}
}
