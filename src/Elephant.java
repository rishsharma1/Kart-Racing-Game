

import org.newdawn.slick.SlickException;


/**
 *Elephant drives through the course as fast as he can,
 *with no special mechanisms 
 */
public class Elephant extends Enemy {
	
	
	
	
	/**Initializes Elephant
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @param w
	 * @throws SlickException
	 */
	public Elephant(double x, double y, Angle angle, String directory,World w) throws SlickException {
		super(x, y, angle, directory,w);
		
	}

	/**
	 * Update waypoints and follow them
	 */
	@Override
	public void update(World w) throws SlickException {
		
		
		super.updateWaypoint(w);
		super.followWaypoints(w);

		
	}


}
