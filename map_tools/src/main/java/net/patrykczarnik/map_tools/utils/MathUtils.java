package net.patrykczarnik.map_tools.utils;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * A utility class with mathematical operations.
 */
public final class MathUtils {

	/** This class is not intended to be instantiated. */
	private MathUtils() {
	}
	
	/** Checks whether the argument fits between the two given numbers.
	 * The range is open (exclusive) on both sides.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenOO(int aArgument, int aMin, int aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument <= aMin || aArgument >= aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Checks whether the argument fits between the two given numbers.
	 * The range is open (exclusive) on both sides.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenOO(double aArgument, double aMin, double aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument <= aMin || aArgument >= aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Checks whether the argument fits between the two given numbers.
	 * The range is closed (inclusive) on the left (lower) side, and open (exclusive) on the right (greater) side.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenCO(int aArgument, int aMin, int aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument >= aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Checks whether the argument fits between the two given numbers.
	 * The range is closed (inclusive) on the left (lower) side, and open (exclusive) on the right (greater) side.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenCO(double aArgument, double aMin, double aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument >= aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Checks whether the argument fits between the two given numbers.
	 * The range is closed (inclusive) on both sides.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenCC(int aArgument, int aMin, int aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument > aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}
	
	/** Checks whether the argument fits between the two given numbers.
	 * The range is closed (inclusive) on both sides.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetweenCC(double aArgument, double aMin, double aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument > aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}	

	/** Chcecks whether the argument is not negative.
	 * If it is not, nothing happens.
	 * If it is negative, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgNonNegative(int aArgument, String aMessage) throws IllegalArgumentException {
		if(aArgument < 0) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Chcecks whether one number is strictly less that the other.
	 * If the condition holds, nothing happens.
	 * If the condition is not met, then an IllegalArgumentException is thrown.
	 */
	public static void checkLT(int aLeft, int aRight, String aMessage) throws IllegalArgumentException {
		if(aLeft >= aRight) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	public static void checkLT(int aLeft, int aRight) throws IllegalArgumentException {
		checkLT(aLeft, aRight, "Number " + aLeft + " is not less than " + aRight);
	}

	/** Chcecks whether one number is less that or equals to the other.
	 * If the condition holds, nothing happens.
	 * If the condition is not met, then an IllegalArgumentException is thrown.
	 */
	public static void checkLE(int aLeft, int aRight, String aMessage) throws IllegalArgumentException {
		if(aLeft > aRight) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	public static void checkLE(int aLeft, int aRight) throws IllegalArgumentException {
		checkLE(aLeft, aRight, "Number " + aLeft + " is greater than " + aRight);
	}

}
