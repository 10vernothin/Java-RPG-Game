package environment;

import player.*;
import monsters.*;
import interfaces.RandomModule;
import items.*;

public class BattleInterface {
	private Player p;
	private Monster m;
	private Item[] drops; 
	
	BattleInterface(Player p, Monster m) {
		this.p = p;
		this.m = m;
		int num_of_drops = RandomModule.RandomBetween(0, 2);
		Item[] i = new Item[num_of_drops];
		for (int n = 0; n < num_of_drops; n++) {
			EquipmentRandomizer e = new EquipmentRandomizer();
			i[n] = e.makeRandomEquipment();
		};
		this.drops = i;
	}
	
	public void attack() {
		
	}
	
	public void useItem() {
		
	}
	
	public Item[] getDropChoices() {
		return drops;
	}
	
	public Player end() {
		return p; 
	}
}
