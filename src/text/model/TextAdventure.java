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
	private Weapon newWeapon;
	private Item inv1;
	private Item inv2;
	private Item inv3;
	private Item inv4;
	private Item inv5;
	private int playerhealth;
	private Scanner input;
	private Thread thread;
	private ArrayList <Item> invList;
	
	public TextAdventure() 
	{
		this.currentWeapon = new Weapon();
		this.newWeapon = new Weapon();
		this.inv1 = new Item();
		this.inv2 = new Item();
		this.inv3 = new Item();
		this.inv4 = new Item();
		this.inv5 = new Item();
		this.playerhealth = 100;
		this.invList = new ArrayList <Item>();
		input = new Scanner(System.in);
		invList.add(inv1);
		invList.add(inv2);
		invList.add(inv3);
		invList.add(inv4);
		invList.add(inv5);
	}
	
	public boolean showInventory()
	{
		Item _inv1 = invList.get(0);
		Item _inv2 = invList.get(1);
		Item _inv3 = invList.get(2);
		Item _inv4 = invList.get(3);
		Item _inv5 = invList.get(4);
		System.out.println(currentWeapon.getName() + "\n" +"{1} " + _inv1.getName() + " {2} " + _inv2.getName() + " {3} " + _inv3.getName() + "\n{4} " + _inv4.getName() + " {5} " + _inv5.getName() + " {6} GO BACK");
		
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
			else if (invNum != 6)
			{
				System.out.println("Invalid input, please type the number assosiated with the action");
				invNum = input.nextInt();
			}
		}
		return goBack;
	}
	
	public boolean useInv()
	{
		Item _inv1 = invList.get(0);
		Item _inv2 = invList.get(1);
		Item _inv3 = invList.get(2);
		Item _inv4 = invList.get(3);
		Item _inv5 = invList.get(4);
		System.out.println(currentWeapon.getName() + "\n" +"{1} " + _inv1.getName() + " {2} " + _inv2.getName() + " {3} " + _inv3.getName() + "\n{4} " + _inv4.getName() + " {5} " + _inv5.getName() + " {6} GO BACK");
		
		int invNum = input.nextInt();
		boolean goBack = true;
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
			}
			else if (invNum == 6)
			{
				goBack = false;
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
	
	public void giveWeapon(String ItemName, String description, int stats, int crit)
	{
		currentWeapon.setStats(stats);
		currentWeapon.setName(ItemName);
		currentWeapon.setCrit(crit);
		currentWeapon.setDescription(description);
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
	
	public boolean didCrit(int critChance)
	{
		boolean hasCrit = false;
		int r = (int) (Math.random() * (100 - 1)) + 1;
		if (r % critChance == 0)
		{
			return true;
		}
		
		return hasCrit;
	}
	
	public boolean fight(String monsterName, int monsterHealth, int monsterAttack)
	{
		boolean fight = false;
		int weapon = currentWeapon.getStats();
		int critChance = currentWeapon.getCrit();
		int attack;
		Monster monster = new Monster(monsterName, monsterHealth, monsterAttack);
		System.out.println(monsterName + " Goes in to fight!");
		System.out.println("Your health: " + playerhealth);
		System.out.println(monsterName + " health " + monsterHealth);
		
		while (playerhealth > 0 && monsterHealth > 0)
		{
			System.out.println("   1          2           3    ");
			System.out.println("{attack}  {use inv}  {show inv}");
			int action = input.nextInt();
			boolean willContinue = false;
			if (action == 1)
			{
				if (didCrit(critChance))
				{
					System.out.println("CRITICAL HIT AGAINST " + monsterName + "!");
					attack = (int) (weapon * 1.5);
					System.out.println("You attack " + monsterName + " for " + attack + " damage!");
					monsterHealth = monsterHealth - attack;
				}
				else
				{
					attack = (int) (weapon + Math.random() + 0.3);
					System.out.println("You attack " + monsterName + " for " + attack + " damage!");
					monsterHealth = monsterHealth - attack;
				}
				willContinue = true;
			}	
			else if(action == 2)
			{
				willContinue = useInv();
			}
			else if(action == 3)
			{
				showInventory();
			}
				//TimeUnit.SECONDS.sleep(1);
			if (willContinue == true)
			{
				if (monsterHealth > 0)
				{
					int monsterCrit = 3;
					if (didCrit(critChance))
					{
						System.out.println(monsterName + " LANDS A CRITICAL HIT AGAINST YOU");
						int monstAttack = (int) (monsterAttack * 1.5);
						System.out.println("You are hit for " + monstAttack + " damage!");
						playerhealth = playerhealth - monstAttack;
					}
					else
					{
						int monstAttack = (int) (monsterAttack + Math.random() + 0.3);
						System.out.println("You are hit for " + monstAttack + " damage!");
						playerhealth = playerhealth - monstAttack;
					}
				}
				System.out.println("Your health: " + playerhealth);
				System.out.println(monsterName + " health: " + monsterHealth);
			}
		}
		
		if (monsterHealth < 0)
		{
			System.out.println(monsterName + " Dies");
		}
		else
		{
			System.out.println("You have died!");
		}
		return fight;
	}
	
}
