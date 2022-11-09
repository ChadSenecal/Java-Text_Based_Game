public class Item extends Entity{
	private int ID = 0, healingAmount = 0, healthIncrease = 0, strengthIncrease = 0;
	String itemType = "", itemName = "";
	
	public int generateRandomItemIDOfType(int type) {
		int ID = 0;
		int randomNum = (int)((Math.random() * 10) + 1);
		switch(type) {
		case 1:
			if(randomNum <= 5) {
				System.out.println("You got nothing... :(");
			}else if(randomNum == 6) {
				ID = 1;
				System.out.println("You got a cool consumable! Check your inventory in game to see what you got!");
			}else if(randomNum == 7) {
				ID = 2;
				System.out.println("You got a cool consumable! Check your inventory in game to see what you got!");
			}else if(randomNum == 8) {
				ID = 3;
				System.out.println("You got a cool consumable! Check your inventory in game to see what you got!");
			}else if(randomNum >= 9) {
				ID = 4;
				System.out.println("You got a cool consumable! Check your inventory in game to see what you got!");
			}
			break;
		case 2:
			if(randomNum <= 2) {
				System.out.println("You got nothing... :(");
			}else if(randomNum == 3) {
				ID = 5;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 4) {
				ID = 6;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 5) {
				ID = 7;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 6) {
				ID = 8;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 7) {
				ID = 9;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 8) {
				ID = 10;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 9) {
				ID = 11;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}else if(randomNum == 10) {
				ID = 12;
				System.out.println("You got a cool board! Check your inventory in game to see what you got!");
			}
			break;
		case 3:
			if(randomNum <= 1) {
				System.out.println("You got nothing... :(");
			}else if(randomNum == 2) {
				ID = 13;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 3) {
				ID = 14;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 4) {
				ID = 15;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 5) {
				ID = 16;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 6) {
				ID = 17;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 7) {
				ID = 18;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 8) {
				ID = 19;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 9) {
				ID = 20;
				System.out.println("You got a cool hat! Check your inventory in game to see what you got!");
			}else if(randomNum == 10) {
				ID = 21;
			}
			break;
		case 4:
			if(randomNum <= 3) {
				System.out.println("You got nothing... :(");
			}else if(randomNum == 4) {
				ID = 22;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 5) {
				ID = 23;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 6) {
				ID = 24;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 7) {
				ID = 25;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 8) {
				ID = 26;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 9) {
				ID = 27;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}else if(randomNum == 10) {
				ID = 28;
				System.out.println("You got a cool piece of clothing! Check your inventory in game to see what you got!");
			}
			break;
		case 5:
			if(randomNum <= 6) {
				System.out.println("You got nothing... :(");
			}else if(randomNum == 7) {
				ID = 29;
				System.out.println("You got some cool shoes! Check your inventory in game to see what you got!");
			}else if(randomNum == 8) {
				ID = 30;
				System.out.println("You got some cool shoes! Check your inventory in game to see what you got!");
			}else if(randomNum == 9) {
				ID = 31;
				System.out.println("You got some cool shoes! Check your inventory in game to see what you got!");
			}else if(randomNum == 10) {
				ID = 32;
				System.out.println("You got some cool shoes! Check your inventory in game to see what you got!");
			}
			break;
		}
		return ID;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itenName) {
		this.itemName = itenName;
	}

	public int getHealingAmount() {
		return healingAmount;
	}

	public void setHealingAmount(int healingAmount) {
		this.healingAmount = healingAmount;
	}

	public int getHealthIncrease() {
		return healthIncrease;
	}

	public void setHealthIncrease(int healthIncrease) {
		this.healthIncrease = healthIncrease;
	}

	public int getStrengthIncrease() {
		return strengthIncrease;
	}

	public void setStrengthIncrease(int strengthIncrease) {
		this.strengthIncrease = strengthIncrease;
	}
	
	
}
