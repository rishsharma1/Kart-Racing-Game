
import org.newdawn.slick.SlickException;
/**
 * Camera is rectangle positioned in the world observing objects
 */
public class Camera {
	

	private final int width;
	private final int height;
	

	
	/**Camera coordinates **/
	private int x;
	private int y;
	

	
	/**Initializes the camera according the location of player 
	 * @param unit player object
	 * @throws SlickException
	 */
	public Camera(int width,int height,Player unit) 
	throws SlickException
	{
		this.width = width;
		this.height = height;
		update(unit);
	}


	/**
	 * get the x coordinate of the camera
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * get the y coordinate of the camera
	 * @return y
	 */
	public int getY() {
		return y;
	}
	

	/**
	 * updates the camera's x and y coordinates 
	 * centered around the player
	 * @param p
	 * @throws SlickException
	 */
	public void update(Player p) throws
	SlickException
	{
		setX((int) p.getX() - (width/2));
		setY((int) p.getY() - (height/2));

	}
	
	/**
	 * set the x coordinate of the camera
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * set the y coordinate of the camera;
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}



}
