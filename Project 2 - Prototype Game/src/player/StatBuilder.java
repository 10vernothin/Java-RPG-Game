package player;

import interfaces.*;

class StatBuilder {

	//this interface is used to build Player stats

	private Player p;
	private StatModifierInterface dodgeBuilder;
	private StatModifierInterface defBuilder;
	private StatModifierInterface critBuilder;
	private StatModifierInterface minrangeBuilder;
	private StatModifierInterface damagerangeBuilder;
	private StatModifierInterface charismaBuilder;	

	//implementation

	private double buildthenReturnStat(StatModifierInterface s, Object[]... b) {
		double x = s.build_stat(
				p.Player_Attributes.getPlayerAttributeByType(AttributeType.STR).getValue(), 
				p.Player_Attributes.getPlayerAttributeByType(AttributeType.DEX).getValue(),
				p.Player_Attributes.getPlayerAttributeByType(AttributeType.WIS).getValue(),
				p.Player_Attributes.getPlayerAttributeByType(AttributeType.LUK).getValue(), b);
		return (double)x;
	}


	private void updatePrimaryStatToBonuses() {
		//this function makes it so that as long as the argument is a list of AttributeBonus objects, the primary stats of PlayerAttribute will be mutated
		final class Helper {
			void helperF(AttributeBonus[] o) {
				for (PlayerAttributes.PlayerAttribute a: p.Player_Attributes.getListView()) {
					for (AttributeBonus b: o){
						if (a.getAtype() == b.getType() && a.getAtype().getAttributeID() < 40000) { //this makes sure it's primary stats
							if (b.isMulti()) {
								a.multiplytoValue(b.getValue());;
							} else {
								a.addtoValue(b.getValue());
							}
						}
					}
				}
			}
		}
		Helper h = new Helper();

		//if there are other ones (for example equipment) it will be added here
		h.helperF(p.Player_Profession.getListofClassBonuses());
	}

	//interface function
	protected void buildClassStats() {

		if (p.Player_Profession.getListofClassBonuses() != null) {
			updatePrimaryStatToBonuses();

			//if there are other lists from equipment or use, it will be added here (must be static)
			Object[] list = (Object[])p.Player_Profession.getListofClassBonuses();	
			double dodge_d = (0.01)*buildthenReturnStat(dodgeBuilder, list);
			double char_d = (0.01)*buildthenReturnStat(charismaBuilder, list);
			double crit_d = (0.01)*buildthenReturnStat(critBuilder,  list);
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.DODGE).value = dodge_d;
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.DEFENCE).value = buildthenReturnStat(defBuilder,  list);
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.CRIT).value = crit_d;
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.D_RANGE).value = buildthenReturnStat(damagerangeBuilder, list);
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.MIN_ATT).value = buildthenReturnStat(minrangeBuilder, list);
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.CHR).value = char_d;
			p.Player_Attributes.getPlayerAttributeByType(AttributeType.RANGE).value = p.Player_Profession.getRange();
		}
	}

	//constructor
	protected StatBuilder(Player p) {
		
		this.p = p;
		
		//instantiation (use these to tweak the numbers)
		
		StatModifierInterface dodgeBuilder = (s, d, w, l, x) -> 
		{double y= p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.DODGE).getValue() + (0.001*(s+d)) + 0.225*l;
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.DODGE.getAttributeID()){
					if (b.isMulti()) {
						y *= b.getValue();
					} else {
						y += b.getValue();
					}
				}
			}
		}
		if (y > 100) {
			return 100;
		} else {
		return y;
		}};

		StatModifierInterface defBuilder = (s, d, w, l, x) -> 
		{double y= p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.DEFENCE).getValue() + 0.6*s + 0.5*d + + 0.1*w + 0.3*l;
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.DEFENCE.getAttributeID()){
					if (b.isMulti()) {
						y *= b.getValue();
					} else {
						y += b.getValue();
					}
				}
			}
		}
		return y;};

		StatModifierInterface critBuilder = (s, d, w, l, x) -> 
		{double y = p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.CRIT).getValue() + 0.001*s + 0.08*l + 0.003*w + 0.002d;
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.CRIT.getAttributeID()){
					if (b.isMulti()) {
					} else {
						y += b.getValue();
					}
				}
			}
		}
		if (y > 100) {
			return 100;
		} else {
		return y;
		}};

		StatModifierInterface damagerangeBuilder = (s, d, w, l, x) -> 
		{double y = p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.D_RANGE).getValue() + 7.5*s- 1*d + 3*l ;
		if (p.Player_Profession.getClassName() == ProfessionName.WIZARD) {
			y -= 2.6*l;
		}
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.D_RANGE.getAttributeID()){
					if (b.isMulti()) {
						y *= b.getValue();
					} else {
						y += b.getValue();
					}
				}
			}
		}
		if (y > 0) { return y; } else {return 0;} };

		StatModifierInterface minrangeBuilder = (s, d, w, l, x) -> 
		{double y = p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.MIN_ATT).getValue() + 1*s + 2*d - 0.5*l;
		if (p.Player_Profession.getClassName() == ProfessionName.WIZARD) {
			y += 0.5*l;
		}
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.MIN_ATT.getAttributeID()){
					if (b.isMulti()) {
						y *= b.getValue();
					} else {
						y += b.getValue();
					}
				}
			}
		} 
		if (y < 1) {
			return 1;
		} else {
		return y;
		}};
		
		StatModifierInterface charismaBuilder = (s, d, w, l, x) -> 
		{double y = p.Player_Attributes.getPlayerBaseAttributeByType(AttributeType.CHR).getValue() + 0.001*s + 0.0001*d + 0.002*w + 0.002*l;
		for(Object[] z: x){
			for (Object a: z) {
				AttributeBonus b = (AttributeBonus)a;
				if(b.getType().getAttributeID() == AttributeType.CHR.getAttributeID()){
					if (b.isMulti()) {
						y *= b.getValue();
					} else {
						y += b.getValue();
					}
				}
			}
		}if (y > 100) {
			return 100;
		} else {
		return y;
		}};
		
		//reallocation
		this.dodgeBuilder = dodgeBuilder;
		this.defBuilder = defBuilder;
		this.critBuilder = critBuilder;
		this.damagerangeBuilder = damagerangeBuilder;
		this.charismaBuilder = charismaBuilder;	
		this.minrangeBuilder = minrangeBuilder;
	}		

}
