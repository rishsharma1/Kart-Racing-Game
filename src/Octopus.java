import java.awt.geom.Point2D;

import org.newdawn.slick.SlickException;



/**
 * When Octopus is within 100 and 250px of the player, he 
 * drives straight towards the player, otherwise he follows 
 * the normal course
 */
public class Octopus extends Enemy{
	
	private static final int LOWER_BOUND = 100;
	private static final int UPPER_BOUND = 250;
	
	
	/**Initializes the Octopus
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @param w
	 * @throws SlickException
	 */
	public Octopus(double x, double y, Angle angle, String directory,World w) throws SlickException {
		super(x, y, angle, directory,w);
		
	}
	
	/**
	 * checks to see if current position is within the range of the player
	 * if so sets the current waypoint as the players position, otherwise 
	 * follows normal course by updating waypoint.
	 */
	@Override
	public void update(World w) throws SlickException {
		
		Point2D.Double pointPosition = new Point2D.Double(super.getX(),super.getY());
		Point2D.Double playerMiddle = new Point2D.Double(w.getPlayer().getX(),w.getPlayer().getY());
		
		
		
		if(touchPlayer(pointPosition,playerMiddle)) {
			super.setCurrentX(playerMiddle.getX());
			super.setCurrentY(playerMiddle.getY());
		}
		else {
			super.updateWaypoint(w);
			
		}
		
		super.followWaypoints(w);
				
	}
	
	
	
	/**if the distance to playe is within the upper bound and 
	 * lower bound, it returns true, otherwise false.
	 * @param pointA
	 * @param pointB
	 * @return boolean
	 */
	private boolean touchPlayer(Point2D.Double pointA,Point2D.Double pointB) {
		
		double distanceToPlayer = super.distance(pointA,pointB);
		
		if(distanceToPlayer >= LOWER_BOUND && distanceToPlayer <= UPPER_BOUND) {
			return true;
		}
		return false;
	}

}
