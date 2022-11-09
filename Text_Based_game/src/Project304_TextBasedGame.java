import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Project304_TextBasedGame {
	private static Connection c;
	public static void main(String[] args) {
		connectToDatabase("jdbc:sqlserver://localhost;databaseName=Text_Game;integratedSecurity=true");
		//comment below for debug purposes
		if(tablesMade()) {
			createAllTables(new Scanner(System.in));
		}
		Game ourGame = new Game(c, new Scanner(System.in));
		ourGame.mainMenu();
	}
	
	public static void connectToDatabase(String login) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			c = DriverManager.getConnection(login);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static boolean tablesMade() {
		ResultSet rs;
		boolean tablesCreated = true;
		try {
			Statement state = c.createStatement();
			rs = state.executeQuery("SELECT * FROM SYS.TABLES;");
			if(rs.next()) {
				tablesCreated = false;
				System.out.println("All the tables were already created");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return tablesCreated;
	}
	
	public static void createAllTables(Scanner scan) {
		try {
			String SQLCode = "";
			Statement s = c.createStatement();
			//Used for debugging
			//s.execute("DROP TABLE IF EXISTS Characters; DROP TABLE IF EXISTS Inventory; DROP TABLE IF EXISTS Items; DROP TABLE IF EXISTS Encounters; DROP TABLE IF EXISTS Enemies;");
			File Characters = new File("Characters.txt");
			File Encounters = new File("Encounters.txt");
			File Enemies = new File("Enemies.txt");
			File Inventory = new File("Inventory.txt");
			File Items = new File("Items.txt");
			try {
				scan = new Scanner(Enemies);
				while(scan.hasNext()) {
					SQLCode += scan.nextLine();
				}
				s.execute(SQLCode);
				SQLCode = "";
				scan = new Scanner(Encounters);
				while(scan.hasNext()) {
					SQLCode += scan.nextLine();
				}
				s.execute(SQLCode);
				SQLCode = "";
				scan = new Scanner(Items);
				while(scan.hasNext()) {
					SQLCode += scan.nextLine();
				}
				s.execute(SQLCode);
				SQLCode = "";
				scan = new Scanner(Inventory);
				while(scan.hasNext()) {
					SQLCode += scan.nextLine();
				}
				s.execute(SQLCode);
				SQLCode = "";
				scan = new Scanner(Characters);
				while(scan.hasNext()) {
					SQLCode += scan.nextLine();
				}
				s.execute(SQLCode);
				SQLCode = "";
				scan = new Scanner(System.in);
			} catch (FileNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();;
		}
	}
}
