import java.awt.Point;
import java.awt.geom.Point2D;

import org.newdawn.slick.SlickException;

/**
 * Represents the behavior of Enemy karts,
 * takes care of their ability to follow waypoints, and is
 * a super class for Dog, Octopus, and Elephant
 */

public abstract class Enemy extends Kart {
	
	/**radius of the waypoint**/
	private static final int PIXELS = 250;
	
	private static final double MOVE_DIR = 1.0;
	/** 90 degrees**/
	private static final int DEGREE = 90;
	
	private double currentX;
	private double currentY;
	private int wayPointIndex;
	private Angle angle;

	
	

	/**Initializes the position of the Enemy kart, angle, model location
	 * and sets the the first waypoint that they will follow.
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @param w
	 * @throws SlickException
	 */
	public Enemy(double x, double y, Angle angle, String directory,World w) throws SlickException {
		super(x, y, angle, directory);
		setWayPointIndex(0);
		Point p = w.getWaypoints().get(wayPointIndex);
		currentX = p.getX();
		currentY = p.getY();

	}
	
	/**This method will determine the angle between 
	 * the enemy's current position and the current waypoint
	 * calculating the rotation direction (0,-1,1), however if there
	 * is a danger up ahead it will override the movement.
	 * @param World
	 * @throws SlickException
	 */
	public void followWaypoints(World w) throws SlickException {
			
		/** find the angle in between the the current waypoint and position of the kart**/
		angle = Angle.fromDegrees(Math.toDegrees(Math.atan2(currentY-super.getY(),currentX-super.getX()))+DEGREE);
		
		
		/**set the rotation direction**/
		if(Math.abs(angle.getRadians()-super.getAngle().getRadians()) < Kart.ANGLE_FACTOR ) {
			super.setRotateDir(0.0);
		}
		else if((angle.subtract(super.getAngle()).getRadians()) > Kart.ANGLE_FACTOR) {
			super.setRotateDir(1.0);
		}
		else {
			super.setRotateDir(-1.0);
		}
		
		/**check for dangers**/
		super.checkDanger(w);

		super.move(super.getRotateDir(),MOVE_DIR, w);

		
	}
	
	
	/**given two points it will get the distance in between, if 
	 * the distance is within 250px, it means the edge has been touched
	 * of the waypoint, return true, otherwise return false
	 * @param a
	 * @param b
	 * @return boolean
	 */
	public boolean touchEdge(Point2D.Double a,Point2D.Double b) {
		
		if(super.distance(a,b) <= PIXELS) {
			return true;
		}
		return false;
	}
	
	/**Checks to see if the waypoint needs to be updated, depending on
	 * if current position is touching the edge of the current waypoint.
	 * @param World
	 */
	public void updateWaypoint(World w) {
		
		Point2D.Double pointPosition = new Point2D.Double(super.getX(),super.getY());
		Point2D.Double currentWaypoint = new Point2D.Double(getCurrentX(),getCurrentY());
		
		if(touchEdge(pointPosition,currentWaypoint)) {
			
			/**only update till the last value in the array list **/
			if(getWayPointIndex() < (w.getWaypoints().size() - 1)) {
				setWayPointIndex(getWayPointIndex()+1);
				Point p = w.getWaypoints().get(getWayPointIndex());
				setCurrentX(p.getX());
				setCurrentY(p.getY());
			}

		}
	}
	
	
	
	/**abstract method which need to be implemented by,
	 * Dog, Octopus, and Elephant ; and any other child class
	 * of Enemy.
	 * @param World
	 * @throws SlickException
	 */
	public abstract void update(World w) throws SlickException;


	/**get current x coordinate of waypoint
	 * @return current
	 */
	public double getCurrentX() {
		return currentX;
	}


	/**set the current x coordinate of waypoint 
	 * @param currentX
	 */
	public void setCurrentX(double currentX) {
		this.currentX = currentX;
	}


	/**get current y coordinate of waypoint
	 * @return currentY
	 */
	public double getCurrentY() {
		return currentY;
	}


	/**set the current y coordinate of waypoint 
	 * @param currentY
	 */
	public void setCurrentY(double currentY) {
		this.currentY = currentY;
	}

	/**get the current waypoint index we are at now 
	 * @return waypoinIndex
	 */
	public int getWayPointIndex() {
		return wayPointIndex;
	}

	/**set the waypoint we are at now 
	 * @param wayPointIndex
	 */
	public void setWayPointIndex(int wayPointIndex) {
		this.wayPointIndex = wayPointIndex;
	}
	
	

}
