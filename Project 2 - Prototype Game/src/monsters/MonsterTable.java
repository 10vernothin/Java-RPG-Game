package monsters;

import interfaces.*;

public class MonsterTable {
	public enum Modifier {
		GILDED(1, "Gilded "),
		AGILE(2, "Agile ",AttributeType.DODGE),
		LUCKY(3, "Lucky ", AttributeType.CRIT),
		LARGE(4, "Large ", AttributeType.M_HP),
		WILLFUL(5, "Willful ", AttributeType.DEFENCE),
		RECKLESS(6, "Reckless ", AttributeType.D_RANGE),
		STRONG(7, "Strong ", AttributeType.MIN_ATT, AttributeType.D_RANGE),
		ANGRY(8, "Angry ", AttributeType.CRIT, AttributeType.D_RANGE),
		POWERFUL(9, "Powerful ", AttributeType.MIN_ATT, AttributeType.CRIT, AttributeType.D_RANGE, AttributeType.M_HP, AttributeType.DODGE, AttributeType.DEFENCE),
		NONE(0, "");
		String name;
		int id;
		AttributeType[] modifier_type;
		Modifier(int id, String name, AttributeType... t) {
			this.name= name;
			this.id = id;
			this.modifier_type = t;
		}
		
		//fetcher
		public static Modifier getModifierByID(int n) {
			for (Modifier m : Modifier.values()) {
				if (m.id == n) {
					return m;
				}
			}
			return NONE;
		}
		
	}
	
	public enum MonsterType {
		CHEST(0, "Treasure Chest"),
		WARRIOR(10, "Warrior"),
		BEAR(20, "Bear"),
		SERPENT(30, "Serpent"),
		GIANT(40, "Giant"),
		GREMLIN(50, "Gremlin"),
		DINOSAUR(60, "Dinosaur"),
		LIZARD(70, "Komodo Dragon"),
		SNOWMAN(80, "Abominable Snowman"),
		LOL(90, "Lollipop Monster"),
		I_JACK(95, "Infernal Jack"),
		L_QUEEN(96, "Lady Unlucky"),
		W_KING(97,"Warrior King"),
		GOOSIFER(98, "Goosifer"),
		GOOSIFER_REX(99, "Goosifer Rex"),
		NONE(-1, "None");
		String name;
		int id;
		AttributeType[] modifier_type;
		MonsterType(int id, String name) {
			this.name= name;
			this.id = id;
		}
		
		//fetcher
		public static MonsterType getMonsterTypeByID(int id) {
			for (MonsterType m : MonsterType.values()) {
				if (m.id == id) {
					return m;
				}
			}
			return NONE;
		}
	}
	
}
