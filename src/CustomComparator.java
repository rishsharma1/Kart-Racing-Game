import java.util.Comparator;

/**
 * Custom comparer to compare karts y coordinate,
 * so it can be used to rank them
 */

public class CustomComparator implements Comparator<Kart> {
	
	/**
	 * return 1 if kartOne > kartTwo y coordinate
	 * return 0 if kartOne = karTwo y coordinate
	 * return -1 if kartOne < kartTwo y coordinate
	 */
	public int compare(Kart kartOne,Kart kartTwo) {
		return (int) (kartOne.getY() - kartTwo.getY());
	}
}
