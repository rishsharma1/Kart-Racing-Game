import org.newdawn.slick.SlickException;

/**
 *Tomato item in the world that can only be picked up by the player,
 *and if used will create a projectile in the world.
 */

public class Tomato extends Item {
	
	/**how far away to create the projectile**/
	private static final int PIXELS_AWAY = 40;
	/**location of projectile model**/
	private static final String PROJECTILE_MODEL = "assets/items/tomato-projectile.png";
	/**constant velocity of the projectile**/
	public static final double VELOCITY = 1.7;

	/**Initializes the Tomato item 
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public Tomato(double x, double y, String directory) throws SlickException {
		super(x, y, directory);

	}
	
	/**
	 * uses the item if player has decided to use it 
	 */
	@Override
	public void update(World w) throws SlickException {
		
		super.getItem(w.getPlayer());
		if(super.isUsingItem()) {
			use(w);
		}
		
		
	}
	
	/**
	 * if player decides to use the tomato, it will create 
	 * a tomato projectile that will be added into dangers of the world
	 * and player item will be set to null.
	 */
	@Override
	public void use(World w) throws SlickException {
		// TODO Auto-generated method stub
		Angle angle = new Angle(w.getPlayer().getAngle().getRadians());
		
		
		double x = w.getPlayer().getX()+angle.getXComponent(VELOCITY)+PIXELS_AWAY;
		double y = w.getPlayer().getY()+angle.getYComponent(VELOCITY)+PIXELS_AWAY;
	


		Danger tomatoProjectile = new Projectile(x,y,angle,PROJECTILE_MODEL);
		w.getDangers().add(tomatoProjectile);
		w.getPlayer().setItem(null);
		w.getPlayer().setExcused(true);
		super.setUsed(true);
		super.setUsingItem(false);
		
		
	}

}
