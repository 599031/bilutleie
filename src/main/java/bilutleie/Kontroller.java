
package bilutleie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class Kontroller {
	
	
	@RequestMapping("/startside")					// localhost:8080/startside i nettleser...
	public String siHallo() {						// ... vil da kalle denne metoden
		return "index";								// ... og returnere "Hallo verden!" tilbake til nettleseren
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

