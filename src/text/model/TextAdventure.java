package text.model;

import java.util.*;

import text.model.Monster;
import text.model.Item;
import text.model.Weapon;
import java.lang.Math;
import java.util.concurrent.TimeUnit;

public class TextAdventure 
{
	private Weapon currentWeapon;
	private Item inv1;
	private Item inv2;
	private Item inv3;
	private Item inv4;
	private Item inv5;
	private int playerhealth;
	private Scanner input;
	private Thread thread;
	private ArrayList <Item> invList;
	private ArrayList <Item> lowItem;
	private ArrayList <Item> highItem;
	private ArrayList <Weapon> highWeap;
	private ArrayList <Weapon> lowWeap;
	private ArrayList <Monster> monstList;
	private int playerPosition[];
	private String map [] [];
	private boolean gameRunning = true; 
	
	public TextAdventure() 
	{
		this.currentWeapon = new Weapon();
		this.inv1 = new Item();
		this.inv2 = new Item();
		this.inv3 = new Item();
		this.inv4 = new Item();
		this.inv5 = new Item();
		this.playerhealth = 100;
		this.invList = new ArrayList <Item>();
		this.playerPosition = new int[2];
		this.map = new String[][]{
			  { "●", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "▫", "D" },
			};
		input = new Scanner(System.in);
		invList.add(inv1);
		invList.add(inv2);
		invList.add(inv3);
		invList.add(inv4);
		invList.add(inv5);
		playerPosition [0] = 0;
		playerPosition [1] = 0;
		monstList = new ArrayList<Monster> ();
		lowItem = new ArrayList<Item> ();
		highItem = new ArrayList<Item> ();
		lowWeap = new ArrayList<Weapon> ();
		highWeap = new ArrayList<Weapon> ();
		this.gameRunning = gameRunning;
	}
	
	public void monsterLoot(boolean isBig)
	{
		boolean isWeapon = false;
		
		if (gameRandom(3) == 0)
		{
			isWeapon = true;
		}
		
		if (isBig && isWeapon);
		{
			bigWeapon();
		}
		if (isBig)
		{
			bigLoot();	
		}
		else if (isWeapon)
		{
			smallWeapon();
		}
		else
		{
			smallLoot();
		}
	}
	
	public boolean showInventory()
	{
		Item _inv1 = invList.get(0);
		Item _inv2 = invList.get(1);
		Item _inv3 = invList.get(2);
		Item _inv4 = invList.get(3);
		Item _inv5 = invList.get(4);
		System.out.println("\n[Inventory]");
		System.out.println("{0} " + currentWeapon.getName() + "\n" +"{1} " + _inv1.getName() + " {2} " + _inv2.getName() + " {3} " + _inv3.getName() + "\n{4} " + _inv4.getName() + " {5} " + _inv5.getName() + " {6} GO BACK");
		
		int invNum = input.nextInt();
		boolean goBack = true;
		boolean correctInput = false;
		while (correctInput == false)
		{
			if (invNum <= 5 && invNum >= 1)
			{
				Item item = invList.get(invNum - 1);
				System.out.println(item.toString());
				correctInput = true;
			}
			else if (invNum == 6)
			{
				goBack = false;
				correctInput = true;
				return goBack;
			}
			else if (invNum == 0)
			{
				System.out.println(currentWeapon.toString());
				correctInput = true;
			}
			else
			{
				System.out.println("Invalid input, please type the number assosiated with the action");
				invNum = input.nextInt();
			}
		}
		return goBack;
	}
	
	public void showMap()
	{
		for(int col=0; col<map[0].length; col++)
		{
		   for(int row=0; row<map.length; row++)
		   {
			  System.out.print(map[row] [col]);
			  System.out.print(" ");
		   }
		   System.out.println();
		}
	}
	
	public void playerMove()
	{
		showMap();
		System.out.println(playerPosition[0] + ", " + playerPosition[1]);
		System.out.println("[MOVEMENT]");
		if (canMoveUp())
		{
			System.out.print("{1} ↑ ");
		}
		if (canMoveDown())
		{
			System.out.print("{2} ↓ ");
		}
		if (canMoveLeft())
		{
			System.out.print("{3} ← ");
		}
		if (canMoveRight())
		{
			System.out.print("{4} → ");
		}
		
		System.out.println("[ACTIONS]");
		System.out.println("{5} show inv  {6} camp");
		
		boolean correctInput = true;
		
		while (correctInput)
		{
			int choice = input.nextInt();
			
			if (choice == 1 && canMoveUp())
			{
				moveUp();
				correctInput = false;
			}
			else if (choice == 2 && canMoveDown())
			{
				moveDown();
				correctInput = false;
			}
			else if (choice == 3 && canMoveLeft())
			{
				moveLeft();
				correctInput = false;
			}
			else if (choice == 4 && canMoveRight())
			{
				moveRight();
				correctInput = false;
			}
			else if (choice == 5)
			{
				showInventory();
				correctInput = false;
				
			}
			else if(choice == 6)
			{
				playerCamp();
				correctInput = false;
			}
			else
			{
				System.out.println("INVALID INPUT, PLEASE CHOOSE FROM THE AVAILABLE OPTIONS");
			}
		}
	}
	
	public void playerCamp() 
	{
		playerhealth = 100;
	}
	
	public boolean canMoveUp()
	{
		
		if (playerPosition[1] != 0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canMoveDown()
	{	
		if (playerPosition[1] != 9)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canMoveLeft()
	{		
		if (playerPosition[0] != 0)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean canMoveRight()
	{
		if (playerPosition[0] != 9)
		{
			return true;
		}
		
		return false;
	}
	
	public void moveDown()
	{
		
		int x = playerPosition[0];
		int y = playerPosition[1];
		
		if (map[x][y].equals("●"))
		{
			map[x] [y] = "▫";
		}
				
		y = playerPosition[1] + 1;
		
		if (map[x][y].equals("▫"))
		{
			map[x] [y] = "●";
		}
		
		playerPosition[1] = y;
		playerPosition[0] = x;
	}

	public void moveLeft()
	{
		int x = playerPosition[0];
		int y = playerPosition[1];
		
		if (map[x][y].equals("●"))
		{
			map[x] [y] = "▫";
		}
				
		x = x - 1;
		
		if (map[x][y].equals("▫"))
		{
			map[x] [y] = "●";
		}
		
		playerPosition[1] = y;
		playerPosition[0] = x;
	}
	
	public void moveUp()
	{
		int x = playerPosition[0];
		int y = playerPosition[1];
		
		if (map[x][y].equals("●"))
		{
			map[x] [y] = "▫";
		}
				
		y = playerPosition[1] - 1;
		
		if (map[x][y].equals("▫"))
		{
			map[x] [y] = "●";
		}
		
		playerPosition[1] = y;
		playerPosition[0] = x;
	}
	
	public void moveRight()
	{
		int x = playerPosition[0];
		int y = playerPosition[1];
		
		if (map[x][y].equals("●"))
		{
			map[x] [y] = "▫";
		}
				
		x = x + 1;
		
		if (map[x][y].equals("▫"))
		{
			map[x][y] = "●";
		}
		
		playerPosition[1] = y;
		playerPosition[0] = x;;
	}
	
	public int gameRandom(int chance)
	{
		int r = (int) (Math.random() * (100 - 1)) + 1;
		r = r % chance;
		return r;
	}
	
	public void smallLoot()
	{
		Item loot;
		
		Item apple = new Item("APPLE", "Shiny Red Apple", 10);
		Item bread = new Item("BREAD", "Loaf of slightly stale bread", 10);
		Item cheese = new Item("CHEESE", "Chunck of yellow cheese", 15);
		
		lowItem.add(apple);
		lowItem.add(bread);
		lowItem.add(cheese);
		
		int chance = gameRandom(3);
		
		loot = lowItem.get(chance);
		
		giveItem(loot);
	}
	
	public void bigLoot()
	{
		Item loot;
		
		Item weakPotion = new Item("WEAK POTION", "Small vial filled with glowing red liquid", 30);
		Item goodPotion = new Item("GOOD POTION", "Small vial filled with glowing green liquid", 60);
		Item strongPotion = new Item("STRONG POTION", "Small vial filled with glowing blue liquid", 100);
		
		highItem.add(weakPotion);
		highItem.add(goodPotion);
		highItem.add(strongPotion);
		
		int chance = gameRandom(3);
		
		loot = highItem.get(chance);
		
		giveItem(loot);
		
	}
	
	public void bigWeapon()
	{
		Weapon loot;
		
		Weapon titanium = new Weapon("TITANIUM BLADE", "Strongest sword made by man", 50,12.0);
		Weapon fiery = new Weapon("FIRERY SWORD", "Found in the depths of the hotest volcano", 60, 12.0);
		Weapon damned = new Weapon("SWORD OF THE DAMNED", "Forged in the depths of hell, this sword sucks the soul out of its victims", 70, 12.0);
		
		highWeap.add(titanium);
		
		highWeap.add(fiery);
		highWeap.add(damned);
		
		int chance = gameRandom(3);
		
		loot = highWeap.get(chance);
		
		giveWeapon(loot);
		
	}
	
	public void smallWeapon()
	{
		Weapon loot;
		
		Weapon club = new Weapon("CLUB", "Stick, but big", 17, 12.0);
		Weapon rustKnife = new Weapon("RUSTY KNIFE", "Pairs well with a tetanus shot", 20, 12.0);
		Weapon brokeSword = new Weapon("BROKEN SWORD", "This piece of steel had a hard life", 23, 12.0);
		
		lowWeap.add(club);
		lowWeap.add(rustKnife);
		lowWeap.add(brokeSword);
		
		int chance = gameRandom(3);
		
		loot = lowWeap.get(chance - 1);
		
		giveWeapon(loot);
		
	}
	
	public boolean useInv()
	{
		Item _inv1 = invList.get(0);
		Item _inv2 = invList.get(1);
		Item _inv3 = invList.get(2);
		Item _inv4 = invList.get(3);
		Item _inv5 = invList.get(4);
		System.out.println("\n[Inventory]");
		System.out.println(currentWeapon.getName() + "\n" +"{1} " + _inv1.getName() + " {2} " + _inv2.getName() + " {3} " + _inv3.getName() + "\n{4} " + _inv4.getName() + " {5} " + _inv5.getName() + " {6} GO BACK");
		
		int invNum = input.nextInt();
		boolean goBack = false;
		boolean correctInput = false;
		
		while (correctInput == false)
		{
			if (invNum <= 5 && invNum >= 1)
			{
				Item nothing = new Item();
				Item item = invList.get(invNum - 1);
				System.out.println(item.getName() + ": heals " + item.getStats() + " health");
				playerhealth = playerhealth + item.getStats();
				if (playerhealth > 100)
				{
					playerhealth = 100;
				}
				invList.set(invNum - 1, nothing);
				correctInput = true;
				goBack = true;
			}
			else if (invNum == 6)
			{
				correctInput = true;
				return goBack;
			}
			else if (invNum != 6)
			{
				System.out.println("Invalid input, please type the number assosiated with the action");
				invNum = input.nextInt();
			}
		}
		return goBack;
	}
	
	public void giveWeapon(String ItemName, String description, int stats, double crit)
	{
		currentWeapon.setStats(stats);
		currentWeapon.setName(ItemName);
		currentWeapon.setCrit(crit);
		currentWeapon.setDescription(description);
	}
	
	public void giveWeapon(Weapon newWeapon)
	{
		boolean correctInput = true;
		System.out.println();
		System.out.println("DO YOU WISH TO REPLACE YOUR CURRENT WEAPON?");
		System.out.println("CURRENT WEAPON: " + currentWeapon.toString());
		System.out.println();
		System.out.println("NEW WEAPON: " + newWeapon.toString());
		System.out.println("{1} YES  {2} NO");
		while (correctInput)
		{
			int choice = input.nextInt();
			if (choice == 1)
			{
				System.out.println("USING NEW WEAPON");
				currentWeapon = newWeapon;
				correctInput = false;
			}
			else if (choice == 2)
			{
				System.out.println("KEEPING CURRENT WEAPON");
				correctInput = false;
			}
			else
			{
				System.out.println("INVALID INPUT PLEASE TRY AGAIN");
			}
		}
		System.out.println();
	}
	
	public void giveItem(String ItemName, String description, int stats)
	{
		Item newItem = new Item();
		boolean isFull = true;
		int i = 0;
		newItem.setStats(stats);
		newItem.setName(ItemName);
		newItem.setDescription(description);
		System.out.println("New Item: " + newItem.toString());
		while (i < 6)
		{
			Item currentItem = invList.get(i);
			if (currentItem.getName().equalsIgnoreCase("nothing"))
			{
				invList.set(i, newItem);
				isFull = false;
				i = i + 10;
			}
			i++;
			
		}
		if (isFull == true)
		{
			System.out.println("INVENTORY FULL");
		}
	}
	
	public void giveItem(Item givenItem)
	{
		Item _inv1 = invList.get(0);
		Item _inv2 = invList.get(1);
		Item _inv3 = invList.get(2);
		Item _inv4 = invList.get(3);
		Item _inv5 = invList.get(4);
		Item newItem = givenItem;
		boolean isFull = true;
		int i = 0;
		
		System.out.println("New Item: " + newItem.toString());
		
		while (i < 5)
		{
			Item currentItem = invList.get(i);
			if (currentItem.getName().equalsIgnoreCase("nothing"))
			{
				invList.set(i, newItem);
				isFull = false;
				i = i + 10;
			}
			i++;
			
		}
		if (isFull == true)
		{
			System.out.println("ERROR INVENTORY FULL, PLEASE SELECT AN ITEM TO REPLACE");
			System.out.println("\n[Inventory]");
			System.out.println("{1} " + _inv1.getName() + " {2} " + _inv2.getName() + " {3} " + _inv3.getName() + "\n{4} " + _inv4.getName() + " {5} " + _inv5.getName() + " {6} DROP NEW ITEM");
			
			int invNum = input.nextInt();
			boolean goBack = true;
			boolean correctInput = false;
			while (correctInput == false)
			{
				if (invNum <= 5 && invNum >= 1)
				{
					invNum = invNum -1;
					invList.set(invNum, newItem);
					correctInput = true;
				}
				else if (invNum == 6)
				{
					goBack = false;
					System.out.println("DROPPING NEW ITEM");
					correctInput = true;
				}
				else
				{
					System.out.println("Invalid input, please type the number assosiated with the action");
					invNum = input.nextInt();
				}
			
		
			}
		}
	}
	
	public boolean didCrit(double critChance)
	{
		boolean hasCrit = false;
		int r = (int) (Math.random() * (100 - 1)) + 1;
		if (r % critChance == 0)
		{
			return true;
		}
		
		return hasCrit;
	}
	
	public int playerAttack(String monsterName)
	{
		int weapon = currentWeapon.getStats();
		double critChance = currentWeapon.getCrit();
		int attack;
		if (didCrit(critChance))
		{
			System.out.println("YOU LAND A CRITICAL HIT AGAINST " + monsterName);
			attack = (int) (weapon * 1.5);
		}
		else
		{
			attack = (int) (weapon + Math.random() + 0.3);
		}
		return attack;
	}
	
	public int monsterAttack(String monsterName, int monsterAttack)
	{
		int monsterCrit = 3;
		int monstAttack;
		if (didCrit(monsterCrit))
		{
			System.out.println(monsterName + " LANDS A CRITICAL HIT AGAINST YOU");
			monstAttack = (int) (monsterAttack * 1.5);
		}
		else
		{
			monstAttack = (int) (monsterAttack + Math.random() + 0.3);
		}
		return monstAttack;
	}
	
	public void fight(Monster monst)
	{
		double critChance = currentWeapon.getCrit();
		boolean actionTaken;
		Monster monster = monst;
		String monsterName = monster.getName();
		int monsterAttack = monster.getattack();
		int monsterHealth = monster.gethealth();
		boolean monstBig = monster.getIsBig();
		System.out.println(monsterName + " Goes in to fight!");
		System.out.println("Your health: " + playerhealth);
		System.out.println(monsterName + " health: " + monsterHealth);
		
		while (playerhealth > 0 && monsterHealth > 0)
		{
			System.out.println("   1          2           3    ");
			System.out.println("{attack}  {use inv}  {show inv}");
			int action = input.nextInt();
			actionTaken = false;
			if (action == 1)
			{
				int playAttack = playerAttack(monsterName);
				System.out.println("You attack " + monsterName + " for " + playAttack + " damage!");
				monsterHealth = monsterHealth - playAttack;
				
				actionTaken = true;
			}	
			else if(action == 2)
			{
				actionTaken = useInv();
			}
			else if(action == 3)
			{
				showInventory();
			}
				//TimeUnit.SECONDS.sleep(1);
			if (actionTaken == true)
			{
				if (monsterHealth > 0)
				{
					int monstAttack = monsterAttack(monsterName, monsterAttack);
					System.out.println("You are hit for " + monstAttack + " damage!");
					playerhealth = playerhealth - monstAttack;
				}
				System.out.println("Your health: " + playerhealth);
				System.out.println(monsterName + " health: " + monsterHealth);
			}
		}
		
		if (monsterHealth < 0)
		{
			System.out.println(monsterName + " DIES");
			monsterLoot(monstBig);
		}
		else
		{
			System.out.println("YOU HAVE DIED!");
		}
	}
	
	public void randEvent()
	{
		
		int random = gameRandom(5);
		Monster golbinWeak = new Monster("GOBLIN WEAKLING", 1, 1, false);
		Monster golbinStrong = new Monster("GOLBLIN STRONG MAN", 40, 40, true);
		Monster goblinElder = new Monster("GOBLIN ELDER", 50, 23, true);
		Monster goblin = new Monster("GOBLIN", 20, 10, false);
		Monster assassin = new Monster("ASSASSIN", 20, 75, true);
		Monster bandit = new Monster("BANDIT", 15, 15, false);
		Monster dragCultistRecruit = new Monster("DRAGON CULTIST RECRUIT", 20, 10, false);
		Monster dragCulistStrong = new Monster("DRAGON CULTIST TANK", 70, 10, true);
		Monster dragCultistElder = new Monster("DRAGON CULIST ELDER", 45, 30, true);
		monstList.add(golbinWeak);
		monstList.add(golbinStrong);
		monstList.add(goblinElder);
		monstList.add(goblin);
		monstList.add(assassin);
		monstList.add(bandit);
		monstList.add(dragCultistRecruit);
		monstList.add(dragCulistStrong);
		monstList.add(dragCultistElder);
		int rand = gameRandom(9);
		if (random > 3)
		{
			fight(monstList.get(rand));	
		}
	}

	public void dragLair()
	{
		System.out.println("You enter the Dragons Lair, thick slab walls cover the sides, and a boiling lava pit lays before you");
		System.out.println("You look up to see that there are 4 floors that you have to go to to face the Dragon King");
		System.out.println("As you walk in a guard comes into view");
		Monster guard = new Monster("DRAGON GUARD", 50, 30, true);
		fight(guard);
		fight(guard);
		System.out.println("You ascend the staircase and search for the dragon lord, but are interupted by the top cultists");
		Monster highCult = new Monster("DRAGON CULTEST COMMANDER", 100, 50, true);
		fight(highCult);
		fight(highCult);
		fight(highCult);
		fight(highCult);
		System.out.println("As you ascend to the final stage, you see the leader, he looks very weak and fragile for all he has done");
		Monster boss = new Monster("DRAGON CULTIST CHEIF", 1, 1, true);
		fight(boss);
	}

	public boolean getIsRunning()
	{
		return this.gameRunning;
	}

	public void significantPlace()
	{
		if (playerPosition[1] == 9 && playerPosition[0] == 9)
		{
			dragLair();
		}
	}

}