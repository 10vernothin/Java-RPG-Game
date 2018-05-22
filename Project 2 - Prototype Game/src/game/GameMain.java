package game;

import player.*;
import interfaces.*;
import items.*;
import monsters.*;

public class GameMain {

	public static void main(String[] args) {
		
		Player p1 = new Player("Player 1", ProfessionName.WIZARD, 7);
		p1.printBasicStats();
		p1.printDetailedAttributes();
		p1.levelUp();
		p1.printBasicStats();
		p1.printDetailedAttributes();
		EquipmentRandomizer equip_ran = new EquipmentRandomizer();
		Equipment equip = equip_ran.makeRandomEquipment();
		System.out.println(equip.getItemName());
		Monster m = new Monster(3);
		System.out.println(m.getMonsterName());
		System.out.println(m.getHealth());
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
	               GameUI.CreateGameUI();
	           }
	     });
	}
	
	

}
