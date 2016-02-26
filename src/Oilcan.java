import org.newdawn.slick.SlickException;

/**
 *Oilcan can be picked up only by the player, and when used creates an
 *OilSlick behind the player.
 */
public class Oilcan extends Item{
	
	private static final int BACK = 180;
	private static final double PIXELS_AWAY = 40;
	private static final String OILSLICK_MODEL = "assets/items/oilslick.png";
	
	
	/**
	 * Initializes the Oilcan
	 * @param x
	 * @param y
	 * @param directory
	 * @throws SlickException
	 */
	public Oilcan(double x, double y, String directory) throws SlickException {
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
	 * if player decides to use the OilCan, it will create 
	 * a OilSlick behind the player that will be added into dangers of the world
	 * and player item will be set to null.
	 */
	@Override
	public void use(World w) throws SlickException{
		Angle angle_back = Angle.fromDegrees(BACK);
		Angle angle = w.getPlayer().getAngle().add(angle_back);

		double x = w.getPlayer().getX()+angle.getXComponent(w.getPlayer().getVelocity())+PIXELS_AWAY;
		double y = w.getPlayer().getY()+angle.getXComponent(w.getPlayer().getVelocity())+PIXELS_AWAY;

		Danger oilSlick = new OilSlick(x,y,OILSLICK_MODEL);
		w.getDangers().add(oilSlick);
		w.getPlayer().setItem(null);
		w.getPlayer().setExcused(false);
		super.setUsed(true);
		super.setUsingItem(false);
	}
}
