
package bilutleie.util;


public class InputValidator {
	
	public static final String ANY_LETTER = "[a-zA-ZæøåÆØÅ]";
	public static final String ANY_LETTER_OR_DIGIT = "[a-zA-ZæøåÆØÅ0-9]";
	public static final String THREE_OR_MORE_TIMES = "{3,}";
	
	
	/**
	 * @param Brukernavnet som skal valideres
	 * @return Om brukernavnet er gyldig eller ikke
	 * 
	 * Brukernavnet kan ikke begynne med et tall.
	 * Lovlige tegn er bokstaver (små og store) inkl. de norske bokstavene og tall.
	 * Et gyldig brukernavn består av 4 eller flere tegn.
	 */
	public static boolean isValidUsername(String username) {
		String regexp = "^" + ANY_LETTER + ANY_LETTER_OR_DIGIT + THREE_OR_MORE_TIMES + "$";
		return username != null && username.matches(regexp);
	}
	
}

