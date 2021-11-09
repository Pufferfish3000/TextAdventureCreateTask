package text.model;

import java.util.ArrayList;
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
	}
	
	public String showInventory(String type)
	{
		String inv = currentWeapon + "\n" + inv1 + inv2 + inv3 + inv4 + inv5;
		
		return inv;
	}
	
	public Item useInventory(int invNum)
	{
		Item inv = inv1;
		
		if (invNum == 2)
		{
			inv = inv2;
		}
		else if (invNum == 3)
		{
			inv = inv3;
		}
		else if (invNum == 4)
		{
			inv = inv4;
		}
		else if (invNum == 5)
		{
			inv = inv5;
		}
		
		return inv;
	}
	
	public String fight(String action, String monsterName, int monsterHealth, int monsterAttack)
	{
		String fightResult = "";
		int weapon = currentWeapon.getStats();
		int attack;
		monster.setattack(monsterAttack);
		monster.sethealth(monsterHealth);
		monster.setname(monsterName);
				
		if (action.equalsIgnoreCase("attack"))
		{
			int crit = (int) (Math.random()*(2-1)) +1;
			attack = crit * weapon;
			monsterHealth = monsterHealth - attack;
			monster.sethealth(monsterHealth);
		}
		int monsterCrit = (int) (Math.random()*(2-1)) +1;
		monsterAttack = monsterCrit * monsterAttack;
		return fightResult;
	}
	
}
