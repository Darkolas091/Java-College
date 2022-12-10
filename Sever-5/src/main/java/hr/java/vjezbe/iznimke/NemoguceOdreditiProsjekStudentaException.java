package hr.java.vjezbe.iznimke;

import java.io.Serial;

/**
 * Iznimka koja se baci kada nije moguće odrediti prosjek studenta.
 */
public class NemoguceOdreditiProsjekStudentaException extends Exception {
	public NemoguceOdreditiProsjekStudentaException() {	}

	public NemoguceOdreditiProsjekStudentaException(String message) {
		super(message);
	}

	public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause) {
		super(message, cause);
	}

	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}

	public NemoguceOdreditiProsjekStudentaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	@Serial
	public static final long serialVersionUID = -5197041166284879069L;
}
