package text.model;

public class Monster 
{
	private String name;
	private int attack;
	private int health;
	private boolean isBig;
	
	public Monster(String name, int health, int attack, boolean isBig)
	{
		this.name = name;
		this.health = health;
		this.attack = attack;
		this.isBig = isBig;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public boolean getIsBig()
	{
		return this.isBig;
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
	
	public void setIsBig(boolean isBig)
	{
		this.isBig = isBig;
	}
}
