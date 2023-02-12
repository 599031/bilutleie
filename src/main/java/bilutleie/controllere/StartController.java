
package bilutleie.controllere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class StartController {
	
	
	@GetMapping("/startside")				// localhost:8080/startside i nettleser...
	public String visStartside() {			// ... vil da kalle denne metoden
		return "startView";					// ... og returnerer ref. til startView.jsp
	}
	
	
	
	@GetMapping("/lokasjon")
	public String hentLokasjon(@RequestParam String lokasjon) {		// ikke implemenert
		return "";
	}
	
}

