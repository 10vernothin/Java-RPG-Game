package interfaces;

public enum ProfessionName {
	WARRIOR(100, "Warrior", "warrior", "WARRIOR"),
	RANGER( 200, "Ranger", "ranger", "RANGER"),
	WIZARD(300, "Wizard", "wizard", "WIZARD"),
	ROGUE(400, "Rogue", "rogue", "ROGUE"),
	NULL(0, "NONE", "NULL", "NULL");
	public final String name_cap;
	public final String name_;
	public final String name_all_cap;
	public final int profession_id;
	ProfessionName(int profession_id, String name_cap, String name_, String name_all_cap) {
		this.profession_id = profession_id;
		this.name_ = name_;
		this.name_cap = name_cap;
		this.name_all_cap = name_all_cap;
	}
	
	public static final ProfessionName findProfession(String name) {
		for (ProfessionName f: ProfessionName.values()) {
			if (f.name_cap == name || f.name_ == name || f.name_all_cap == name) {
				return f;
			}
		}
		return ProfessionName.NULL;
	}
	
}
