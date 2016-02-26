import org.newdawn.slick.SlickException;

/**
 * Danger holds the consequences that must be applied to
 * a kart if the kart is within a danger. It also asks child
 * classes to implement a update method. 
 */

public abstract class Danger extends GameObject {
	
	/**time the consequence lasts 0.7 seconds**/
	private static final int TIME_LENGTH = 700;
	/**rotation rate when consequence is ensuing**/
	private static final double ROTATE_RATE = 0.008;
	/**default move and rotate direction when consequence ensuing **/
	private static final double MOVE_DIR = 1.0;
	private static final double ROTATE_DIR = 1.0;
	
	/**rotation rate when consequence not ensuing**/
	private static final double NORMAL_RATE = 0.004;
	
	/**status of whether Danger is hit **/
	private boolean isHit;
	/** status of if danger still in affect**/
	private boolean inAffect;
	
	/**tracks the time of affect of danger**/
	private int counter;
	
	/**
	 * Initializes Danger, setting hit to false, in affect to false, and 
	 * counter to 0.
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public Danger(double x, double y, String directory) throws SlickException {
		super(x, y, directory);
		setHit(false);
		counter = 0;
		setInAffect(false);
	}
	
	/**
	 * Update method that needs to be implemented
	 * by child classes that will take care of different types of dangers
	 * @param w
	 * @throws SlickException
	 */
	public abstract void update(World w) throws SlickException;

	/**
	 * the hit status of the danger
	 * @return boolean
	 */
	public boolean isHit() {
		return isHit;
	}

	/**
	 * set the hit status of the danger
	 * @param isHit
	 */
	public void setHit(boolean isHit) {
		this.isHit = isHit;
	}
	
	/**
	 * checks to see if counter is within the time
	 * of the consquence, if so, applies, effects, to the 
	 * kart k, otherwise, sets effects back to normal for the 
	 * kart.
	 * @param k
	 */
	public void use(Kart k) {
		counter++;
		if(counter <= TIME_LENGTH && !k.isExcused()){
			setHit(true);
			inAffect = true;
			k.setAngleFactor(ROTATE_RATE);
			k.setMoveDir(MOVE_DIR);
			k.setRotateDir(ROTATE_DIR);
		}
		else {
			inAffect = false;
			k.setAngleFactor(NORMAL_RATE);
			k.setDanger(null);
		}
		
	}
	/**
	 * get count of danger in affect
	 * @return counter
	 */
	public int getCounter() {
		return counter;
	}
	
	/**
	 * set the counter for the danger in affect 
	 * @param counter
	 */

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	/**
	 * get the status of if item is in affect
	 * @return boolean
	 */
	public boolean isInAffect() {
		return inAffect;
	}
	
	/**
	 * set the status of the item that is in affect
	 * @param inAffect
	 */
	public void setInAffect(boolean inAffect) {
		this.inAffect = inAffect;
	}

}
