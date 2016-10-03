package classes;

import interfaces.*;
import player.*;


public class Ranger extends Profession {
	
	//makes a Ranger of level n
	public Ranger(int n) {
		super(ProfessionName.RANGER, n);
		AttributeBonus rstr_bonus = new AttributeBonus(AttributeType.STR, 1*player_level,false);
		AttributeBonus rdex_bonus = new AttributeBonus(AttributeType.DEX, 4*player_level,false);
		AttributeBonus rrange_bonus = new AttributeBonus(AttributeType.RANGE, 6 ,false);
		AttributeBonus rcrit_bonus = new AttributeBonus(AttributeType.CRIT, (1+(0.08*player_level)), true);
		AttributeBonus rmatt_bonus = new AttributeBonus(AttributeType.MIN_ATT, 1+(0.7*player_level), true);
		AttributeBonus[] available_ranger_bonuses =  {rstr_bonus, rdex_bonus, rrange_bonus, rcrit_bonus, rmatt_bonus};
		
		
		super.available_skills = this.available_ranger_skills;
 ;
		super.profession_bonuses = available_ranger_bonuses;
	}
	
	
	//this creates a new Ranger that keeps all the skill bonuses
	//@Override
	public Profession updateSkillsAndBonusestoLevel(int i) {
		Skill[] tempStorage = this.available_skills;
		AttributeBonus[] bonuses = this.profession_bonuses;
		Profession newP = new Ranger(i);
		newP.available_skills = tempStorage;
		newP.profession_bonuses = bonuses;
		return newP;
	};
	
	//-- Bonuses
	
	
	//-- Skills
	Skill[] available_ranger_skills;
}
