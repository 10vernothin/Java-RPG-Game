package classes;

import interfaces.*;
import player.*;

public class Rogue extends Profession {
	
	//makes a rogue of level n
	public Rogue(int n) {
		super(ProfessionName.ROGUE, n);
		AttributeBonus rstr_bonus = new AttributeBonus(AttributeType.DEX, 2.3, false);
		AttributeBonus rdex_bonus = new AttributeBonus(AttributeType.LUK, 7.4, false);
		AttributeBonus rrange_bonus = new AttributeBonus(AttributeType.RANGE, 2, false);
		AttributeBonus rcrit_bonus = new AttributeBonus(AttributeType.CRIT, 35.4, false);
		AttributeBonus rhp_bonus = new AttributeBonus(AttributeType.M_HP, 0.05, true);
		AttributeBonus rmp_bonus = new AttributeBonus(AttributeType.M_MP, 0.05, true);
		AttributeBonus rmatt_bonus = new AttributeBonus(AttributeType.DODGE, 0.06, true);
		AttributeBonus[] available_rogue_bonuses =  {rstr_bonus, rdex_bonus, rrange_bonus, rcrit_bonus, rmatt_bonus,rhp_bonus, rmp_bonus};

		super.available_skills = available_rogue_skills;
		super.profession_bonuses = available_rogue_bonuses;
	}
	
	
	//this creates a new rogue that keeps all the skill bonuses
	//@Override
	public Profession updateSkillsAndBonusestoLevel(int i) {
		Skill[] tempStorage = this.available_skills;
		AttributeBonus[] bonuses = this.profession_bonuses;
		Profession newP = new Rogue(i);
		newP.available_skills = tempStorage;
		newP.profession_bonuses = bonuses;
		return newP;
	};
	
	//-- Bonuses
	
	//-- Skills
	
	protected Skill[] available_rogue_skills;
	
}