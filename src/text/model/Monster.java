package text.model;

public class Monster 
{
	private String name;
	private int attack;
	private int health;
	 
	
	public void Monster(String name, int health, int attack)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int gethealth()
	{
		return this.health;
	}
	
	public int getattack()
	{
		return this.attack;
	}
	
	public void setname(String name)
	{
		this.name = name;
	}
	
	public void setattack(int attack)
	{
		this.attack = attack;
	}
	
	public void sethealth(int health)
	{
		this.health = health;
	}

}
