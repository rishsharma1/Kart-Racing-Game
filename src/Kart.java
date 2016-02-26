
import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.newdawn.slick.SlickException;

/**
 *Represents a kart which is a super class for player and 
 *Enemy, it handles the physics of the kart that is common
 *to both child classes
 */

public abstract class Kart extends GameObject {
	
	/** Rate of change of the angle **/
    public static final double ANGLE_FACTOR = 0.004;
    /** Rate of change of velocity of player **/
    public static final double ACCELERATION = 0.0005;
    
    private static final double ROTATE_DIR = 1.0;
    private static final double MOVE_DIR = 1.0;
      
	
	private double velocity;
	private Angle angle;
	private double angleFactor;
	private double acceleration;
	private int ranking;
	private Point2D.Double futurePoint;
	private double moveDir;
	private double rotateDir;
	private Danger danger;
	private boolean isExcused;
	

	/**Initializes the Kart by setting position, angle, and model
	 * location, it also sets default values of ranking, velocity, and 
	 * initial rotation and acceleration, and danger to null.
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @throws SlickException
	 */
	public Kart(double x, double y, Angle angle, String directory) throws SlickException {
		super(x, y, directory);
		/*Initializes velocity*/
		velocity = 0;
		ranking = 0;
		/*Initializes Angle*/
		this.angle = angle;
		
		angleFactor = ANGLE_FACTOR;
		acceleration = ACCELERATION;
		futurePoint = new Point2D.Double();
		setDanger(null);
		setExcused(false);
		
	}
	
	/**Takes care of movement of the car depending on the rotation direction
	 * and movement direction. Each time it is called it updates the velocity
	 * depending on the friction coefficient, and therefore
	 * the position of the kart , checking if the future position 
	 * collides with another kart or mountain/water block, if so it will set
	 * the velocity of the kart to 0, otherwise it will update it.
	 * @param rotate_dir
	 * @param move_dir
	 * @param world
	 * @throws SlickException
	 */
	public void move(double rotate_dir, double move_dir,World world) 
	throws SlickException{

		/**Creating a new angle based on rotat_dir and delta**/
		Angle newAngle = new Angle(angleFactor*rotate_dir);
		/**Adding onto the previous angle **/
		setAngle(getAngle().add(newAngle));
		/** getting current friction **/
		double mu = world.friction((int)super.getX(),(int)super.getY());
		
		/** update velocity **/
		velocity += acceleration*move_dir;
		velocity *= (1-mu);
		/** update rotation **/
		super.getModel().setRotation((float)getAngle().getDegrees());

		futurePoint = futureCoordinate(velocity);
		
		/** what is the upcoming friction coefficient **/
		double mu_future = world.friction((int)futurePoint.getX(), (int)futurePoint.getY());
		
		/** if the friction coefficient is 1 **/
		if(world.isFrictionBlock(mu_future) || collide(futurePoint,world)) {
			velocity = 0;
		}
		else { 
			super.setX(futurePoint.getX());
			super.setY(futurePoint.getY());
		
		}
		
	}
	
	/**Given a length this will provide the future coordinates 
	 * depending on the rotational movement 
	 * @param length
	 * @return Point2D.Double
	 */
	public Point2D.Double futureCoordinate(double length) {
		
		/**getting Cartesian from polar coordinates and adding onto previous location**/		
		double final_x = super.getX() + getAngle().getXComponent(length);
		double final_y = super.getY() + getAngle().getYComponent(length);
		
		return new Point2D.Double(final_x, final_y);
		
	}
	
	/**Checks to see if a kart is about to collide with another kart, if
	 * so it returns true, otherwise returns false.
	 * @param future
	 * @param w
	 * @return boolean
	 */
	public boolean collide(Point2D.Double future,World w) {
		ArrayList<Kart> karts = w.getKarts();
		
		for(int i=0;i<karts.size();i++) {
			
			/**Make sure not to take into account the kart you are checking**/
			if(super.hitObject(future,karts.get(i)) && !karts.get(i).equals(this)) {
					return true;
			}

		}
		return false;
		
	}
	
	/**Checks to see if there are any dangers near this kart, if so ...
	 * @param w
	 * @throws SlickException
	 */
	public void checkDanger(World w) throws SlickException {
		
		Point2D.Double pointFuture = futureCoordinate(velocity);
		for(int i=0;i<w.getDangers().size();i++) {
			
			if(super.hitObject(pointFuture, w.getDangers().get(i)) 
			||getDanger() != null) {
				
				setDanger(w.getDangers().get(i));
			}
		}
		if(getDanger() != null) {
			
			getDanger().use(this);
			setRotateDir(ROTATE_DIR);
			setMoveDir(MOVE_DIR);
		}
		
		
	}
	

	/**
	 * @return velocity
	 */
	public double getVelocity() {
		return velocity;
	}

	/**
	 * @param velocity
	 */
	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * @param angle
	 */
	public void setAngle(Angle angle) {
		this.angle = angle;
	}
	
	/**
	 * @return Angle
	 */
	public Angle getAngle() {
		return angle;
	}

	/**
	 * @return double
	 */
	public double getAngleFactor() {
		return angleFactor;
	}

	/**
	 * @param angleFactor
	 */
	public void setAngleFactor(double angleFactor) {
		this.angleFactor = angleFactor;
	}

	/**
	 * @return acceleration
	 */
	public double getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration
	 */
	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @return ranking 
	 */
	public int getRanking() {
		return ranking;
	}

	/**
	 * @param ranking
	 */
	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	/**
	 * @return move direction
	 */
	public double getMoveDir() {
		return moveDir;
	}

	/**
	 * @param moveDir
	 */
	public void setMoveDir(double moveDir) {
		this.moveDir = moveDir;
	}

	/**
	 * @return rotation direction
	 */
	public double getRotateDir() {
		return rotateDir;
	}

	/**
	 * @param rotateDir
	 */
	public void setRotateDir(double rotateDir) {
		this.rotateDir = rotateDir;
	}

	/**
	 * @return Danger
	 */
	public Danger getDanger() {
		return danger;
	}

	/**
	 * @param danger
	 */
	public void setDanger(Danger danger) {
		this.danger = danger;
	}
	
	/**
	 * gets if kart is excused to danger
	 * @return boolean
	 */
	public boolean isExcused() {
		return isExcused;
	}
	
	/**
	 * set if kart is excused to danger
	 * @param isExcused
	 */
	public void setExcused(boolean isExcused) {
		this.isExcused = isExcused;
	}

}
