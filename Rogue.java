package classes;

import interfaces.*;
import player.*;

public class Rogue extends Profession {
	
	//makes a rogue of level n
	public Rogue(int n) {
		super(ProfessionName.ROGUE, n);
		super.available_skills = available_rogue_skills;
		super.profession_bonuses = rogue_bonuses;
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
	
	protected AttributeBonus[] rogue_bonuses;
	
	//-- Skills
	
	protected Skill[] available_rogue_skills;
	
}