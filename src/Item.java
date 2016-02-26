import java.awt.geom.Point2D;

import org.newdawn.slick.SlickException;



/**
 *Item class that is responsible for the items in the world,
 *implements the procedure on how they are picked up, and asks
 *child classes to implement the effects of it. 
 */
public abstract class Item extends GameObject {
	

	/**status of if item is picked up**/
	private boolean pickedUp;
	/**counter to keep track of affect of item**/
	private int counter;
	/**status of if item has been used*/
	private boolean used;
	/**status of if item is being used*/
	private boolean usingItem;
	

	/**Initializes the item with its position, and model location,
	 * sets the used status to false, and using status to false, and
	 * counter to 0.
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public Item(double x, double y, String directory) throws SlickException {
		super(x, y, directory);
		setUsed(false);
		setUsingItem(false);
		counter = 0;
	}
	
	/**Abstract method that needs to be implemented by child classes,
	 * like Tomato, and Boost, which will take care of updating 
	 * the item
	 * @param World
	 * @throws SlickException
	 */
	public abstract void update(World w) throws SlickException;
	
	/**Abstract method that needs to be implemented by child classes,
	 * which will take care applying the effects of the item
	 * @param w
	 * @throws SlickException
	 */
	public abstract void use(World w) throws SlickException;
	
	
	/**Checks if a player has hit an item on the ground, if so
	 * sets picked up to true, and sets the players item.
	 * @param player
	 */
	public void getItem(Player player) {
		
		Point2D.Double pointItem = new Point2D.Double(super.getX(),super.getY());
	
		if(super.hitObject(pointItem, player) && !isPickedUp()) {
			setPickedUp(true);
			player.setItem(this);

		}
	}
	

	/**the status of the isPickedup
	 * @return boolean
	 */
	public boolean isPickedUp() {
		return pickedUp;
	}

	/**set status of pickedUp
	 * @param pickedUp
	 */
	public void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}

	/**get counter of item use
	 * @return counter
	 */
	public int getCounter() {
		return counter;
	}

	/**set the counter of item use
	 * @param counter
	 */
	public void setCounter(int counter) {
		this.counter = counter;
	}

	/**used for drawing the item icons to the panel in the 
	 * game
	 * @param i
	 * @param panel_top
	 * @param object
	 */
	public void drawImage(int i, int panel_top, Object object) {
		super.getModel().draw(i, panel_top);
		
	}

	/**check to see if the item has been used
	 * @return boolean
	 */
	public boolean isUsed() {
		return used;
	}
	

	/**sets the status of used
	 * @param used
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**check to see if you are still using the item
	 * @return boolean
	 */
	public boolean isUsingItem() {
		return usingItem;
	}

	/**set the status of usingItem
	 * @param usingItem
	 */
	public void setUsingItem(boolean usingItem) {
		this.usingItem = usingItem;
	}
	

}
