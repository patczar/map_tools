package net.patrykczarnik.map_tools.exn;

/**
 * @author Patryk Czarnik <patryk@patrykczarnik.net>
 *
 * Exception thrown in case of file processing problems.
 * Instances usually contain a reference to the cause.
 */
public class FileProcessingException extends MapToolsException {

	public FileProcessingException() {
	}

	public FileProcessingException(String aMessage) {
		super(aMessage);
	}

	public FileProcessingException(Throwable aCause) {
		super(aCause);
	}

	public FileProcessingException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
	}
}
