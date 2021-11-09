package text.model;

public class Item 
{
	private String name;
	private int stats;
	 
	public Item()
	{
		this.stats = 0;
		this.name = "Nothing"; 
	}
	
	public void Item(String name, int stats)
	{
		this.name = name;
		this.stats = stats;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public int getStats()
	{
		return this.stats;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setStats(int stats)
	{
		this.stats = stats;
	}

}
