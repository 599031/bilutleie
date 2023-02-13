
package bilutleie.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginUtil {
	
	private final static int MAX_INTERACTIVE_INTERVAL = 500;								//500 sek.
	
	
	public static void loggInnBruker(HttpServletRequest request, String username) {
		loggUtBruker(request.getSession());												// Logger først ut for å sikre at vi...
		HttpSession session = request.getSession();										// ... oppretter en splitter ny sesjon her!
		session.setAttribute("username", username);										// Legger inn data: brukernavn										
		session.setMaxInactiveInterval(MAX_INTERACTIVE_INTERVAL);						// Setter antall sek. levetid etter siste bruk
	}
	
	
	public static void loggUtBruker(HttpSession session) {
		session.invalidate();															// Logger ut ved å invalidere sesjonen
	}
	
	
	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("username") != null;				// Er pålogget hvis brukeren har sesjon MED data
	}
	
}

