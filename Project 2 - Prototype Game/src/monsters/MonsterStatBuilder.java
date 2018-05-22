package monsters;

import interfaces.*;
import player.*;

class MonsterStatBuilder {

	//this interface is used to build Monster stats

	private Monster m;

	//implementation
	
	private AttributeBonus[] extractandBuildAttributes() {
		AttributeBonus[] att = {};
		if (m.getMonsterModifiers() != null) {
		for (MonsterTable.Modifier mo: m.getMonsterModifiers()) {
			for (AttributeType mot : mo.modifier_type) {
				AttributeBonusGenerator b = new AttributeBonusGenerator();
				AttributeBonus att_n = b.generateAttributeBonus(mot, m.monster_level);
				AttributeBonus[] att_temp = new AttributeBonus[att.length+1];
				for (int i = 0; i < att.length; i++) {
					att_temp[i] = att[i];
				}
				att_temp[att_temp.length -1] = att_n;
				att = att_temp;
				}	
			}
		}
		return att;
			
	}

	protected void buildAttributeBonuses() {
		//this function makes it so that as long as the argument is a list of AttributeBonus objects, the primary stats of PlayerAttribute will be mutated
		final class Helper {
			void helperF(AttributeBonus[] o) {
				for (PlayerAttributes.PlayerAttribute a: m.monster_attributes.getListView()) {
					for (AttributeBonus b: o){
							if (b.isMulti()) {
								a.multiplytoValue(b.getValue());
								
							} else {
								a.addtoValue(b.getValue());
							}
					}
				}
			}
		}
		Helper h = new Helper();

		h.helperF(extractandBuildAttributes());
	}

	//interface function

	//constructor
	protected MonsterStatBuilder(Monster m) {
		this.m = m;
	}		
}
