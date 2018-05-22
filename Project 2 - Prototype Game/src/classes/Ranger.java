package classes;

import interfaces.*;
import player.*;


public class Ranger extends Profession {
	
	//makes a Ranger of level n
	public Ranger(int n) {
		super(ProfessionName.RANGER, n);
		AttributeBonus rstr_bonus = new AttributeBonus(AttributeType.STR, 2.3, false);
		AttributeBonus rdex_bonus = new AttributeBonus(AttributeType.DEX, 6.4, false);
		AttributeBonus rrange_bonus = new AttributeBonus(AttributeType.RANGE, 6 ,false);
		AttributeBonus hp_bonus = new AttributeBonus(AttributeType.M_HP, 30, false);
		AttributeBonus mp_bonus = new AttributeBonus(AttributeType.M_MP, 20, false);
		AttributeBonus rcrit_bonus = new AttributeBonus(AttributeType.CRIT, 0.08, true);
		AttributeBonus rmatt_bonus = new AttributeBonus(AttributeType.MIN_ATT, 0.01, true);
		AttributeBonus[] available_ranger_bonuses =  {rstr_bonus, rdex_bonus, rrange_bonus, rcrit_bonus, rmatt_bonus, hp_bonus, mp_bonus};
		
		
		super.available_skills = this.available_ranger_skills;
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
