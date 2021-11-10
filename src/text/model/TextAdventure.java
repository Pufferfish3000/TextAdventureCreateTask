package text.model;

import java.util.*;

import text.model.Monster;
import text.model.Item;
import java.lang.Math;

public class TextAdventure 
{
	private Item currentWeapon;
	private Item newWeapon;
	private Item inv1;
	private Item inv2;
	private Item inv3;
	private Item inv4;
	private Item inv5;
	private int playerhealth;
	
	private ArrayList <Item> invList;
	private Monster monster;
	
	public TextAdventure() 
	{
		this.currentWeapon = new Item();
		this.newWeapon = new Item();
		this.inv1 = new Item();
		this.inv2 = new Item();
		this.inv3 = new Item();
		this.inv4 = new Item();
		this.inv5 = new Item();
		this.playerhealth = 100;
		this.invList = new ArrayList <Item>();
		invList.add(inv1);
		invList.add(inv2);
		invList.add(inv3);
		invList.add(inv4);
		invList.add(inv5);
	}
	
	public String showInventory(String type)
	{
		String inv = currentWeapon + "\n" + inv1 + inv2 + inv3 + inv4 + inv5;
		
		return inv;
	}
	
	public Item useInventory(int invNum)
	{
		
		return invList.get(invNum - 1);
	}
	
	public void giveItem(String ItemName, int stats)
	{

	}
	
	public String fight(String action, String monsterName, int monsterHealth, int monsterAttack)
	{
		String fightResult = "";
		int weapon = currentWeapon.getStats();
		int attack;
		monster.setattack(monsterAttack);
		monster.sethealth(monsterHealth);
		monster.setname(monsterName);
		while (playerhealth > 0 || monsterHealth > 0)
		{
			System.out.println("Your health: " + playerhealth);
			System.out.println("Monster health " + monsterHealth);
			if (action.equalsIgnoreCase("attack"))
			{
				int crit = (int) (Math.random()*(2-1)) +1;
				attack = crit * weapon;
				System.out.println("You attack monster for " + attack + " damage!");
				monsterHealth = monsterHealth - attack;
				monster.sethealth(monsterHealth);
			}
			int monsterCrit = (int) (Math.random()*(2-1)) +1;
			int monstAttack = monsterAttack * monsterCrit;
			playerhealth = playerhealth - monstAttack;
			System.out.println("Monster Attacks!");
			System.out.println("You are hit for " + monstAttack + " damage!");
			monsterAttack = monsterCrit * monsterAttack;
		}
		return fightResult;
	}
	
}
