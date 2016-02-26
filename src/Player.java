import org.newdawn.slick.SlickException;

/**
 * The player is the user controlled Kart (Donkey)
 */

public class Player extends Kart{
	
	private Item item;

	
	
	/** Initializes the player object 
	 * @param directory where the player image asset is located
	 * @throws SlickException
	 */
	public Player(double x,double y,Angle angle,String directory) 
	throws SlickException 
	{
		
		super(x,y,angle,directory);		

	}
	
	/**updates player, depending on if it can pick anything up near by,
	 * or any dangers near player vicinity, consequence applied accordingly
	 * @param rotate_dir (-1 for anti-clockwise, 1 for clockwise, or 0).
	 * @param move_dir The player's movement in the car's axis (-1, 0 or 1)
	 * @param use_item this is boolean which gives indication if player wants to use item
	 * @param world World object 
	 * @throws SlickException
	 */
	public void update(double rotate_dir, double move_dir,boolean use_item,World world) 
			throws SlickException 
	{
		
		super.setRotateDir(rotate_dir);
		super.setMoveDir(move_dir);
		
		if(item != null) {
			
			if(item.isUsingItem()) {
				use_item = true;
			}
			else if(use_item) {
				item.setUsingItem(true);
			}
			else {
				use_item = false;
			}
		}
		
		/**check to see if player can get any pick ups **/
		for(int i=0;i<world.getPickUps().size();i++) {
			world.getPickUps().get(i).getItem(this);
		}
		
		/**if picked something up**/
		if(this.getItem() != null) {
			item.update(world);
		}
		
		/**check for dangers**/
		super.checkDanger(world);
		
		super.move(super.getRotateDir(),super.getMoveDir(), world);

	}
	
	/**
	 * gets the item in player's possession 
	 * @return item
	 */
	public Item getItem() {
		return item;
	}
	
	/**
	 * sets the item in player's possession
	 * @param item
	 */
	public void setItem(Item item) {
		this.item = item;
	}




	


	
}
