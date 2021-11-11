package text.model;

public class Item 
{
	private String name;
	private String description;
	private int stats;
	 
	public Item()
	{
		this.stats = 0;
		this.name = "NOTHING"; 
	}
	
	public void Item(String name, String description, int stats)
	{
		this.name = name;
		this.description = description;
		this.stats = stats;
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
	
	@Override
	public String toString()
	{
		String string = "";
		string += name + ": " + description + "\nHeals "+ stats + " damage";
		return string;
	}

}
