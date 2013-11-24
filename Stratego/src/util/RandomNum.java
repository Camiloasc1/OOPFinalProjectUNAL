
package util;

/**
 * @author Camilo Sanchez
 * 
 */
public class RandomNum
{
	/**
	 * @return
	 *         Random byte
	 */
	public static byte getByte()
	{
		return getByte((byte) (Byte.MAX_VALUE - 1));
	}
	
	/**
	 * @param max
	 *            Highest number
	 * @return
	 *         Random byte
	 */
	public static byte getByte(byte max)
	{
		return getByte((byte) 0, max);
	}
	
	/**
	 * @param min
	 *            Lowest number
	 * @param max
	 *            Highest number
	 * @return
	 *         Random byte
	 */
	public static byte getByte(byte min, byte max)
	{
		return (byte) (Math.random() * ((max - min) + 1) + min);
	}
	
	/**
	 * @return
	 *         Random short
	 */
	public static short getShort()
	{
		return getShort((short) (Short.MAX_VALUE - 1));
	}
	
	/**
	 * @param max
	 *            Highest number
	 * @return
	 *         Random short
	 */
	public static short getShort(short max)
	{
		return getShort((short) 0, max);
	}
	
	/**
	 * @param min
	 *            Lowest number
	 * @param max
	 *            Highest number
	 * @return
	 *         Random short
	 */
	public static short getShort(short min, short max)
	{
		return (short) (Math.random() * ((max - min) + 1) + min);
	}
	
	/**
	 * @return
	 *         Random integer
	 */
	public static int getInt()
	{
		return getInt((int) Integer.MAX_VALUE - 1);
	}
	
	/**
	 * @param max
	 *            Highest number
	 * @return
	 *         Random integer
	 */
	public static int getInt(int max)
	{
		return getInt((int) 0, max);
	}
	
	/**
	 * @param min
	 *            Lowest number
	 * @param max
	 *            Highest number
	 * @return
	 *         Random integer
	 */
	public static int getInt(int min, int max)
	{
		return (int) (Math.random() * ((max - min) + 1) + min);
	}
	
	/**
	 * @return
	 *         Random long
	 */
	public static long getLong()
	{
		return getLong((long) Long.MAX_VALUE - 1);
	}
	
	/**
	 * @param max
	 *            Highest number
	 * @return
	 *         Random long
	 */
	public static long getLong(long max)
	{
		return getLong((long) 0, max);
	}
	
	/**
	 * @param min
	 *            Lowest number
	 * @param max
	 *            Highest number
	 * @return
	 *         Random long
	 */
	public static long getLong(long min, long max)
	{
		return (long) (Math.random() * ((max - min) + 1) + min);
	}
	
	/**
	 * @return Random boolean
	 */
	public static boolean getBool()
	{
		return (getByte((byte) 0, Byte.MAX_VALUE) % 2 == 0);
	}
}
