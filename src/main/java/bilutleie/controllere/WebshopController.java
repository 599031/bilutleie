
package bilutleie.controllere;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bilutleie.model.Cart;
import bilutleie.model.CartItem;
import bilutleie.util.LoginUtil;


@Controller
@RequestMapping("/webshop")													// "/webshop" mappes til ALLE controllerne i klassen...
public class WebshopController {
	
	private String REQUIRES_LOGIN_MESSAGE = "Foresporselen din krever at du er innlogget";
	
	
	@GetMapping																	// GET /webshop er forespørselen for å vise webshop-siden
    public String visWebshoppen(HttpSession session, RedirectAttributes ra) {
		
		if (!LoginUtil.erBrukerInnlogget(session)) {							// Hvis brukeren ikke er innlogget...
			ra.addFlashAttribute("redirectMessage", REQUIRES_LOGIN_MESSAGE);	// ... legger vi til en melding i loginView
			return "redirect:" + "login";										// ... og gjør så en redirect tilbake til "/login"
		}
		return "webshopView";													// Viser webshop-siden
    }
	
	
	
	@PostMapping																// POST /webshop er forespørselen for å handle en/flere varer
    public String leggVarerIHandlekurv(
    		@RequestParam(name="vare", required=false) List<String> varer,		// required=false for å unngå krasj hvis ingen avkrysset
    		HttpSession session, RedirectAttributes ra) {
		
		if (!LoginUtil.erBrukerInnlogget(session)) {							// Hvis brukeren IKKE er innlogget...
			ra.addFlashAttribute("redirectMessage", REQUIRES_LOGIN_MESSAGE);	// ... legger vi til en melding i loginView
			return "redirect:" + "login";										// ... og gjør så en redirect tilbake til "/login"
		}
		
		Cart cart = (Cart) session.getAttribute("cart");						// Henter handlevogn som ble opprettet ved innlogging
		
		if (varer != null) {													// NB! Sjekker at vi faktisk mottok en liste med varer
			if (varer.contains("bukse"))										// Hvis listen inneholder "bukse" (value="bukse" i JSP)...
				cart.addItem(new CartItem("Bukse", 699));						// ... oppretter og legger til CartItem i cart
			if (varer.contains("genser"))										// Hvis listen inneholder "genser" (value="genser" i JSP)...
				cart.addItem(new CartItem("Genser", 399));						// ... oppretter og legger til CartItem i cart
		}
		return "redirect:" + "webshop";										// Gjør en redirect til "/webshop"
    }
	
}



/*	
	
	TEORI
	
	_____________________________________________________________________________________________________________________
	
	
	Spørsmål (se LoginUtil-klassen)
	
	1. Hvordan kan brukeren ha en gyldig sesjons-id i requestene sine (lagret i nettleser) UTEN å være innlogget?
	
	2. Hvorfor hjelper det å sjekke om denne sesjonen har data knyttet til seg for å avgjøre om brukeren er innlogget?
	
	___________________________________________________________________________________________________________
	
	
	Her ser vi på:
	
	- Sesjoner og sesjonsdata
	
	- Innlogging, autentisering og utlogging
	
	- Legge konstanter i properties-fil
	
	- JSP Standard Template Library (JSTL)
	
	
	Dette er en enkel handlevogn-applikasjon som også krever en slags innlogging.
	PS! Det er et par ting i "fasiten" som ikke ble gjennomgått i timen.
	
	_____________________________________________________________________________________________________________________
	
	
	HTTP er tilstandsløs
	
	
	Det vil si at det ikke finnes noen "tilstand" i HTTP-protokollen som tar vare på data (hver request er helt "ny").
	Vi trenger altså en mekanisme for å ta vare på data utover HTTP-protokollen.
	
	
	Alt. 1		Nettleseren må enten direkte eller indirekte sende ALL nødvendig info. til tjeneren HVER gang!
				For hver eneste request, må tjeneren motta nok info. til å kunne finne ut av:
				- Hvem er brukeren?
				- Har brukeren rettigheter til å utføre forespørselen?
				- Hvor langt var vi kommet?
				- Hvilke data har vi og hvilke mangler vi?
	
	Alt. 2		Vanligvis kan tjeneren lagre informasjon.
				Nettleseren kan da nøye seg med å sende en sesjons-id som knyttes til data lagret på tjeneren.
	
	_____________________________________________________________________________________________________________________
	
	
	
	Nettleser	 --------------------- "Hi, I´m A" --------------------->
	
				 <------- "Hi A" + [Set-Cookie: session_id="..."] -------	 Server
	
	
	
	Nettleser	 --- "Hi, remember me?" + [Cookie: session_id="..."] --->
	
				 <--------------- "Yeah, your name is A!" ---------------	 Server
	
	
	_____________________________________________________________________________________________________________________
	
	
	Sesjons-id
	
	
	Vi har altså en unik id knyttet til en bestemt nettlesersesjon.
	
	Det er mest vanlig å sende denne med som en cookie i hver forespørsel.
	
	En alternativ sendingsmåte innebærer såkalt URL-omskriving.
	Id-en "bakes" da inn i alle URL-er til den gitte applikasjonen.
	Dette kan være en plan B om nettleseren har slått av cookies.
	
	_____________________________________________________________________________________________________________________
	
	
	Sesjons-data
	
	
	Vi kan knytte data på tjeneren til en bestemt sesjons-id.
	Disse dataene deles ikke på tvers av brukere / klienter, men tilhører en bestemt bruker-økt.
	
	I Java web-applikasjoner er dette lagt opp som et Map av "sesjonsobjekter",
	der hvert sesjonsobjekt blant annet inneholder et Map av "attributter".
	
	_____________________________________________________________________________________________________________________
	
	
	HttpSession API
	
	
	Sesjoner i Spring gjøres på en litt mer avansert måte enn det vi skal se på.
	Vi bruker heller standard Java HttpSession ifm. sesjonshåndtering.
	
	PS: Det samme gjelder sikkerhet/innlogging. Spring har noe som heter Spring Security,
	men dette er også noe mer avansert. Vi ser heller på det som heter programmatisk inn-/utlogging.
	
	
	For å jobbe med sesjoner i koden vår må vi bruke et HttpSession-objekt med tilhørende metoder.
	
	
	Vi tar først imot et HttpServletRequest-objekt (request-objekt) i parameterlisten til controlleren,
	og bruker dette til å opprette et HttpSession-objekt (sesjons-objekt) for denne requesten, slik:
	
	HttpSession sesjon = request.getSession()		Henter request sin sesjon, hvis den finnes.
													Hvis ikke, blir det opprettet en.
	
	HttpSession sesjon = request.getSession(true)	Henter request sin sesjon, hvis den finnes.
													Hvis ikke, blir det opprettet en.
	
	HttpSession sesjon = request.getSession(false)	Henter request sin sesjon, hvis den finnes.
													Hvis ikke, blir sesjon satt til null.
	
	
	Merk! Vi kan derimot også ta imot dette HttpSession-objektet direkte i parameterlisten til controlleren,
	men dette vil ha null-verdi om det ikke finnes.
	
	
	Videre kan vi:
	
	- Invalidere sesjonen			sesjon.invalidate()
	
	- Knytte data til sesjonen		sesjon.setAttribute("navn", objekt)
	
	- Hente data fra sesjonen		T data = (T) sesjon.getAttribute("navn")
	
	
	Husk at poenget med å knytte data til en sesjon, er at de "lever forbi" requesten.
	Data lagret på denne måten vil også være tilgjengelig fra alle controllere/views i applikasjonen.
	
	_____________________________________________________________________________________________________________________
	
	
	Oversikt:
	
	
	GET /login		--> Vise innloggingsskjema
	POST /login		--> Logge inn. Deretter gå til butikksiden
	
	GET /webshop	--> Vise butikksiden
	POST /webshop	--> Legge varer i kurven. Deretter gå til butikksiden igjen.
	
	POST /logout	--> Logge ut. Deretter gå til innloggingsside.
	
	
	Merk at vi i denne applikasjnen har en egen controller-klasse for hver URL!
	
	_____________________________________________________________________________________________________________________
	
	
	Sekvensielt forløp:
	
	
	1.	Brukeren ber om login-skjema (get til "/login")
		- hentLoginSkjema()-metoden i LoginController-klassen håndterer get-requesten
		  loginView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	2.	Brukeren logger inn (post til "/login")
		- provAaLoggeInn()-metoden i LoginController-klassen håndterer post-requesten
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om webshop-skjema (get til "/webshop")
		- visWebshoppen()-metoden i WebshopController-klassen håndterer get-requesten
		  webShopView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	3.	Brukeren handler (post til "/webshop")
		- leggVarerIHandlekurv()-metoden i WebshopController-klassen håndterer post-requesten
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om webshop-skjema (get til "/webshop")
		- visWebshoppen()-metoden i WebshopController-klassen håndterer get-requesten
		  webShopView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	4.	Brukeren logger ut (post til "/logout")
		- loggUt()-metoden i LogoutController-klassen håndterer post-requesten
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om login-skjema (get til "/login")
		- hentLoginSkjema()-metoden i LoginController-klassen håndterer get-requesten
		  loginView sendes tilbake til nettleser ("200 OK"-respons)
	
	_____________________________________________________________________________________________________________________
	
	
	Sekvensielt forløp MED sesjonsdata OG innlogging:
	
	
	1.	Brukeren ber om login-skjema (get til "/login")
		- hentLoginSkjema()-metoden i LoginController-klassen håndterer get-requesten
		  loginView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	2.	Brukeren logger inn (post til "/login")
		- provAaLoggeInn()-metoden i LoginController-klassen håndterer post-requesten
		  
		  GYLDIG BRUKERNAVN? (eller redirect til "/login"?)
		  
		  NY SESJON
		  
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om webshop-skjema (get til "/webshop")
		- visWebshoppen()-metoden i WebshopController-klassen håndterer get-requesten
		  
		  ER BRUKEREN INNLOGGET? (eller redirect til "/login"?)
		  
		  HENTE SESJONSDATA (brukernavn og handlekurv hentes direkte i viewet)
		  
		  webShopView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	3.	Brukeren handler (post til "/webshop")
		- leggVarerIHandlekurv()-metoden i WebshopController-klassen håndterer post-requesten
		  
		  ER BRUKEREN INNLOGGET? (eller redirect til "/login"?)
		  
		  OPPDATERE SESJONEN (oppdaterer handlekurven)
		  
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om webshop-skjema (get til "/webshop")
		- visWebshoppen()-metoden i WebshopController-klassen håndterer get-requesten
		  
		  ER BRUKEREN INNLOGGET? (eller redirect til "/login"?)
		  
		  HENTE SESJONSDATA (brukernavn og handlekurv hentes direkte i viewet)
		  
		  webShopView sendes tilbake til nettleseren ("200 OK"-respons)
	
	
	4.	Brukeren logger ut (post til "/logout")
		- loggUt()-metoden i LogoutController-klassen håndterer post-requesten
		  
		  INVALIDERE SESJONEN (synonymt med å logge ut brukeren)
		  
		  Gjør en "302 Found" (redirect)
		- Nettleseren ber automatisk om login-skjema (get til "/login")
		- hentLoginSkjema()-metoden i LoginController-klassen håndterer get-requesten
		  loginView sendes tilbake til nettleser ("200 OK"-respons)
	
	
	Merk!
	
	- Vi bruker her sesjoner for å lagre info. om handlekurv og brukernavn mellom requester i samme nettleserøkt.
	  I tillegg setter vi opp et system for inn-/utlogging for å avgjøre om brukeren har lov til å utføre handlinger.
	  Programmatisk inn-/utlogging bygger på sesjoner og sesjonsdata.
	  Sesjoner og sesjonsdata kan derimot brukes helt for seg selv og har flere ulike bruksområder.
	
	- Det er såpass mange ulike steder i applikasjonen hvor vi må sjekke om brukeren er innlogget,
	  at det er lurt å legge dette i en egen hjelpeklasse (vi bruker LoginUtil-klassen for dette).
	
	- Det å logge inn vil si å få en ny sesjon.
	  Det kan vær brukeren har sesjons-id fra før av, men ved innlogging skal det pga. sikkerhet alltid opprettes en ny.
	  Ved å først logge ut, sikrer vi at "request.getSession()" faktisk oppretter en splitter ny sesjons-id.
	  Det må også knyttes data til denne sesjonen, slik at vi senere kan sjekke om gitt sesjons-id er innlogget.
	
	- Det å logge ut vil si å invalidere denne sesjonen.
	  Fremtidige requester med denne sesjonen vil da ikke bli gjenkjent.
	
	- Brukere kan ha en sesjon med gyldig id i requestene sine UTEN å være innlogget!
	  Hvis det derimot også er data tilknyttet denne sesjonen, er de alltid innlogget.
	  For å avgjøre om en bruker er innlogget må vi altså sjekke begge deler
	  (for sistnevnet er det nok å sjekke ett av sesjonens attributter).
	
	- Til slutt anvender vi også litt av det vi lærte om validering av bruker-input.
	  I InputValidator-klassen lager vi en egen statisk metode for brukernavn-validering,
	  og anvender denne ved innlogging, heller enn den manuelle sjekken vi lagde først.
	
	_____________________________________________________________________________________________________________________
	
	
	Nettleser-sesjon vs. tjener-sesjon
	
	
	Det er viktig å huske at det er 2 datamaskiner som inngår i en web-applikasjon: klient og tjener.
	Av og til snakker de med hverandre, men stort sett lever de hver for seg.
	
	En nettleser-sesjon varer så lenge brukeren har åpent et nettleservindu.
	Levetiden til sesjons-cookies følger levetiden til nettleser-sesjonen.
	
	En tjener-sesjon varer så lenge vi bestemmer oss for, enten til vi "invaliderer" den eller til den "går ut på tid".
	Tjeneren har ingen formening om nettleseren hos brukeren kjører eller ikke.
	
	Vi bør derfor alltid gi brukeren mulighet til å aktivt "invalidere" tjener-sesjonen, dvs. logge seg ut.
	Dette sikrer mot at neste bruker på samme maskin får tilgang til innlogget innhold hos forrige bruker.
	(uheldig å ikke ha knapp for utlogging og i tillegg en tjener-sesjon som varer "uendelig" lenge på serveren)
	
	Vi bør også alltid oppmuntre brukeren til å stenge nettleseren etter utlogging.
	Dette sikrer at sesjons-cookien i nettleseren "dør", slik at all sesjonsdata da uansett blir gjort utilgjengelig.
	
	_____________________________________________________________________________________________________________________
	
	
	Sesjon-timeout
	
	
	Vi bør alltid ha en fornuftig timeout på tjener-sesjonen ved inaktivitet.
	Dette sikrer at brukere som glemmer å logge ut og stenge nettleseren likevel får en viss beskyttelse.
	Dette gjør også at vi får ryddet litt opp, og unngår et økende antall "gamle" sesjoner som må holdes i minnet.
	
	Vi skriver:		sesjon.setMaxInactiveInterval(sek.)
	
	_____________________________________________________________________________________________________________________
	
	
	Legge konstanter i properties-fil
	
	
	I web-applikasjonene vi lager mappes hver controller til en bestemt (konstant) URL.
	Vi har også sett at ulike controllere kan kalle hverandre ved Post-Redirect-Get.
	
	Om en på et senere tidspunkt skulle ønske å endre på en eller flere URL-er,
	vil dette altså kreve opp til flere endringer rundt om i applikasjoner per URL.
	
	I Spring kan vi derimot gjøre denne jobben mye enklere ved å samle alle konstantene
	i properties-filen, og så hente verdiene herfra og inn i Java-koden der vi trenger dem.
	
	Spring har et eget minispråk for å hente frem data fra properties-filen (slik vi ser i JSP).
	
	Vi skriver:		"${propertieName}"
	
	Det er i prinsippet to typer steder vi kan bruke dette:
	1. Direkte i Spring-annoteringer, f.eks. @RequestMapping("/${app.url.login}")
	2. Annotere konstanter i Java-koden med @Value("${propertieName}")
	   NB! Klassen må være Spring-annotert, f.eks. med @Controller
	
	_____________________________________________________________________________________________________________________
	
	
	Litt mer om JSP
	
	
	JSP er egentlig forkledd Java-kode (det blir generert og kompilert Java-kode).
	
	JSP-sider er "program" som kjøres på web-tjeneren, som regel for å generere HTML,
	FØR responsen sendes tilbake til nettleseren.
	
	JSP gir også mulighet for løkker og valg som i vanlige Java-programmer.
	
	Men det er viktig å tenke MVC:
	- Det meste av programlogikk skal gjennomføres av controller og hjelpeklasser.
	- Det som gjenstår av logikk i JSP er f.eks. å presentere lister av data som HTML
	  eller ta valg om elementer og utseende i HTML basert på data.
	
	_____________________________________________________________________________________________________________________
	
	
	JSP Direktiver
	
	
	Direktiver er beskjeder til kodegenerator og kompilator om innstillinger som ikke kan uttrykkes i koden direkte.
	Disse beskjedene legges øverst i JSP-siden og ser f.eks. slik ut:
	
	<%@ page language="java" contentType="text/html; charset=UTF-8" ... %>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	<%@ page	Angir diverse egenskaper for siden.
				F.eks. tegnsett, responstype som produseres, osv...
	
	
	<%@ taglib	Angir at man ønsker å bruke et tag-bibliotek og hvordan disse taggene navngis.
				F.eks. Spring-form eller JSTL core.
				
				Tag-bibliotek i JSP er akkuratt som å bruke kodebibliotek i Java:
				- Legger importlinje øverst
				- Bruker prefiks for å angi hvilket bibliotek taggen tilhører
	
	
	Det finnes flere ...
	
	_____________________________________________________________________________________________________________________
	
	
	JSP Standard Tag Library (JSTL)
	
	
	Dette er et bibliotek av JSP-tagger til ulike formål (vi ser kun på noen få i core-pakken).
	JSTL er ikke automatisk inkludert i Spring, så bruk av JSTL krever også import i pom.xml-filen.
	
	
	JSTL-direktiv:			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	JSTL dependency:		<dependency>
									<groupId>javax.servlet</groupId>
									<artifactId>jstl</artifactId>
							</dependency>
	
	_____________________________________________________________________________________________________________________
	
	
	JSTL-tagger
	
	
	<c:set .../>						Lokal hjelpevariabel i JSP-siden
	
	<c:if test= .../>					If-setning (ofte bedre å bruke den ternære operatoren i EL)
	
	<c:choose .../>						If-else-setninger (bruk heller den ternære operatoren i EL)
	  <c:when test= ...s/>
	  <c:otherwise />
	
	<c:foreach var= items= .../>		Utvidet for-løkke
	
	<c:foreach var= begin= end= .../>	Vanlig for-løkke
	
	<c:out .../>						Viser frem et resultat + Ufarliggjør bruker-input
	
	
	En ting som er litt skummelt når man skal lage dynamiske web-sider,
	er å vise frem data på web-siden som brukeren har tastet inn.
	I dette tilfellet dreier det seg i hovedsak om brukernavnet som tastes inn (se webshopView.jsp).
	
	Dersom validering av bruker-input kun innebærer at f.eks. brukernavnet ikke er null,
	og er lengre enn tre tegn, så kan brukeren egentlig skrive inn hva som helst.
	Dette kan være et stort sikkerhetsproblem.
	
	På slike steder er det f.eks. vanlig å skrive inn JavaScript for å hacke systemet.
	Brukeren kan lage et et helt JavaScript-program som legges inn der brukernavnet egentlig skal inn.
	Dette gjøres på en slik måte at når web-siden genereres, så kjøres JavaScriptet.
	
	I dette tilfellet har vi ikke vært dumme nok til å hente data i databasen basert på dette brukernavnet.
	Hadde dette derimot vært tilfellet, kunne brukeren f.eks. skrevet inn SQL-kode,
	og på denne måten herpe hele databasen.
	
	Hvis vi tillater brukere å skrive inn data ganske fritt på web-siden, og vi skal vise dette frem etterpå,
	bør vi altså gjøre noe med denne input-teksten slik at den ikke kan gjøre skumle ting på serveren.
	
	Det å "escape" noe betyr i denne konteksten å gjøre om alle typer tegn som kan være problematiske til koder.
	Her gjøres slike tegn om til HTML-koder, slik at HTML-siden fortsatt vil vise akkuratt det brukeren tastet inn.
	
	Bruker-input skal her kun tolkes som ren tekst (String).
	I webshopView.jsp bruker vi JSTL-taggen <c:out.../> for å "escape" alle andre mulige tolkninger, slik:
	
	${username}    -->    <c:out value="${username}"/>
	
	_____________________________________________________________________________________________________________________
	
	
	JSP Expression Language (EL)
	
	
	Vi setter opp JSP-sider for å kunne generere HTML-sider dynamisk basert på dynamiske data,
	og prinsippet om MVC innebærer at data på forhånd er klargjort av controllerne.
	
	Vi henter disse dataene inn i JSP-siden fra to typer steder:
	1. Attributter i ulike scopes (page, request, session, application)
	2. Direkte fra requesten (parametre, headere)
	
	JSP prøver å finne den "nærmeste" attributten, dvs. leter i skopene listet over i prioritert rekkefølge.
	
	I teorien kan vi også la JSP-sider hente data fra «eksterne» kilder som f.eks. en database,
	men litt av poenget med MVC er å unngå dette. Vi ønsker minst mulig Java-kode i JSP-sider!
	
	
	Så! JSP Expression Language, eller EL-uttrykk, er det vi bruker for å hente data inn i JSP-sider.
	Dette minispråket har en enkel og trygg syntaks, og kan også brukes til å gjøre enkle beregninger.
	
	Mens tag-bibliotekene handler om struktur og logikk, så handler EL-uttrykk om dataene.
	EL-uttrykk lar oss altså på en enkel måte sette inn dynamiske elementer i siden.
	
	_____________________________________________________________________________________________________________________
	
	
	EL-uttrykk
	
	
	${x}									Gir attributten x (leter gjennom scopes)
	
	${x.y}									Gir egenskapen y til attributten x (NB! x må være en JavaBean)
	
	${param.a}								Gir request-parameter a
	
	${header.b}								Gir request-header b
	
	${3+4}									EL kan også utføre beregninger
	
	${p.alder >= 18}						inkl. logiske uttrykk
	
	${p.alder >= 18 ? "voksen" : "barn"}	Ternær if-else-operator (true gir "voksen", false gir "barn")
	
	${tab[i]}								Gir verdien fra tab[i]
	
	${liste[i]}								Gir verdien fra liste[i]
	
	${map.key}								Gir verdien som er mappet til key...
	
	${map["key"]}							... alt. skrivemåte
	
	
	EL har også automatisk konvertering fra null til "" (f.eks. ved utskrift),
	og flere operatorer: ==/eq, !=/ne, </le, &&/and, empty, osv...
	
	_____________________________________________________________________________________________________________________
	
*/	

