package text.controller;
import text.model.TextAdventure;
public class Controller 
{
	private TextAdventure game;
	public Controller()
	{
		this.game = new TextAdventure();	
	}
	public void main()
	{
		String stickDescript = "medium sized branch taken off a tree";
		game.giveWeapon("Stick", stickDescript, 15, 6);
		game.giveItem("Apple", "Shiny Red Apple", 20);
		game.fight("GOBLIN", 20, 10);
		game.fight("GOBLIN ELDER", 50, 23);
	}
}