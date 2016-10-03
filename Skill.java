package classes;

import player.*;
import interfaces.*;

public class Skill {
	
	
	protected ProfessionName SkillProfession;
	protected Player unaltered;
	protected String skill_name;
	protected int skill_id;
	protected boolean isInfinite;
	protected int duration;
	protected int skill_level = 0;
	protected final int max_skill_lvl = 10;
	protected double mana_usage_rating;
	ImplementOnPlayer impl;
	protected boolean activated = false;
	
	
	public Skill(String name, ImplementOnPlayer impl, boolean isInfinite, double mana_usage_rating, int id) {
		this.skill_name = name;
		this.impl = impl;
		this.skill_id = id;
		this.isInfinite = isInfinite;
		this.mana_usage_rating = mana_usage_rating;
	}


	public void levelUpSkillnoEffectOnPlayer() {
		this.skill_level++;	
	};
	
	public void activateSkill() {
			this.activated = true;
	};
	
	public void deactivateSkill() {
			this.activated = false;
	};
	
	public Player Implement_or_Deimplement_Skill(Player p) {
		if (this.activated == true) {
			if (unaltered == null) {
				unaltered = p.deepCopy();
				return impl.activateImpl(this, p);
			} else {
				return p;
			}	
		} else {
			if (unaltered != null) {
				Player temp = unaltered;
				unaltered = null;
				return temp;
				} else {
					return p;
				}
			}
		}
	
	
	//getters and setters
	
	public boolean getActivation() {
		return activated;
	}
	
	public Skill getSkill() {
		return this;
	}


	public ProfessionName getSkillProfession() {
		return SkillProfession;
	}


	public Player getUnaltered() {
		return unaltered;
	}


	public String getSkill_name() {
		return skill_name;
	}


	public int getSkill_id() {
		return skill_id;
	}


	public boolean isInfinite() {
		return isInfinite;
	}


	public int getDuration() {
		return duration;
	}


	public int getSkill_level() {
		return skill_level;
	}


	public int getMax_skill_lvl() {
		return max_skill_lvl;
	}


	public double getMana_usage_rating() {
		return mana_usage_rating;
	}


	public boolean isActivated() {
		return activated;
	}


	public void setSkillProfession(ProfessionName skillProfession) {
		SkillProfession = skillProfession;
	}


	public void setUnaltered(Player unaltered) {
		this.unaltered = unaltered;
	}


	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}


	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}


	public void setInfinite(boolean isInfinite) {
		this.isInfinite = isInfinite;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public void setSkill_level(int skill_level) {
		this.skill_level = skill_level;
	}


	public void setMana_usage_rating(double mana_usage_rating) {
		this.mana_usage_rating = mana_usage_rating;
	}


	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	
}