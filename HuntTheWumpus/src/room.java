public class room 
{
	private int[] roomArray;
	private String roomDesc;
	private boolean pit;
	private boolean spiders;
	private boolean wumpus;
	private boolean arrow;
	
	/**
	 * Constructor for room object
	 * 
	 * @param rA	Array that stores the room number and the 3 adjacent rooms
	 * @param rD	Room description
	 */
	public room(int[] rA, String rD) 
	{
		roomArray = rA;
		roomDesc = rD;
		pit = false;
		spiders = false;
		wumpus = false;
		arrow = false;
	}
	
	/**
	 * Room getter
	 * 
	 * @param x		Int that represents the room. 0 is the room number. 1, 2, and 3 are the adjacent rooms
	 * @return		Room number
	 */
	public int getRoom(int x)
	{
		return roomArray[x];
	}
	
	/**
	 * Description getter
	 * 
	 * @return		The description
	 */
	public String getDesc()
	{
		return roomDesc;
	}
	
	/**
	 * Spider setter (adds spiders to a room)
	 */
	public void setSpiders()
	{
		spiders = true;
	}
	
	/**
	 * Pit setter (adds pit to a room)
	 */
	public void setPit()
	{
		pit = true;
	}
	
	/**
	 * Wumpus setter (adds a wumpus to a room)
	 */
	public void setWumpus()
	{
		wumpus = true;
	}
	
	/**
	 * Checks for spiders
	 * 
	 * @return		spiders as true or false
	 */
	public boolean hasSpiders()
	{
		return spiders;
	}
	
	/**
	 * Checks for a pit
	 * 
	 * @return		pit as true or false
	 */
	public boolean hasPit()
	{
		return pit;
	}
	
	/**
	 * Checks for wumpus
	 * 
	 * @return		wumpus as true or false
	 */
	public boolean hasWumpus()
	{
		return wumpus;
	}
	
	/**
	 * Checks if a room has arrows
	 * 
	 * @return		If room has arrows
	 */
	public boolean getArrows()
	{
		return arrow;
	}

	/**
	 * Sets a room to have arrows
	 * @param b 
	 * 
	 */
	public void setArrows(boolean b)
	{
		arrow = b;
	}
}