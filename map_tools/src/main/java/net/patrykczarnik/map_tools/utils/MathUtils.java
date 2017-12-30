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
	
	/** Chcecks whether the argument fits between the two given numbers.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetween(int aArgument, int aMin, int aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument > aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}

	/** Chcecks whether the argument fits between the two given numbers.
	 * If yes, nothing happens.
	 * If no, then an IllegalArgumentException is thrown.
	 */
	public static void checkArgBetween(double aArgument, double aMin, double aMax, String aMessage) throws IllegalArgumentException {
		if(aArgument < aMin || aArgument > aMax) {
			throw new IllegalArgumentException(aMessage);
		}
	}
}
