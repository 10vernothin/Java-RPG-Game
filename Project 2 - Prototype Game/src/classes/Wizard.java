package classes;

import interfaces.*;
import player.*;

public class Wizard extends Profession {
	
	//makes a wizard of level n
	public Wizard(int n) {
		super(ProfessionName.WIZARD, n);
		AttributeBonus wluk_bonus = new AttributeBonus(AttributeType.LUK, 1.2, false);
		AttributeBonus wwis_bonus = new AttributeBonus(AttributeType.WIS, 8.45, false);
		AttributeBonus wrange_bonus = new AttributeBonus(AttributeType.RANGE, 2, false);
		AttributeBonus wmana_bonus = new AttributeBonus(AttributeType.M_MP, 400, false);
		AttributeBonus wchr_bonus = new AttributeBonus(AttributeType.CHR, 0.01, true);
		AttributeBonus wcrit_bonus = new AttributeBonus(AttributeType.CRIT, 0.08, true);
		AttributeBonus wdod_bonus = new AttributeBonus(AttributeType.DODGE, 0.06, true);
		AttributeBonus[] available_wizard_bonuses =  {wluk_bonus, wwis_bonus, wrange_bonus, wmana_bonus, wchr_bonus, wcrit_bonus, wdod_bonus};

		super.available_skills = available_wizard_skills;
		super.profession_bonuses = available_wizard_bonuses;
	}
	
	
	//this creates a new wizard that keeps all the skill bonuses
	//@Override
	public Profession updateSkillsAndBonusestoLevel(int i) {
		Skill[] tempStorage = this.available_skills;
		AttributeBonus[] bonuses = this.profession_bonuses;
		Profession newP = new Wizard(i);
		newP.available_skills = tempStorage;
		newP.profession_bonuses = bonuses;
		return newP;
	};
	
	//-- Skills
	
	protected Skill[] available_wizard_skills;
	
}