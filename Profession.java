package classes;

import interfaces.*;

public abstract class Profession {
	
	protected int player_level;
	protected ProfessionName profession_type;
	protected Skill[] available_skills;
	protected AttributeBonus[] profession_bonuses;
	protected int range = 1;
	
	//this is the constructor for subclass initialization
	protected Profession (
			ProfessionName profession_type,
			int player_level
			) {
		this.profession_type = profession_type;
		this.player_level = player_level;
	}
	
	
	public abstract Profession updateSkillsAndBonusestoLevel(int i);
	
	protected double makeGrad(int n, double x) {
		if (n % 10 == 0) {
			return (Math.getExponent(-n + x));
		} else {
			return (makeGrad(n-1, x));
		}
	}
	
	//-- These functions check if a particular skill is available to the class
	
	public boolean isSkillAvailablebyID(int id) {
		for (Skill s: available_skills) {
			if (s.getSkill_id() == id) {
				return true;
			}
		} return false;
	}
	
	public boolean isSkillAvailablebyName(String name) {
		for (Skill s: available_skills) {
			if (s.getSkill_name() == name) {
				return true;
			}
		} return false;
	}

	//-- Getters
	
	public Skill getAvailableSkillByName(String name) {
		for (Skill s: available_skills) {
			if (s.getSkill_name() == name) {
				return s;
			}
		} return null;
	}
	
	public Skill getAvailableSkillByID(int id){
		for (Skill s: available_skills) {
			if (s.getSkill_id() == id) {
				return s;
			}
		} return null;
	}
	
	public Skill[] getListofAvailableSkills() {
		return available_skills;
	}
	
	public AttributeBonus[] getListofClassBonuses() {
		return profession_bonuses;
	}
	
	public ProfessionName getClassName() {
		return profession_type;
	}
	
	public int getRange() {
		return range;
	}
	
	

}