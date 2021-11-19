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
	private int playerPosition[];
	private String map [] [];
	
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
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "C", "▫" },
			  { "▫", "V", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "G", "▫", "▫" },
			  { "L", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "▫" },
			  { "▫", "▫", "▫", "▫", "▫", "▫", "D" },
			};
		input = new Scanner(System.in);
		invList.add(inv1);
		invList.add(inv2);
		invList.add(inv3);
		invList.add(inv4);
		invList.add(inv5);
		playerPosition [0] = 0;
		playerPosition [1] = 0;
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
		
		int choice = input.nextInt();
		boolean correctInput = true;
		
		while (correctInput)
		{
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
				System.out.println("INVAALID INPUT, PLEASE CHOOSE FROM THE AVAILABLE OPTIONS");
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
		if (playerPosition[1] != 6)
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
	
	public void moveRight()
	{
		
	}

	public void moveLeft()
	{
		
	}
	
	public void moveUp()
	{
		
	}
	
	public void moveDown()
	{
		
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
		
		loot = lowItem.get(chance - 1);
		
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
		
		loot = lowItem.get(chance - 1);
		
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
		
		loot = highWeap.get(chance - 1);
		
		//giveItem(loot);
		
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
		
		//giveWeapon(loot);
		
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
		currentWeapon = newWeapon;
	}
	
	public void giveItem(String ItemName, String description, int stats)
	{
		Item newItem = new Item();
		boolean isFull = true;
		int i = 0;
		newItem.setStats(stats);
		newItem.setName(ItemName);
		newItem.setDescription(description);
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
		Item newItem = givenItem;
		boolean isFull = true;
		int i = 0;
		
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
	
	public boolean fight(String monsterName, int monsterHealth, int monsterAttack, boolean monstBig)
	{
		boolean fight = false;
		double critChance = currentWeapon.getCrit();
		boolean actionTaken;
		Monster monster = new Monster(monsterName, monsterHealth, monsterAttack, monstBig);
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
			if(monstBig)
			{
				bigLoot();
			}
			else
			{
				smallLoot();
			}
		}
		else
		{
			System.out.println("YOU HAVE DIED!");
		}
		return fight;
	}
	
}