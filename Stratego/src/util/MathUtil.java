/*******************************************************************************
 * File: MathUtil.java
 * Project: Stratego
 * 
 * Copyright (C) 2014 Camiloasc1.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package util;

/**
 * @author camiloasc1
 * 
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
