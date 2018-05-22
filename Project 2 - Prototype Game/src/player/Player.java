package player;

import classes.*;
import interfaces.*;

public class Player {
	
	
	//-- Fields
	
	//this is basic Player info
	protected Profession Player_Profession;
	protected String player_name;
	protected int level;
	protected double exp;
	protected int skill_points_avail;
	protected double health, mana; 
	protected PlayerAttributes Player_Attributes;
	protected PlayerEquipped Equipped;  
	protected Inventory inventory;
	
	//-- Constructor
	
	public Player(String name, ProfessionName class_name, int level) {
		Player_Attributes = new PlayerAttributes();
		initializePlayerClass(class_name, level);
		this.inventory = new Inventory(20);
		this.player_name = name;
		this.level = level;
		this.exp = 0;
		this.skill_points_avail = 1;
		buildOrUpdateStats();
		this.health = Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue();
		this.mana = Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue();
	}
	
	//Private code snippet that initializes Player_Profession
	private void initializePlayerClass(ProfessionName class_name, int level) {
		switch (class_name) {
		case WARRIOR:
			this.Player_Profession = new Warrior(level);
			break;
		case RANGER:
			this.Player_Profession = new Ranger(level);
			break;
		case WIZARD:
			this.Player_Profession = new Wizard(level);
			break;
		case ROGUE:
			this.Player_Profession = new Rogue(level);
			break;
		default:
			break;
		}	
	}
	
	//Private code snippet that calculates all the states using StatBuilder and returns the Player
	private void buildOrUpdateStats() {
		StatBuilder x = new StatBuilder(this);
		x.buildClassStats();
	}
	
	//Private code snippet used when a skill is activated
	private void updateStatUponSkill(Skill w){
		Player p = w.Implement_or_Deimplement_Skill(this);
		this.health = p.health;
		this.mana = p.mana;
		for(int i = 0; i < this.Player_Attributes.aListofAttributes.length; i++) {
			if (this.Player_Attributes.aListofAttributes[i].id < 30000) {
				this.Player_Attributes.aListofAttributes[i] = p.Player_Attributes.aListofAttributes[i];
			}
		}
		Player pp = w.Implement_or_Deimplement_Skill(this);
		for(int i = 0; i < this.Player_Attributes.aListofAttributes.length; i++) {
			if (this.Player_Attributes.aListofAttributes[i].id > 30000) {
				this.Player_Attributes.aListofAttributes[i] = pp.Player_Attributes.aListofAttributes[i];
			}
		}
	}
	//
	//-- Copy function
	
	public Player deepCopy() {
			Player copy = new Player(this.player_name, this.Player_Profession.getClassName(), this.level);
			copy.Player_Profession = this.Player_Profession; 
			copy.player_name = this.player_name; 
			copy.level = this.level; 
			copy.exp = this.exp;
			copy.skill_points_avail = this.skill_points_avail;
			copy.health = this.health; 
			copy.mana = this.mana;
			copy.Player_Attributes = this.Player_Attributes;
			return copy;
	}
			
	//-- Interface Functions
	
	//these are interface functions that activate or deactivate a player's skill
	public void activatePlayerSkillbyID(int id, boolean act) {
		for (Skill k: this.Player_Profession.getListofAvailableSkills()) {
			if (k.getSkill_id() == id && k.getSkill_level() != 0 && (mana - ((int) - k.getMana_usage_rating()*k.getSkill_level())) > 0) {
				if (act == true) {
					k.activateSkill();
					this.addMana((int)-k.getMana_usage_rating()*k.getSkill_level());
					updateStatUponSkill(k);
				} else {
					k.deactivateSkill();
					updateStatUponSkill(k);
				}
			}
		}
	}
	
	public void activatePlayerSkillbyName(String name, boolean act) {
		for (Skill k: this.Player_Profession.getListofAvailableSkills()) {
			if (k.getSkill_name() == name && k.getSkill_level() != 0 && (mana -((int) -k.getMana_usage_rating()*k.getSkill_level())) > 0) {
				if (act == true) {
					k.activateSkill();
					updateStatUponSkill(k);
					this.addMana((int)-k.getMana_usage_rating()*k.getSkill_level());
				} else {
					k.deactivateSkill();
					updateStatUponSkill(k);
				}
					
			}
		}
	}
	
	//this is an interface function that levels up the player
	public void levelUp() {
		this.level++;
		this.exp = 0;
		this.skill_points_avail++;
		this.Player_Profession = this.Player_Profession.updateSkillsAndBonusestoLevel(level);
		buildOrUpdateStats();
		this.health = this.Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue();
		this.mana = this.Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue();
	}
	
	//these are interface functions that levels up the player's skills
	public void levelUpSkillbyID(int n) {
		if (this.skill_points_avail != 0 && this.Player_Profession.isSkillAvailablebyID(n)) {
			this.skill_points_avail -= 1;
			this.Player_Profession.getAvailableSkillByID(n).levelUpSkillnoEffectOnPlayer();
		} else {
			System.out.println("Cannot level up skill due to lack of skill points or skill not available.");
			n();
		}
	}
	
	public void levelUpSkillbyName(String name) {
		if (this.skill_points_avail != 0 && this.Player_Profession.isSkillAvailablebyName(name)) {
			this.skill_points_avail -= 1;
			this.Player_Profession.getAvailableSkillByName(name).levelUpSkillnoEffectOnPlayer();
		} else { 
			System.out.println("Cannot level up skill due to lack of skill points or skill not available.");
			n();
		}
	}
	
	//these two interface functions set the health & mana (negative mana/hp if using mana or being damage) 
	public void addHealth(int n) {
		if (0 < (health + n) && (health + n) < Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue()) {
			health += n;
		} else if ((health + n) > Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue()){
			health = Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue();
		}
	}
	
	public void addMana(int n) {
		if (0 < (mana + n) && (mana + n) < Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue()) {
			mana += n;
		} else if ((mana + n) > Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue()){
			mana = Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue();
		}
	}

	//print functions
	
	protected final void n(){
		System.out.println();
	}
	
	public void printBasicStats() {
		System.out.println("--- BASIC PLAYER STATS ---");
		System.out.println("Name: " + player_name);
		System.out.println("Class: " + this.Player_Profession.getClassName());
		System.out.println("Level: " + level);
		System.out.println("Health: " + (int)health + "/" + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.M_HP).getValue());
		System.out.println("Mana: " + (int)mana + "/" + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.M_MP).getValue());
		System.out.println("Strength: " + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.STR).getValue());
		System.out.println("Dexterity: " + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.DEX).getValue());
		System.out.println("Intelligence: " + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.WIS).getValue());
		System.out.println("Luck: " + (int)Player_Attributes.getPlayerAttributeByType(AttributeType.LUK).getValue());
		System.out.println("Skill Points unused: " + this.skill_points_avail);
		n();
		
	}

	public void printDetailedAttributes() {
		System.out.println("--- DETAILED PLAYER ATTRIBUTES ---");
		PlayerAttributes p = this.Player_Attributes;
		for (PlayerAttributes.PlayerAttribute a: p.getListView()) {
			System.out.println(a.getAttName() + ": " + a.getValue());
		}
		n();
	}
	
	//----------------------------------------------------------------------- ----------------------------//
	
	
	//-- Getters and setters
	
	public PlayerEquipped getEquipped() {
		return Equipped;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public Profession getPlayer_Profession() {
		return Player_Profession;
	}

	public String getPlayer_name() {
		return player_name;
	}

	public int getLevel() {
		return level;
	}

	public double getExp() {
		return exp;
	}

	public int getSkill_points_avail() {
		return skill_points_avail;
	}

	public double getHealth() {
		return health;
	}

	public double getMana() {
		return mana;
	}

	public PlayerAttributes getPlayer_Attributes() {
		return Player_Attributes;
	}

	public void setPlayer_Profession(Profession player_Profession) {
		Player_Profession = player_Profession;
	}

	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setExp(double exp) {
		this.exp = exp;
	}

	public void setSkill_points_avail(int skill_points_avail) {
		this.skill_points_avail = skill_points_avail;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public void setPlayer_Attributes(PlayerAttributes player_Attributes) {
		Player_Attributes = player_Attributes;
	}
	
	

}