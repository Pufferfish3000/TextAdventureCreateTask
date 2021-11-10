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
		game.fight("attack", "goblin", 20, 10);
	}
}