import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Game {
	private Connection c;
	private Scanner scan;
	public Game(Connection c, Scanner scan) {
		this.c = c;
		this.scan = scan;
	}
	
	public void mainMenu() {
		System.out.println("WELCOME TO THE SKATE PARK BABY");
		System.out.println("Please choose what you want to do BABY!!:");
		int input = 0;
		do {
			System.out.println("1) Load your game");
			System.out.println("2) Start a new game");
			System.out.println("3) Exit Game");
			System.out.print("Your answer: ");
			input = scan.nextInt();
			if(input < 1 || input > 3) {
				System.out.println();
				System.out.println("Wrong input, try again...");
			}
		}while(input < 1 || input > 3);
		switch(input) {
		case 1:
			loadGame();
			break;
		case 2:
			startNewGame(new Entity());
			break;
		case 3:
			break;
		} 
	}
	
	public void loadGame() {
		int input = 0;
		boolean noData = false;
		ResultSet rs;
			try {
				Statement s;
				s = c.createStatement();
				rs = s.executeQuery("SELECT name FROM Characters;");
				if(rs.next() == true) {
					System.out.println("Select your characer: ");
					for(int i = 1; (i-1) < rs.getMetaData().getColumnCount(); i++) {
						System.out.println(i + ") " + rs.getString(i-1));
					}
					do{
						input = scan.nextInt();
						if(input < rs.getMetaData().getColumnCount() || input < 0) {
							System.out.println("incorrect input type... try again...");
						}
					}while(input < rs.getMetaData().getColumnCount() || input < 0);
					mainGame(getCharacter(input));
				}
				if(rs.next() == false) {
					noData = true;
					System.out.println("There were no charcters created yet...");
					int input1 = 0;
					do {
						System.out.println("Press 0 to exit load...");
						input1 = scan.nextInt();
					}while(input1 != 0);
				}else {
					mainGame(getCharacter(input));
				}
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			if(noData) {
				mainMenu();
			}
	}
	
	public void startNewGame(Entity guy) {
		mainGame(guy.createNewPlayer(c, scan));
	}
	
	public Player getCharacter(int number) {
		Player player = new Player(c, scan);
		//Based upon which number the character is in the character list, load that character into the game
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT * FROM Characters WHERE ID = " + number + ";");
			if(rs.next()) {
				player.updateCharacterInfo(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return player;
	}
	
	public void PlayerAndEnemyFight(Player man, Enemy monster) {
		System.out.println("you are currently fighting: " + monster.getNameOfEnemy() + "!");
		switch(monster.getID()) {
		case 1:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'let's smoke em boys'(literally *caught* *caugh*)");
			break;
		case 2:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'I'm the best, what bout you?'(bruh not again, he does this every week)");
			break;
		case 3:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'Gimme your lunch money kid!'(what's this man doing here?)");
			break;
		case 4:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'watever bro'(no careo)");
			break;
		case 5:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'The skatepark is not just for skateboarders you know...(typical soyjack moment)'");
			break;
		case 6:
			System.out.println("the " + monster.getNameOfEnemy() + " says: 'look at me go!(the worst people ever)'");
			break;
		}
		System.out.println("Choose your action: ");
		int input = 0;
		run:while(monster.getHealth() >= 0 && man.getHealth() >= 0) {
			do {
				System.out.println("1) attack");
				System.out.println("2) defend");
				System.out.println("3) useItem");
				System.out.println("4) Save");
				System.out.println("5) run");
				input = scan.nextInt();
				if(input < 1 || input > 5) {
					System.out.println("That was the wrong choice... try again");
				}
			}while(input < 1 || input > 5);
			
			switch(input) {
			case 1:
				System.out.println("You swung your board at the "+monster.getNameOfEnemy()+"...");
				int realDamage2 = man.damageByWeapon() + man.getStrength();
				monster.setHealth(monster.getHealth() - (realDamage2));
				System.out.println("You did "+realDamage2+" to the "+monster.getNameOfEnemy()+"...");
				System.out.println(monster.getNameOfEnemy() + " has " + monster.getHealth() + " health...");
				if(monster.getHealth() > 0) {
					System.out.println(monster.getNameOfEnemy() + "attacks back!");
					realDamage2 = monster.getDamage() - (man.getProtectionByArmor() + man.getProtectionByBoots() + man.getProtectionByHelmet());
					if(realDamage2 < 0) {
						realDamage2 = 0;
					}
					man.setHealth(man.getHealth() - realDamage2);
					System.out.println("The "+monster.getNameOfEnemy()+" did "+realDamage2+" to you...");
					System.out.println("You have "+man.getHealth()+" left...");
				}
				break;
			case 2:
				System.out.println("You defend against the monster...");
				int realDamage = monster.getDamage() - (man.getProtectionByArmor() + man.getProtectionByBoots() + man.getProtectionByHelmet() + 7);
				if(realDamage < 0) {
					realDamage = 0;
				}
				man.setHealth(man.getHealth() - realDamage);
				System.out.println("The "+monster.getNameOfEnemy()+" did "+realDamage+" to you...");
				System.out.println("You have "+man.getHealth()+" left...");
				break;
			case 3:
				Item chosenItem = man.chooseCharacterItemToUse();
				if(chosenItem.getItemType().equals("CONSUMABLE")) {
					man.setHealth(man.getHealth() + chosenItem.getHealingAmount());
					if(man.getHealth() > 10) {
						man.setHealth(10);
					}
					man.setHealth(man.getHealth() + chosenItem.getHealthIncrease());
					man.setStrength(man.getStrength() + chosenItem.getStrengthIncrease());
					System.out.println(man.getPlayerName() + "'s health is now: " + man.getHealth());
					System.out.println(man.getPlayerName() + "'s bare strength is now: " + man.getStrength());
				}else if(chosenItem.getItemType().equals("WEAPON")) {
					man.setWeaponID(chosenItem.getID());
					System.out.println("You have changed your board to: " + chosenItem.getItemName());
				}else if(chosenItem.getItemType().equals("ARMOR_HEAD")) {
					man.setHelmetID(chosenItem.getID());
					System.out.println("You have changed your hat to: " + chosenItem.getItemName());
				}else if(chosenItem.getItemType().equals("ARMOR_BODY")) {
					man.setArmorID(chosenItem.getID());
					System.out.println("You have changed your clothing to: " + chosenItem.getItemName());
				}else if(chosenItem.getItemType().equals("ARMOR_FEET")) {
					man.setBootsID(chosenItem.getID());
					System.out.println("You have changed your shoes to: " + chosenItem.getItemName());
				}else {
					System.out.println("UNKNOWN ITEM TYPE");
				}
				break;
			case 4:
				man.updateCharacterInfo(man.getPlayerID(), man.getPlayerName(), man.getPlayerHelmetID(), man.getArmorID(), man.getBootsID(), man.getWeaponID(), man.getEncounterID(), man.getHealth(), man.getStrength());
				monster.giveEnemyData(monster.getID(), monster.getNameOfEnemy(), monster.getHealth(), monster.getArmor(), monster.getDamage());
				break;
			case 5:
				man.updateCharacterInfo(man.getPlayerID(), man.getPlayerName(), man.getPlayerHelmetID(), man.getArmorID(), man.getBootsID(), man.getWeaponID(), 1, 10, 0);
				break run;
			}
		}
	}
	
	public Enemy encounterEnemy(Player man) {
		int enemyID = 0, monsterHealth = 0, monsterArmor = 0, monsterDamage = 0;
		String nameOfEnemy = "";
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT enemyID FROM Encounters WHERE encounterID = (SELECT encounterID FROM Characters WHERE ID = "+man.getPlayerID()+");");
			rs.next();
			enemyID = rs.getInt(1);
			ResultSet rs2 = s.executeQuery("SELECT * FROM Enemies WHERE enemyID = "+enemyID+";");
			rs2.next();
			nameOfEnemy = rs2.getString(2);
			monsterHealth = rs2.getInt(3);
			monsterArmor = rs2.getInt(4);
			monsterDamage = rs2.getInt(5);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		Enemy monster = new Enemy(c);
		monster.giveEnemyData(enemyID, nameOfEnemy, monsterHealth, monsterArmor, monsterDamage);
		return monster;
	}
	
	public boolean whatHappenedInEncounter(Player man, Enemy monster) {
		if(man.getHealth() <= 0) {
			return false;
		}else if(monster.getHealth() <= 0) {
			return true;
		}else {
			System.out.println("Welp... continue till the game resets you");
			return false;
		}
	}
	
	public void mainGame(Player man) {
		//This is the basically loop for the game to run through making it a little more dynamic, though it has some undynamic elements to it
		while(true) {
		Enemy monster = encounterEnemy(man);
		PlayerAndEnemyFight(man, monster);
		if(whatHappenedInEncounter(man, monster)) {
			System.out.println("You won the encounter!");
			System.out.println("Choose what type of drop you'll get!");
			int input = 0;
			do {
				System.out.println("1) CONSUMABLE ;)");
				System.out.println("2) BOARDS");
				System.out.println("3) HATS");
				System.out.println("4) CLOTHING");
				System.out.println("5) SHOES");
				if(input < 1 || input > 5) {
					System.out.println("wrong input, try again...");
				}
			}while(input < 1 || input > 5);
			Item temp = new Item();
			man.passItemToInventory(temp.generateRandomItemIDOfType(input));
			System.out.println("lets fight the next one!");
			man.setEncounterID(man.getEncounterID()+1);
		}else {
			System.out.println("YOU DIED...");
			man.updateCharacterInfo(man.getPlayerID(), man.getPlayerName(), man.getPlayerHelmetID(), man.getArmorID(), man.getBootsID(), man.getWeaponID(), 1, 10, 0);
			int input = 0;
			do {
				System.out.println("Press 1 to play again...");
				System.out.println("Press 2 to exit");
				input = scan.nextInt();
				if(input < 1 || input > 2) {
					System.out.println("Wrong input type...try again...");
				}
			}while(input < 1 || input > 2);
			if(input == 1) {
				mainGame(man);
			}else {
				mainMenu();
				break;
			}
		}
		}
	}
}
