import java.sql.Connection;
import java.util.Scanner;

public class Entity {
	public Player createNewPlayer(Connection c, Scanner scan) {
		Player man = new Player(c, scan);
		man.newGamePlayer();
		return man;
	}
}
