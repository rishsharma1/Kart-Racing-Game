

import java.awt.geom.Point2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *GameObject encapsulates the properties that each object in the 
 *world has, like the coordinates, screen coordinates, and the image model.
 *It also implements a render method that can be inherited by all objects in the
 *world.
 */
public abstract class GameObject {
	
	/**hit detection range **/
	private static final int HIT_PIXELS = 40;
	/**coordinates on the map**/
	private double x;
	private double y;
	
	
	
	/**coordinates of the screen**/
	private double screenX;
	private double screenY;
	
	
	
	/**model for the objects**/
	private Image model;
	
	
	/**
	 * Initializes the Game object,
	 * setting the coordinates in the world and the relevant image model of
	 * the object
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public GameObject(double x,double y,String directory) 
	throws SlickException {
		
		this.x = x;
		this.y = y;
		model = new Image(directory);
		
	}
	
	/**
	 * Renders the objects
	 * @param camera
	 */
	public void render(Camera camera){
		screenX = x - camera.getX();
		screenY = y - camera.getY();
		model.drawCentered((int)screenX, (int)screenY);
		
	}
	
	/**
	 * checks to see if point is about to be near an object,within 40px,
	 * if so return true, otherwise return false
	 * @param future
	 * @param obj
	 * @return boolean
	 */
	public boolean hitObject(Point2D.Double future,GameObject obj) {
		Point2D.Double objPoint = new Point2D.Double(obj.getX(),obj.getY());
		
		if(distance(future,objPoint) < HIT_PIXELS) {
			return true;
		}
		return false;
	}
	
	/**
	 * Distance between two points 
	 * @param a
	 * @param b
	 * @return distance 
	 */
	public static double distance(Point2D.Double a,Point2D.Double b) {
		return Math.sqrt(Math.pow(a.getX()-b.getX(),2)+Math.pow(a.getY()-b.getY(),2));
	}

	/**
	 * @return player model
	 */
	public Image getModel() {
		return model;
	}
	/** set the model for player
	 * @param model
	 */
	public void setModel(Image model) {
		this.model = model;
	}
	
	/**
	 * get coordinate x of object
	 * @return coordinate x
	 */
	public double getX() {
		return x;
	}
	/**
	 * get coordinate y of object
	 * @return coordinate y
	 */
	public double getY() {
		return y;
	}
	
	/**
	 *set coordinate x 
	 * @param x
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * set coordinate y
	 * @param y
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * get screen coordinate x for object
	 * @return screen coordinate x
	 */
	public double getScreenX() {
		return screenX;
	}
	
	/**
	 * set screen coordinate x for object
	 * @param screenX
	 */
	public void setScreenX(double screenX) {
		this.screenX = screenX;
	}

	/**
	 * get screen coordinate y for object
	 * @return screen coordinate y
	 */
	public double getScreenY() {
		return screenY;
	}
	
	/**
	 * set screen coordinate y for object
	 * @param screenY
	 */
	public void setScreenY(double screenY) {
		this.screenY = screenY;
	}


}
