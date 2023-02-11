
package bilutleie.controllere;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bilutleie.util.LoginUtil;


@Controller
@RequestMapping("/logout")													// "/logout" mappes til ALLE controllerne i klassen...
public class LogoutController {
	
	private String LOGGED_OUT_MESSAGE = "Du er naa logget ut";
	
	
	@PostMapping															// POST /logout er forespørselen for å logge ut
    public String loggUt(HttpSession session, RedirectAttributes ra) {
		
		if (LoginUtil.erBrukerInnlogget(session)) {							// Kun hvis brukeren er innlogget...
			LoginUtil.loggUtBruker(session);								// ... logger vi brukeren ut
		}
		ra.addFlashAttribute("redirectMessage", LOGGED_OUT_MESSAGE);		// Legger til en melding i loginView
		return "redirect:" + "login";										// Gjør så en redirect tilbake til "/login"
    }
	
}

