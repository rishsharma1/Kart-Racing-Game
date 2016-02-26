/* SWEN20003 Object Oriented Software Development
 * Kart Racing Game
 * Author: <Rishabh Sharma> <rishabhs>
 * Student Number: 694739
 */

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */

public class World
{
    /** Location of the "assets" directory. */
    private static final String ASSETS_PATH = "assets/";
    /** File name of the map **/
    private static final String MAP_NAME = "map.tmx";
    private static final String FILE_DELIMITER = ",";
    
    /**Location of the data**/
    private static final String WAYPOINTS = "data/waypoints.txt";
    private static final String ITEMS = "data/items.txt";
    
    /**Location of the models**/
    private static final String ELEPHANT_MODEL = "karts/elephant.png";
    private static final String DOG_MODEL = "karts/dog.png";
    private static final String OCTOPUS_MODEL = "karts/octopus.png";
    private static final String OILCAN_MODEL = "items/oilcan.png";
    private static final String TOMATO_MODEL = "items/tomato.png";
    private static final String BOOST_MODEL = "items/boost.png";
    private static final String PLAYER_MODEL = "karts/donkey.png";
    
    /**File Not Found **/
    private static final String FILE_ERROR = "File Not Found.";
    
    private static final String OILCAN = "Oilcan";
    private static final String BOOST = "Boost";
    

    /** to call friction from map.tmx **/
    private static final String FRICTION = "friction";
    /** for rendering the map a little over boundaries calculated **/
    private static final int EXCESS_RENDER = 5;
    /** if friction value is within 0.01% of 1, then they are equal **/
    private static final double STOP_FRICTION = 1;
    
    /** Starting coordinates on the map **/
	private static final double PLAYER_X = 1332;
	private static final double PLAYER_Y = 13086;
	private static final double ELEPHANT_X = 1260;
	private static final double ELEPHANT_Y = 13086;
	private static final double DOG_X = 1404;
	private static final double DOG_Y = 13086;
	private static final double OCTOPUS_X = 1476;
	private static final double OCTOPUS_Y = 13086;
	
	/**finish line data**/
	private static final double FINISH_LINE = 1026;
	private static final String MESSAGE = "You Came ";
	private static final String EXCLAMATION = "!";
	
	
	
	private ArrayList<Point> waypoints;
	private ArrayList<Enemy> enemies;
	private ArrayList<Item> pickUps;
	private ArrayList<Danger> dangers;
	
	private boolean finished;
	
	/** Initial angle of player **/
	public static final Angle INITIAL_ANGLE = Angle.fromDegrees(0.0f);
	
	private Panel panel;
    

    /**to store the map **/
    private TiledMap map;
    /**to store the camera **/
    private Camera camera;
    /**to store the player **/
    private Player player;
    
    

    
	
	
    /** Create a new World object. */
    public World()
    throws SlickException
    {
    	/** Initializing and adding reference to objects in the world**/
    	 waypoints = new ArrayList<Point>();
    	 enemies = new ArrayList<Enemy>();
    	 pickUps = new ArrayList<Item>();
    	 dangers = new ArrayList<Danger>();
    	 waypoints();
    	 items();
    	 finished = false;
    	 map = new TiledMap(ASSETS_PATH+MAP_NAME,ASSETS_PATH);
    	 player = new Player(PLAYER_X,PLAYER_Y,INITIAL_ANGLE,ASSETS_PATH+PLAYER_MODEL);
    	 panel = new Panel();
    	
    	 enemies.add(new Elephant(ELEPHANT_X,ELEPHANT_Y,INITIAL_ANGLE,ASSETS_PATH+ELEPHANT_MODEL,this));
    	 enemies.add(new Dog(DOG_X,DOG_Y,INITIAL_ANGLE,ASSETS_PATH+DOG_MODEL,this));
    	 enemies.add(new Octopus(OCTOPUS_X,OCTOPUS_Y,INITIAL_ANGLE,ASSETS_PATH+OCTOPUS_MODEL,this));
    	 camera = new Camera(Game.SCREENWIDTH,Game.SCREENHEIGHT,player);
    }


    /**get all karts in the world
     * @return ArrayList<Kart>
     */
    public ArrayList<Kart> getKarts() {
		ArrayList<Kart> karts = new ArrayList<Kart>();
		karts.addAll(enemies);
		karts.add(player);
		
		return karts;
	}


	/** Update the game state for a frame until the player
	 * reaches the finish line
     * @param rotate_dir The player's direction of rotation
     *      (-1 for anti-clockwise, 1 for clockwise, or 0).
     * @param move_dir The player's movement in the car's axis (-1, 0 or 1).
     * @param use_item passed for status of use item.
     */
    public void update(double rotate_dir, double move_dir,boolean use_item)
    throws SlickException
    {
    	
    	if(finished) {
    		rotate_dir = 0.0;
    		move_dir = 0.0;
    		player.update(rotate_dir, move_dir,use_item,this);
    	}
    	else {
    		player.update(rotate_dir, move_dir,use_item,this);
    	}
    	
    	camera.update(player);
   
    	for(int i = 0;i<enemies.size();i++) {
    		enemies.get(i).update(this);
    	}
    	for(int i = 0;i<dangers.size();i++) {
    		
    		if(!dangers.get(i).isHit()) {
    			dangers.get(i).update(this);
    		}
    	}
    	ranking();
    	
   

    }
    
    /** get player object
     * @return player
     */
    public Player getPlayer() {
		return player;
	}
    
	/** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
	public void render(Graphics g)
    throws SlickException
    {
	
		int camTileX = camera.getX()/map.getTileWidth();
		int camTileY = camera.getY()/map.getTileHeight();
		
		
		int camOffSetX = camera.getX() % map.getTileWidth();
		int camOffSetY = camera.getY() % map.getTileHeight();
		
		int screenTileWidth = Game.SCREENWIDTH/map.getTileWidth() + EXCESS_RENDER;
		int screenTileHeight = Game.SCREENHEIGHT/map.getTileHeight() + EXCESS_RENDER;
		
    	map.render(-camOffSetX,-camOffSetY,camTileX,
    	camTileY,screenTileWidth,screenTileHeight);
    	player.render(camera);
    	
    	/**render enemies**/
    	for(int i=0;i<enemies.size();i++) {
    		enemies.get(i).render(camera);
    	}
    	
    	/** render if not picked up**/
    	for(int i=0;i<pickUps.size();i++) {
    		
    		if(!pickUps.get(i).isPickedUp()) {
    			pickUps.get(i).render(camera);
    		}
    	}
    	
    	/** render if not hit**/
    	for(int i=0;i<dangers.size();i++) {
    		
    		if(!dangers.get(i).isHit()) {
    			dangers.get(i).render(camera);
    		}
    	}
    	panel.render(g,player.getRanking(),player.getItem());
    	
    	/** display message once game is finished*/
    	if(finished) {
    		g.drawString(MESSAGE+Panel.ordinal(player.getRanking())+EXCLAMATION,Game.SCREENWIDTH/2, Game.SCREENHEIGHT/2);
    	}
    	
    }
	
	/**
	 * Returns the friction coefficient at point x,y by getting tileID.
	 * @param x
	 * @param y
	 */
	public double friction(int x, int y) {
	   	int tileID = map.getTileId(x/map.getTileWidth(),y/map.getTileHeight(),0);
    	return Double.parseDouble(map.getTileProperty(tileID, FRICTION, null));
    }
	
	/**This will return true if the friction is 1,and false otherwise.
	 * @param friction
	 */
	public boolean isFrictionBlock(double friction) {
		
		return friction >= STOP_FRICTION;
	}
	
	
	
	/**
	 * using scanner to get waypoints from
	 * a text file and storing it in Array list 
	 * waypoints
	 */
	private void waypoints() {
		
		File file = new File(WAYPOINTS);
		int x;
		int y;
		
		try {
			
			@SuppressWarnings("resource")
			Scanner readFile = new Scanner(file);
			
			
			while(readFile.hasNext()) {
				String[] arr = readFile.next().split(FILE_DELIMITER);
				x = Integer.parseInt(arr[0]);
				y = Integer.parseInt(arr[1]);
				Point p = new Point(x,y);
				waypoints.add(p);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println(FILE_ERROR);
		}

	}
	
	/**
	 * Sort the ranking by comparing Y coordinates of each 
	 * kart, assign places from left to right 
	 */
	private void ranking() {
		
		ArrayList<Kart> karts = getKarts();
		
		Collections.sort(karts,new CustomComparator());
		
		if(player.getY() > FINISH_LINE) {
			for(int i=0;i<karts.size();i++) {
				karts.get(i).setRanking(i+1);
			}
		}
		else {
			finished = true;
		}
		
		
	}
	
	/**get all item locations and initialize and 
	 * store it in a array list called picUps
	 * @throws SlickException
	 */
	private void items() throws SlickException {
		
		File file = new File(ITEMS);
		String name;
		int x;
		int y;
		
		try {
			
			@SuppressWarnings("resource")
			Scanner readFile = new Scanner(file);
			
			
			while(readFile.hasNext()) {
				String[] arr = readFile.next().split(FILE_DELIMITER);
				name = arr[0];
				x = Integer.parseInt(arr[1]);
				y = Integer.parseInt(arr[2]);
				
				if(name.equals(OILCAN)) {
					pickUps.add(new Oilcan(x,y,ASSETS_PATH+OILCAN_MODEL));
				}
				else if(name.equals(BOOST)) {
					pickUps.add(new Boost(x,y,ASSETS_PATH+BOOST_MODEL));
				}
				else {
					pickUps.add(new Tomato(x,y,ASSETS_PATH+TOMATO_MODEL));
				}
			}
		}
		catch (FileNotFoundException e) {
			System.out.println(FILE_ERROR);
		}
		
	}
	
	/**get all pickUps
	 * @return ArrayList<Item>
	 */
	public ArrayList<Item> getPickUps() {
		return pickUps;
	}


	/**get all waypoints
	 * @return ArrayList<Point>
	 */
	public ArrayList<Point> getWaypoints() {
		return waypoints;
	}


	/**get all Dangers in the world
	 * @return ArrayList<Danger>
	 */
	public ArrayList<Danger> getDangers() {
		return dangers;
	}


	
}
