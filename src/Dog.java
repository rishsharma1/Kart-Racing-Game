

import org.newdawn.slick.SlickException;



/**
 *Dog is an Enemy, when Dog is ahead of player, she slows down, to let the 
 *player catch up, whereas whe she is behind the player, she speeds up, to 
 *catch up.
 */
public class Dog extends Enemy {
	
	/**acceleration when ahead player**/
	private static final double AHEAD = 0.00045;
	/**acceleration when behind player**/
	private static final double BEHIND = 0.00055;
	
	
	
	/**Initializes Dog
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @param w
	 * @throws SlickException
	 */
	public Dog(double x, double y, Angle angle, String directory,World w) throws SlickException {
		super(x, y, angle, directory,w);
			
	}

	/**
	 * updates waypoints, and checks to see if dog is ahead of player,
	 * and applies relevant accelerations accordingly, then finally follows
	 * the waypoint.
	 */
	@Override
	public void update(World w) throws SlickException {
		
		
		super.updateWaypoint(w);
		
		/**remember the smaller the ranking the higher it is**/
		if(super.getRanking() < w.getPlayer().getRanking()) {
			super.setAcceleration(AHEAD);

		}
		else {
			super.setAcceleration(BEHIND);
		}
		
		super.followWaypoints(w);
		
	}

}
