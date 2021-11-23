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
		game.giveWeapon("STICK", "medium sized branch taken off a tree", 15, 6.0);
		game.giveItem("APPLE", "Shiny Red Apple", 10);
		game.giveItem("SMALL POTION", "Small vial filled with a mysterious red liquid", 30);;
		while (game.getIsRunning())
		{
			game.playerMove();
			game.randEvent();
		}
	}
}