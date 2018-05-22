package game;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.application.*;
import javafx.geometry.*;

import interfaces.*;
import player.*;
import items.*;


public class GameUIFX extends Application {
	
	//these help find the screen
	protected final static Rectangle2D MONITOR_BOUNDS = Screen.getPrimary().getVisualBounds();
	protected final static double MONITOR_SCREEN_WIDTH = MONITOR_BOUNDS.getWidth();
	protected final static double MONITOR_SCREEN_HEIGHT = MONITOR_BOUNDS.getHeight();
	
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		Stage root = new Stage();
		root.setTitle("Game"); // Set the stage title
		root.setScene(new Scene(new Button("Hello"))); // Place the scene in the stage
		root.show(); // Display the stage
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	/*
	//saved current_player
	protected static Player current_player = new Player("Null", ProfessionName.WARRIOR, 1);
	
	//attribute labels
	static Label displayname = new Label();
	static Label prof = new Label();
	static Label level = new Label();
	static Label exp = new Label();
	static Label hp = new Label();
	static Label mp = new Label();
	static Label str = new Label();
	static Label dex = new Label();
	static Label wis = new Label();
	static Label luk = new Label();
	static Label chr = new Label();
	static Label dodge = new Label();
	static Label d_range = new Label();
	static Label crit = new Label();
	static Label skill = new Label();
	
	//this should be invoked every time the current_player field is changed
	//this method updates all the Attribute Labels to the latest current_player stats
	private static void updateAllStatLabels() {
		displayname.setText("Name: " + current_player.getPlayer_name());
		prof.setText("Class: " + current_player.getPlayer_Profession().getClassName().name_cap);
		level.setText("Level: " + current_player.getLevel());
		exp.setText("Experience: "+ (int)current_player.getExp());
		hp.setText("Health: " + (int)current_player.getHealth() + "/" + 
				(int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.M_HP).getValue());
		mp.setText("Mana: " + (int)current_player.getMana() + "/" + 
				(int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.M_MP).getValue());
		str.setText("Strength: "+ (int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.STR).getValue());
		dex.setText("Dexterity: "+ (int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.DEX).getValue());
		wis.setText("Intelligence: "+ (int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.WIS).getValue());
		luk.setText("Luck: "+ (int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.LUK).getValue());
		chr.setText ("Charisma: " + (double)((int)(current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.CHR).getValue()*100000))/1000 + "%");
		dodge.setText ("Dodge: " + ((double)((int)(current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.DODGE).getValue()*10000)))/100 + "%");
		crit.setText("Critical Chance: "+ ((double)((int)(current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.CRIT).getValue()*10000)))/100 + "%");
		d_range.setText ("Damage: " + (int)current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.MIN_ATT).getValue() + " ~ " +
				(int)(current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.MIN_ATT).getValue() +
						current_player.getPlayer_Attributes().getPlayerAttributeByType(AttributeType.D_RANGE).getValue()));
		skill.setText("Skill Points Unused: "+ (int)current_player.getSkill_points_avail());
		
	}
	
	//main Game GUI (this is the only public part)
	public static void CreateGameUI() {
		
		
		//initialize a new main screen window
		Frame main_screen = new Frame("Game");
		main_screen.setVisible(true);
		
		//Closing the main window exits the program
		main_screen.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				System.exit(0);
			}
		});
		
		//Player Window adjustments
		Rectangle current_player_screen_rect = new Rectangle(MONITOR_SCREEN_WIDTH/8, MONITOR_SCREEN_HEIGHT/4, MAIN_SCREEN_WIDTH, MONITOR_SCREEN_HEIGHT/2);

		
		//Existing Game button: is disabled upon start and will only be enabled when a new current_player is saved 
		Button saved_game_button = new Button("Existing Game");
		saved_game_button.setEnabled(false);
		saved_game_button.setSize(MAIN_SCREEN_WIDTH/2, 3*MAIN_SCREEN_HEIGHT/4);
		
		//New Game button
		Button new_game_button = new Button("New Game");
		new_game_button.setSize(MAIN_SCREEN_WIDTH/2, 3*MAIN_SCREEN_HEIGHT/4);
		
		
		//This validates an on-click event for the Existing Game button 
		saved_game_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//this deactivates all buttons on click
				new_game_button.setEnabled(false);
				saved_game_button.setEnabled(false);
				updateAllStatLabels();
				//this creates a window for saved current_player with the label PLAYER_CLASS current_player_count
				Frame current_player_screen = makePlayerScreen(current_player_screen_rect, current_player.getPlayer_name());
				//this closes the current_player screen and reactivates new_game button on closing
				current_player_screen.addWindowListener( new WindowAdapter() {
					public void windowClosing(WindowEvent arg0) {
						current_player_screen.dispose();
						saved_game_button.setEnabled(true);
						new_game_button.setEnabled(true);
					}
				});
				//shows the current_player screen
				current_player_screen.setVisible(true);
				
			}
		});
		
	    //some examples

		//this validate an on-click event for New Game button
		new_game_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				//this deactivates all buttons on click
				new_game_button.setEnabled(false);
				saved_game_button.setEnabled(false);
				//this makes a new current_player, increments a current_player count and initiates the current_player window
				
				Frame newGame = new Frame("New Game?");
				int NewGamePosX = MAIN_SCREEN_POS_X + (MAIN_SCREEN_POS_X/16);
				int NewGamePosY = MAIN_SCREEN_POS_Y + (MAIN_SCREEN_POS_Y/8);
				int NewGameW = MAIN_SCREEN_WIDTH - (MAIN_SCREEN_POS_X/8);
				int NewGameH = 3*MAIN_SCREEN_HEIGHT/4;
				newGame.setBounds(NewGamePosX, NewGamePosY, NewGameW, NewGameH);

				newGame.setLayout(new GridLayout(3,0));

				Label intro = new Label("Choose your name: ");
				TextField nameField = new TextField(20);
				//ImageIcon img = new ImageIcon("")
				nameField.setText("Player 1");
				Panel name_choice = new Panel();
				name_choice.add(intro);
				name_choice.add(nameField);
				name_choice.add(new Label());
				
				Label class_intro = new Label("Choose your class:");
				Choice profession = new Choice();
				profession.add("Warrior");
				profession.add("Ranger");
				profession.add("Wizard");
				profession.add("Rogue");
				Panel profession_choices = new Panel();
				profession_choices.add(class_intro);
				profession_choices.add(profession);

				Button confirm = new Button("Confirm");
				confirm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						current_player = new Player(nameField.getText(), ProfessionName.findProfession(profession.getSelectedItem()), 1);
						
						//some random starting equipment
						EquipmentRandomizer e = new EquipmentRandomizer();
						current_player.getInventory().addItemToInventory(e.makeRandomEquipByType(1));
						current_player.getInventory().addItemToInventory(e.makeRandomEquipByType(2));
						current_player.getInventory().addItemToInventory(e.makeRandomEquipByType(3));
						current_player.getInventory().addItemToInventory(e.makeRandomEquipByType(4));
						
						updateAllStatLabels();
						Frame current_player_screen = makePlayerScreen(current_player_screen_rect, "Player");
						//this closes the current_player screen and reactivates new_game button on closing
						current_player_screen.addWindowListener( new WindowAdapter() {
							public void windowClosing(WindowEvent arg0) {
								current_player_screen.dispose();
								new_game_button.setEnabled(true);
								saved_game_button.setEnabled(true);
							}
						});
						//this displays the current_player
						current_player_screen.setVisible(true);
						newGame.dispose();
					}
				});
				Button cancel = new Button("Cancel");
				cancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						newGame.dispose();
						new_game_button.setEnabled(true);
						if (current_player.getPlayer_name() != "Null") {
							saved_game_button.setEnabled(true);
						}
					}
				});
				Panel isConfirm = new Panel();
				isConfirm.add(confirm);
				isConfirm.add(cancel);

				newGame.add(name_choice);
				newGame.add(profession_choices);
				newGame.add(isConfirm);
				newGame.setVisible(true);
				newGame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent arg0) {
						newGame.dispose();
						new_game_button.setEnabled(true);
						saved_game_button.setEnabled(true);
					}
				});
			}
		});
		
		//setting the Main Screen using grid layout
	    main_screen.setBounds(MAIN_SCREEN_POS_X, MAIN_SCREEN_POS_Y, MAIN_SCREEN_WIDTH, MAIN_SCREEN_HEIGHT);
	    main_screen.setLayout(new GridLayout(0,2));
	    main_screen.add(new_game_button);
	    main_screen.add(saved_game_button);
	    
		};
		
	//this snippet function makes a new Player Screen
	private static Frame makePlayerScreen(Rectangle current_player_screen_rect, String name) {
		
		Frame current_player_screen = new Frame(name);
		current_player_screen.setBounds(current_player_screen_rect);
		
		int current_player_screen_width = current_player_screen.getWidth();
		int current_player_screen_height = current_player_screen.getHeight();

		//the bottom action buttons panel
		Panel ps_bottom = new Panel();
		Button attack_button = new Button("HUNT!");
		Button inventory_button = new Button("Inventory");
		inventory_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				inventory_button.setEnabled(false);
				Frame inv = makeInventoryScreen(current_player_screen_rect, "Inventory");
				inv.setVisible(true);
				inv.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent arg0) {
						inv.dispose();
						inventory_button.setEnabled(true);
					}
				});
			}
		});
		ps_bottom.setPreferredSize(new Dimension(current_player_screen_width/2,  current_player_screen_height/5));
		ps_bottom.add(attack_button);
		ps_bottom.add(inventory_button);
		ps_bottom.setLayout(new GridLayout(1,0));
		current_player_screen.setLayout(new BorderLayout());
		
		//the top Introduction panel
		Panel ps_top = new Panel();
		ps_top.setLayout(new GridLayout(0, 1));
		Label current_player_name = new Label("Welcome, "+ current_player.getPlayer_name() + "!");
		current_player_name.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		current_player_name.setSize(current_player_screen_width, current_player_screen_height/5);
		current_player_name.setBackground(new Color(130, 200, 103));
		current_player_name.setAlignment(Label.CENTER);
		ps_top.add(current_player_name);
		
		//the side Statistics panel
		Panel ps_left = makeStatsPanel();
		
		//adding all panels
		current_player_screen.add(ps_bottom, BorderLayout.PAGE_END);
		current_player_screen.add(ps_top, BorderLayout.PAGE_START);
		current_player_screen.add(ps_left, BorderLayout.LINE_START);

		//this is an experimental panel
		Button levelup = new Button("Level Up!");
		levelup.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				GameUI.current_player.levelUp();
				updateAllStatLabels();

			}});
		current_player_screen.add(levelup, BorderLayout.LINE_END);

		
		return current_player_screen;
	};

	//this code snippet makes the Inventory screen (this is invoked in makePlayerScreen)
	private static Frame makeInventoryScreen(Rectangle inv_screen_rect, String name) {

		Frame inv_screen = new Frame(name);
		inv_screen_rect.setLocation(MONITOR_SCREEN_WIDTH - (inv_screen_rect.x + inv_screen_rect.width) , inv_screen_rect.y);
		inv_screen.setBounds(inv_screen_rect);


		Button[] inv = new Button[current_player.getInventory().MAX_AMOUNT];
		for (int i = 0; i < current_player.getInventory().MAX_AMOUNT; i++) {
			int j = i;
			inv[i] = new Button(current_player.getInventory().getIteminInventory(i).getItemName());
			inv[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Frame inventory_screen = makeInventoryInfoScreen(current_player.getInventory().getIteminInventory(j));
					inventory_screen.setVisible(true);
					inv[j].setEnabled(false);
					inventory_screen.addWindowListener(new WindowAdapter() {
						public void windowClosed(WindowEvent arg0) {
							inv[j].setEnabled(true);
							inventory_screen.dispose();
						}
						public void windowClosing(WindowEvent arg0) {
							inv[j].setEnabled(true);
							inventory_screen.dispose();
						}
						
					});
						
					}
				}
			);
			if (inv[i].getLabel() == "EMPTY") {
				inv[i].setEnabled(false);
			} else {
				inv[i].setEnabled(true);
			}
		}
		Panel ps_body = new Panel();
		ps_body.setLayout(new GridLayout(0,4));
		for (Button b: inv) {
			ps_body.add(b);
		}


		inv_screen.setLayout(new GridLayout(0, 1));
		inv_screen.add(ps_body);
		return inv_screen;
	};

	//this code snippet adds the Statistic panel to the current_player screen (this is invoked in makePlayerScreen)
	private static Panel makeStatsPanel() {
		Panel ps_left = new Panel();
		ps_left.setLayout(new GridLayout(0,1));
		ps_left.add(new Label("BASIC PLAYER STATS:"));
		ps_left.getComponent(0).setFont(new Font(Font.SERIF, Font.BOLD, 13));
		ps_left.add(displayname);
		ps_left.add(prof);
		ps_left.add(level);
		ps_left.add(d_range);
		ps_left.add(hp);
		ps_left.add(mp);
		ps_left.add(str);
		ps_left.add(dex);
		ps_left.add(wis);
		ps_left.add(luk);
		ps_left.add(chr);
		ps_left.add(crit);
		ps_left.add(dodge);
		ps_left.add(skill);
		for (int n = 0; n < ps_left.getComponentCount(); n++) {
			if (n%2 == 0) {
				ps_left.getComponent(n).setBackground(new Color(200, 200, 200));
			}
		}
		ps_left.setBackground(new Color(209, 200, 200));
		return ps_left;
	}
	
	private static Frame makeInventoryInfoScreen(Item i){
		Frame item_info = new Frame(i.getItemName());
		item_info.setLayout(new BorderLayout());
		item_info.setSize(MAIN_SCREEN_WIDTH, MAIN_SCREEN_HEIGHT);
		item_info.setLocation(MAIN_SCREEN_POS_X, MAIN_SCREEN_POS_Y + MAIN_SCREEN_HEIGHT + 30);

		Panel actions_panel = new Panel();
		actions_panel.setLayout(new GridLayout(1,2));
		Button action = new Button();
		if (i.getItemType() == ItemType.EQUIPMENT) {
			action.setLabel("Equip");
			action.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					item_info.dispose();
				}
			});
		} else {
			action.setLabel("Use");
			action.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					item_info.dispose();
				}
			});
		}
		actions_panel.add(action);
		Button cancel = new Button("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				item_info.dispose();
			}
		});
		actions_panel.add(cancel);

		Panel item_bonuses = GenerateItemBonusPanel((Equipment)i);
		Panel item_pic = new Panel();
		
		
		
		item_info.add(actions_panel, BorderLayout.PAGE_END);
		item_info.add(item_bonuses, BorderLayout.CENTER);
		return item_info;
	}
	
	private static Panel GenerateItemBonusPanel(Equipment e) {
		Panel n = new Panel();
		n.setLayout(new GridLayout(0,1));
		n.add(new Label(e.getItemName()));
		for (int i = 0; i < e.getAttributeBonus().length; i++) {
			double value = e.getAttributeBonus()[i].getValue();
			String percent = "";
			if (e.getAttributeBonus()[i].isMulti() ||
				e.getAttributeBonus()[i].getType() == AttributeType.DODGE ||
				e.getAttributeBonus()[i].getType() == AttributeType.CHR ||
				e.getAttributeBonus()[i].getType() == AttributeType.CRIT ) {
				percent = "%";
				value *= 100;
			}
			n.add(new Label(e.getAttributeBonus()[i].getType().getAttributeName() + ": +" +  (int)value +percent));
		}
		return n;
	}
	*/
}
