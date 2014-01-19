
package util;

/**
 * @author camiloasc1
 */
public class MathUtil
{
	/**
	 * @param x
	 * @param y
	 * @return Distance from (0,0) to (x,y)
	 */
	public static double getDistance(int x, int y)
	{
		// return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return getDistance(0, 0, x, y);
	}
	
	/**
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return Distance from (x1,y1) to (x2,y2)
	 */
	public static double getDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	/**
	 * @param a
	 * @param b
	 * @return Difference between both numbers
	 */
	public static double compareDouble(double a, double b)
	{
		return (((a - b) > 0) ? (a - b) : (b - a));
	}
	
	/**
	 * @param a
	 * @param b
	 * @return difference accuracy with respect to 1E-2
	 */
	public static boolean isEqualsDouble2(double a, double b)
	{
		return ((compareDouble(a, b)) < 1E-2);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return difference accuracy with respect to 1E-4
	 */
	public static boolean isEqualsDouble4(double a, double b)
	{
		return ((compareDouble(a, b)) < 1E-4);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return difference accuracy with respect to 1E-8
	 */
	public static boolean isEqualsDouble8(double a, double b)
	{
		return ((compareDouble(a, b)) < 1E-8);
	}
	
	/**
	 * @param a
	 * @param b
	 * @return difference accuracy with respect to 1E-16
	 */
	public static boolean isEqualsDouble16(double a, double b)
	{
		return ((compareDouble(a, b)) < 1E-16);
	}
}
