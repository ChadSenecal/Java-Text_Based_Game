import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Player extends Entity{
	private int ID = 0, helmetID = 15, armorID = 22, bootsID = 29, weaponID = 5, encounterID = 1, health = 10, strength = 0;
	private String name = "";
	private Connection c;
	private Scanner scan;
	public Player(Connection c, Scanner scan) {
		this.c = c;
		this.scan = scan;
	}
	
	public void newGamePlayer() {
		int yourID = 1;
		try {
		Statement s = c.createStatement();
		ResultSet rs = s.executeQuery("SELECT TOP 1 ID FROM Characters ORDER BY ID DESC");
		rs.next();
		yourID = rs.getInt(1) + 1;
		}catch(SQLException e) {
			System.out.println("Your First Character...");
		}
		System.out.println("Welcome to your new characer!!");
		String input = "";
		do {
		System.out.println("Type in your characters new name: ");
		name = scan.next();
		System.out.println("Are you sure you want your character to be called: " + name + "?");
		System.out.println("Type Y for yes and N for no...");
		input = scan.next();
		if(!input.equals("n") && !input.equals("N") && !input.equals("y") && !input.equals("Y")) {
			System.out.println("Okay, sending you to rename your character...");
		}
		}while(((input.equals("n") || input.equals("N")) || (!input.equals("n") || !input.equals("N"))) && (!input.equals("y") && !input.equals("Y")));
		updateCharacterInfo(yourID, name, helmetID, armorID, bootsID, weaponID, encounterID, health, strength);
		
		ID = yourID;
		passItemToInventory(getPlayerHelmetID());
		passItemToInventory(getWeaponID());
		passItemToInventory(getArmorID());
		passItemToInventory(getBootsID());
	}
	
	public void updateCharacterInfo(int ID2, String name2, int helmetID2, int armorID2, int bootsID2, int weaponID2, int encounterID2, int health2, int strength2) {
		try {
			Statement s = c.createStatement();
			s.execute("DELETE FROM Characters WHERE ID =" + ID2);
			s.execute("INSERT INTO Characters VALUES("+ ID2 + ", '" + name2 + "', "+ helmetID2 + ", "+armorID2+", "+bootsID2+", "+weaponID2+", "+encounterID2+", "+health2+", "+strength2+");");
			ID = ID2;
			name = name2;
			helmetID = helmetID2;
			armorID = armorID2;
			bootsID = bootsID2;
			weaponID = weaponID2;
			encounterID = encounterID2;
			health = health2;
			strength = strength2;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Item chooseCharacterItemToUse() {
		Item item = new Item();
		ArrayList<Integer> IDList = new ArrayList<Integer>();
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT COUNT(inventoryID) FROM Inventory WHERE inventoryID = "+getPlayerID()+";");
			rs.next();
			ResultSet rs2 = s.executeQuery("SELECT itemID FROM Inventory WHERE inventoryID = "+getPlayerID()+";");
			rs2.next();
			for(int i = 1; i < (rs.getInt(1)+1); i++) {
				ResultSet rs3 = s.executeQuery("SELECT itemName FROM Items WHERE ID = "+rs2.getInt(i)+";");
				rs3.next();
				IDList.add(rs2.getInt(i));
				System.out.println(i + ") "+rs3.getString(1)+"");
			}
			System.out.println("Select from your inventoryAbove:");
			int input = 0;
			do {
				input = scan.nextInt();
				if(input < 1 || input > rs.getInt(1)) {
					System.out.println("Wrong input...try again");
				}
			}while(input < 1 || input > rs.getInt(1));
			ResultSet rs4 = s.executeQuery("SELECT * FROM Items WHERE ID = "+IDList.get(input-1)+";");
			rs4.next();
			item.setID(rs4.getInt(1));
			item.setItemType(rs4.getString(2));
			item.setItemName(rs4.getString(3));
			item.setHealingAmount(rs4.getInt(4));
			item.setHealthIncrease(rs4.getInt(5));
			item.setStrengthIncrease(rs4.getInt(6));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return item;
	}
	
	public void passItemToInventory(int ID) {
		try {
			Statement s = c.createStatement();
			s.execute("INSERT INTO Inventory VALUES("+getPlayerID()+", "+ID+");");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getPlayerID() {
		return ID;
	}
	
	public String getPlayerName() {
		return name;
	}
	
	public int getPlayerHelmetID() {
		return helmetID;
	}
	
	public int getProtectionByHelmet() {
		int protection = 0;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT healthIncrease FROM Items WHERE ID = "+getPlayerHelmetID()+";");
			rs.next();
			protection = rs.getInt(1);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return protection;
	}
	
	public int getArmorID() {
		return armorID;
	}
	
	public int getProtectionByArmor() {
		int protection = 0;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT healthIncrease FROM Items WHERE ID = "+getArmorID()+";");
			rs.next();
			protection = rs.getInt(1);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return protection;
	}
	
	public int getBootsID() {
		return bootsID;
	}
	
	public int getProtectionByBoots() {
		int protection = 0;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT healthIncrease FROM Items WHERE ID = "+getBootsID()+";");
			rs.next();
			protection = rs.getInt(1);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return protection;
	}
	
	public int getWeaponID() {
		return weaponID;
	}
	
	public int damageByWeapon() {
		int damage = 0;
		try {
			Statement s = c.createStatement();
			ResultSet rs = s.executeQuery("SELECT strengthIncrease FROM Items WHERE ID = "+getWeaponID()+";");
			rs.next();
			damage = rs.getInt(1);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return damage;
	}
	
	public int getEncounterID() {
		return encounterID;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public void setPlayerHelemtID(int number) {
		helmetID = number;
	}
	
	public void setArmorID(int number) {
		armorID = number;
	}
	
	public void setHelmetID(int number) {
		helmetID = number;
	}
	
	public void setBootsID(int number) {
		bootsID = number;
	}
	
	public void setWeaponID(int number) {
		weaponID = number;
	}
	
	public void setEncounterID(int number) {
		encounterID = number;
	}
	
	public void setHealth(int number) {
		health = number;
	}
	
	public void setStrength(int number) {
		strength = number;
	}
	}
