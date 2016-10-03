package interfaces;

public enum ItemRarity {
	COMMON("Common", 1),
	RARE("Rare", 7),
	LEGENDARY("Legendary", 9);
	public String identifier;
	public int id;
	ItemRarity(String identifier, int id) {
		this.identifier = identifier;
		this.id = id;
	}
}
