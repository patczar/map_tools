package net.patrykczarnik.map_tools.exn;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 * 
 * The base class for all custom exceptions.
 */
public class MapToolsException extends Exception {
	public MapToolsException() {
	}

	public MapToolsException(String message) {
		super(message);
	}

	public MapToolsException(Throwable cause) {
		super(cause);
	}

	public MapToolsException(String message, Throwable cause) {
		super(message, cause);
	}
}
