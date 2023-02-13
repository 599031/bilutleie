
package bilutleie.controllere;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bilutleie.util.LoginUtil;




@Controller
public class StartController {
	
	
	@GetMapping("/startside")				// localhost:8080/startside i nettleser...
	public String visStartside() {			// ... vil da kalle denne metoden
		return "startView";					// ... og returnerer ref. til startView.jsp
	}
	
	
	

	
	@GetMapping("/lokasjon")														// Viser webshop-siden
    public String visLedigeBiler(HttpSession session, RedirectAttributes ra,
    		@RequestParam String lokasjon, Model model ) {	
		
		model.addAttribute("lokasjon", lokasjon);

		return "locationView";													// Viser webshop-siden
    }
	
}

