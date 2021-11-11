package text.model;

public class Weapon 
{
	private String name;
	private String description;
	private int stats;
	private int crit;
	 
	public Weapon()
	{
		this.name = "Nothing";
		this.description = "";
		this.stats = 0;
		this.crit = 0;
	}
	
	public void Weapon(String name, String description, int stats, int crit)
	{
		this.name = name;
		this.stats = stats;
		this.description = description;
		this.crit = crit;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public int getStats()
	{
		return this.stats;
	}
	
	public int getCrit()
	{
		return this.crit;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setStats(int stats)
	{
		this.stats = stats;
	}
	
	public void setCrit(int crit)
	{
		this.crit = crit;
	}

}
