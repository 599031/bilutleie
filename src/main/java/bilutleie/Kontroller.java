
package bilutleie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class Kontroller {
	
		// localhost:8080/startside i nettleser...
	@GetMapping("/startside")	
	public String siHallo() {	
		System.out.println("triggerd");
		// ... vil da kalle denne metoden
		return "index";								// ... og returnere "Hallo verden!" tilbake til nettleseren
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

