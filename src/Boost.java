import org.newdawn.slick.SlickException;

/**
 *Boost increases player's acceleration for a short time.
 *The Boost lasts for 3 seconds, increasing the acceleration the
 *0.0008 px/ms2 in the forwards direction
 */
public class Boost extends Item{
	
	/**acceleration while boost is active**/
	private static final double BOOST_ACCELERATION = 0.0008;
	/**time the boost effect lasts **/
	private static final int THREE_MS = 3000;
	/**acceleration while boost is not active **/
	private static final double NORMAL_ACCELERATION = 0.0005;
	
	
	
	/**Initializes the Boost item, with the position and model location 
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public Boost(double x, double y, String directory) throws SlickException {
		super(x, y, directory);
	}
	
	/**updates the time of the boost and uses the item
	 * if usingItem is still true
	 * @param World
	 */
	@Override
	public void update(World w) throws SlickException {
		
		super.getItem(w.getPlayer());
		
		if(super.isUsingItem()) {
			
			super.setCounter(super.getCounter()+1);
			use(w);
		}
		
	}
	
	/**
	 * applies the affect of setting a higher acceleration if the counter is
	 * within 3 seconds, otherwise sets the item to be used, and sets players acceleration
	 * back to normal and sets player item to null
	 */
	@Override
	public void use(World w) throws SlickException{
		
		if(super.getCounter() <= THREE_MS && !super.isUsed()) {
			w.getPlayer().setAcceleration(BOOST_ACCELERATION);
		}
		else {
			super.setUsed(true);
			super.setUsingItem(false);
			w.getPlayer().setAcceleration(NORMAL_ACCELERATION);
			w.getPlayer().setItem(null);
		}
		
	}

}
