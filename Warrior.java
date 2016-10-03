package classes;

import player.*;
import interfaces.*;

public class Warrior extends Profession {
	
	//makes a warrior of level n
	public Warrior(int n) {
		super(ProfessionName.WARRIOR, n);
		super.available_skills = available_warrior_skills;
		
		//all bonuses are initialized in the constructor
		AttributeBonus wstr_bonus = new AttributeBonus(AttributeType.STR, 2.2*player_level, false);
		AttributeBonus wdex_bonus = new AttributeBonus(AttributeType.DEX, 1.5*player_level,false);
		AttributeBonus wrange_bonus = new AttributeBonus(AttributeType.RANGE, 1 ,false);
		AttributeBonus wdrange_bonus = new AttributeBonus(AttributeType.D_RANGE, 15*player_level,false);
		AttributeBonus whp_bonus = new AttributeBonus(AttributeType.M_HP, 25*player_level,false);
		AttributeBonus wmp_bonus = new AttributeBonus(AttributeType.M_MP, 1.2*player_level,false);
		
		AttributeBonus[] bonuses = {wstr_bonus , wdex_bonus,  wrange_bonus, wdrange_bonus,  whp_bonus, wmp_bonus};
		
		super.profession_bonuses = bonuses;
		super.range = 1;
	}
	
	
	//this creates a new warrior that initialize all the skill and profession bonuses
	//@Override
	public Profession updateSkillsAndBonusestoLevel(int i) {
		Skill[] tempStorage = this.available_skills;	
		Profession newP = new Warrior(i);
		newP.available_skills = tempStorage;
		return newP;
	};
	
	//-- Skills
	
	//BlessingOfTheWarrior
	//Skill-ID = 1001
	//isPassive = true
	protected ImplementOnPlayer _BOTW_Implementation = new ImplementOnPlayer() {
		 public Player activateImpl(Object skill, Player p){
			p.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.MIN_ATT).addtoValue(20d*((Skill)skill).skill_level);
			p.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.M_HP).addtoValue(200*((Skill)skill).skill_level);
			return p;
		};
	};
	
	//Power Strike
	//Skill-ID = 2002
	//isPassive = false
	protected ImplementOnPlayer _PS_Implementation = new ImplementOnPlayer() {
		public Player activateImpl(Object skill, Player p) {
			p.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.MIN_ATT).addtoValue(200d*((Skill)skill).skill_level);
			p.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.D_RANGE).setValue(0);
			return p;
		}
	};
	
	//All skill initializations
	public Skill Blessing_Of_The_Warrior = new Skill("Blessing Of The Warrior", _BOTW_Implementation, true, 3, 1001);
	public Skill Power_Strike = new Skill("Power Strike", _PS_Implementation, false, 5, 1002);
	public Skill[] available_warrior_skills = {Blessing_Of_The_Warrior, Power_Strike};
	
}
