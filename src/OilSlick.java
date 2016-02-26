import org.newdawn.slick.SlickException;

/**
 *Oilslick is a danger in the world, that if a kart is near,
 *it will apply consequences to the kart.
 */
public class OilSlick extends Danger{

	/**
	 * Initializes the Oilslick
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public OilSlick(double x, double y, String directory) throws SlickException {
		super(x, y, directory);

	}
	
	/** 
	 *  no update needed for this class
	 */
	@Override
	public void update(World w) {
		
		
	}

}
