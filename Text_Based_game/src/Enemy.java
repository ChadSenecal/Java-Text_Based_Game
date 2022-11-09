import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Enemy extends Entity{
	private int ID = 0, health = 0, armor = 0, damage = 0;
	private String nameOfEnemy = "";
	Connection c;
	public Enemy(Connection c) {
		this.c = c;
	}
	
	public void giveEnemyData(int ID2, String NameOfEnemy2, int health2, int armor2, int damage2) {
		try {
			Statement s = c.createStatement();
			s.execute("DELETE FROM Enemies WHERE enemyID = " + ID2);
			s.execute("INSERT INTO Enemies VALUES("+ ID2 + ", '" + NameOfEnemy2 + "', "+ health2 + ", "+armor2+", "+damage2+");");
			ID = ID2;
			nameOfEnemy = NameOfEnemy2;
			health = health2;
			armor = armor2;
			damage = damage2;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public String getNameOfEnemy() {
		return nameOfEnemy;
	}

	public void setNameOfEnemy(String nameOfEnemy) {
		this.nameOfEnemy = nameOfEnemy;
	}
	
}
