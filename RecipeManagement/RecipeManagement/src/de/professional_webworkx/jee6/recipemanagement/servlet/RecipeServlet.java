/**
 * @author Patrick Ott
 * @version 1.0
 * 
 * Diese Klasse ist unser Servlet, es nimmt Post- und Get-Anfragen der Clients
 * entgegen und reagiert mit der entsprechenden Methode (doGet(), doPost()) auf die
 * Anfrage.
 * 
 */
package de.professional_webworkx.jee6.recipemanagement.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class RecipeServlet
 */
// Die Annotation @WebServlet macht aus unserer "einfachen" Java Klasse ein Servlet und in 
// Klammern ist das URL Pattern (Muster) angegeben, auf den das Servlet im Browser angesprochen werden
// kann und dann reagiert
// Natürlich muss auch die Klasse HttpServlet beerbt werden.
@WebServlet({ "/RecipeServlet", "/recipe" })
public class RecipeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// unser Listenobject, zur Verwaltung der Zutaten für unser Rezept
	private List<String> ingredients;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecipeServlet() {
        super();
    }

    /**
     * processRequest(HttpServletRequest request, HttpServletResponse response)
     * @throws IOException 
     * @throws ServletException 
     * 
     */
    @SuppressWarnings("unchecked")
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// wie gewohnt unsere Arbeitsmethode
    	// Nun besorgen wir uns ein HttpSession Object vom Request-Object
    	HttpSession httpSession = request.getSession();
    	
    	// beim httpSession-Object nach unserer Liste fragen
    	// Casting auf List<String> nicht vergessen, aber Eclipse meckert ja auch
    	ingredients = (List<String>)httpSession.getAttribute("recipeList");
    	
    	// prüfen ob die Liste bereits initialisiert wurde, wenn nein, dann initialisieren
    	if(ingredients == null) {
    		// neue ArrayList<String> bauen
    		ingredients = new ArrayList<String>();
    		// eingegeben Zutat vom request-Object mit Hilfe von getParameter holen und 
    		// auf die Liste "schreiben"
    		ingredients.add(request.getParameter("ingredient"));
    		
    		// die Liste an die httpSession anhängen
    		httpSession.setAttribute("recipeList", ingredients);
    	} else {
    		// gibt's die Liste schon, können wir die Zutat einfach anfügen
    		ingredients.add(request.getParameter("ingredient"));
    	}
    	
    	/*
    	 * um nun die Zuständigkeit an die JSP Datei "abzugeben", benötigen wir einen
    	 * RequestDispatcher, den besorgen wir uns vom request-Object und geben ihm als
    	 * Ziel die jsp Datei. Anschließend wir ein Forward an diese Datei gemacht, indem
    	 * auch das request und response-Object übergeben werden.
    	 * Klingt schwieriger als es ist.
    	 */
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("recipe.jsp");
    	// beim "forwarden" kann eine Exception aftreten, deshalb sagen wir der processRequest Methode
    	// welche es ist, die geworfen werden kann. Da wir sie nicht behandeln sondern an den
    	// Aufrufer durchreichen, ist der Aufrufer für die Behandlund verantwortlich!
    	// Damit ist die Arbeit im Servlet soweit abgeschlossen. Bauen wir uns die JSP Seite zusammen.
    	dispatcher.forward(request, response);
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// weiterleiten der Anfrage an processRequest()
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
