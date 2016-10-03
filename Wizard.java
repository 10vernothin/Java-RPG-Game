package classes;

import interfaces.*;
import player.*;

public class Wizard extends Profession {
	
	//makes a wizard of level n
	public Wizard(int n) {
		super(ProfessionName.WIZARD, n);
		super.available_skills = available_wizard_skills;
		super.profession_bonuses = wizard_bonuses;
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
	
	//-- Bonuses
	
	protected AttributeBonus[] wizard_bonuses;
	
	//-- Skills
	
	protected Skill[] available_wizard_skills;
	
}