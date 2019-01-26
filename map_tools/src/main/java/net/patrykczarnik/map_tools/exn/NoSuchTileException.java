package net.patrykczarnik.map_tools.exn;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Exception thrown in case when a tile image should be obtained, but is not found.
 */
public class NoSuchTileException extends MapToolsException {

	public NoSuchTileException() {
	}

	public NoSuchTileException(String aMessage) {
		super(aMessage);
	}

	public NoSuchTileException(Throwable aCause) {
		super(aCause);
	}

	public NoSuchTileException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}
}
