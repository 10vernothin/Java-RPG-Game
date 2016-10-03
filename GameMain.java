package game;

import player.*;
import interfaces.*;
import items.*;

public class GameMain {

	public static void main(String[] args) {
		
		Player p1 = new Player("Player 1", ProfessionName.WARRIOR, 7);
		p1.printBasicStats();
		p1.printDetailedAttributes();
		p1.levelUp();
		p1.printBasicStats();
		p1.printDetailedAttributes();
		EquipmentRandomizer equip_ran = new EquipmentRandomizer();
		Equipment equip = equip_ran.makeRandomEquipment();
		System.out.println(equip.getItemName());
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	           public void run() {
	               GameUI.CreateGameUI();
	           }
	     });
	}
	
	

}
