
package bilutleie.controllere;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bilutleie.util.InputValidator;
import bilutleie.util.LoginUtil;


@Controller
public class LoginController {
	
	
	private String INVALID_USERNAME = "Manglende eller ugyldig brukernavn";
	
	
	@GetMapping("/login")												// Henter login-skjema
    public String hentLoginSkjema() {
		return "loginView";
    }
	
	
	@PostMapping("/login")												// Forsøker å logge inn
    public String provAaLoggeInn(@RequestParam String username,
    		HttpServletRequest request,	RedirectAttributes ra) {
		
		if (!InputValidator.isValidUsername(username)) {				// Hvis inntastet brukernavn er ugyldig...
			ra.addFlashAttribute("redirectMessage", INVALID_USERNAME);	// ... legger vi til en melding i loginView
			return "redirect:" + "login";								// ... og gjør så en redirect tilbake til "/login"
		}
		LoginUtil.loggInnBruker(request, username);						// Logger inn brukeren vha. egen hjelpemetode fra LoginUtil
		return "redirect:" + "startside";									// Gjør en redirect til "/biler"
    }
	
}

