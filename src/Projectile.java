

import org.newdawn.slick.SlickException;

/**
 * The Projectile will be a danger in the world, that if kart is in contact with
 * it, the projectile will be destroyed, and the danger consequence will be applied.
 * Also, if the projectile hits any tile that is rock, or water, or of friction
 * type 1 in general, the projectile will be destroyed.
 */

public class Projectile extends Danger{
	
	private static final double VELOCITY = Tomato.VELOCITY;
	
	private Angle angle;
	private double futureX;
	private double futureY;
	
	/**
	 * Initializes the projectile 
	 * @param x
	 * @param y
	 * @param angle
	 * @param directory
	 * @throws SlickException
	 */
	public Projectile(double x, double y,Angle angle,String directory) throws SlickException {
		super(x, y, directory);
		this.angle = angle;


	}
	
	/**
	 *check to see if future coordinates give a friction of 1, if so destroy it,
	 *otherwise update the future coordinates of the projectile  
	 */
	@Override
	public void update(World w) {
		
		futureX = super.getX() + angle.getXComponent(VELOCITY);
		futureY = super.getY() + angle.getYComponent(VELOCITY);
		double mu_future = w.friction((int)futureX, (int)futureY);
		
		if(w.isFrictionBlock(mu_future)) {
			super.setHit(true);
		}
		else {
			super.setX(futureX);
			super.setY(futureY);
		}
		
	}




}
