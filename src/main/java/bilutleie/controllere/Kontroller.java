
package bilutleie.controllere;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class Kontroller {
	
													// localhost:8080/startside i nettleser...
	@GetMapping("/startside")
	public String siHallo() {
		System.out.println("triggerd");				// ... vil da kalle denne metoden
		return "index";								// ... og returnere "Hallo verden!" tilbake til nettleseren
	}
	
	
	
	@GetMapping("/lokasjon")
	public String hentLokasjon(@RequestParam String lokasjon) {
		
		
		
		
		return "";
		
		
	}
	
	
	
	
	
	
	
	
	
}

/*


<dependency>
<groupId>javax.servlet</groupId>
<artifactId>jstl</artifactId>
<version>1.2</version>
</dependency>



*/

