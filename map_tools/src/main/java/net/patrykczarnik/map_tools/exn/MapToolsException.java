package net.patrykczarnik.map_tools.exn;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * 
 * The base class for all custom exceptions.
 */
public class MapToolsException extends Exception {

	public MapToolsException() {
		super();
	}

	public MapToolsException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}

	public MapToolsException(String aMessage) {
		super(aMessage);
	}

	public MapToolsException(Throwable aCause) {
		super(aCause);
	}
}
