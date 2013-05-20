package de.professional_webworkx.jee6.SessionServlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * Eclipse hat uns bei der Erstellung des Servlets etwas unter die Arme gegriffen, so dass wir
 * nicht den ganzen Code selber schreiben müssen.
 * Zur Abarbeitung unserer Anfragen gibt es eine doGet() und doPost() Methode, die angesprochen
 * werden, je nachdem wie der Client auf unser Servlet zugreift.
 * Zur Erleichterung basteln wir uns nun noch eine Methode namens "processRequest()", die sowohl
 * auf doPost() als auch doGet() reagieren kann und etwas tut. Dieses Etwas bauen wir uns gleich zusammen.
 */

/*
 * Wie ihr gesehen habt, hat das Speichern meines Namens in dem SessionObject funktioniert und als ich 
 * zum Zweiten Mal auf die Seite kam und meinen Namen eingegeben habe, hat mit das Servlet
 * gleich anders begrüßt.
 * 
 */
/*
 * Ich werde das Project auf GitHub.com laden, dann kann der Sourcecode bei Bedarf runtergeladen 
 * werden. Viel Spaß und herzlichen Dank fürs Anschauen.
 * Einen schönen Pfingstmontag noch!
 * Ach ja, den Pfad zum Git Repo schreibe ich dann bei Youtube in die Beschreibung rein.
 */
/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * processRequest(HttpServletRequest request, HttpServletResponse response)
     * @throws IOException 
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	
    	String userName = null;
    	HttpSession session = null;
    	// das Weiterleiten an diese Methode ist bisher nichts neues, ich wollte es aber
    	// zur Wiederholung einfach noch einmal zeigen..
    	
    	// Kurzer Sprung nach oben, beginnen wir uns die Antwort (Response) zusammen zu basteln
    	response.setContentType("text/html");
    	
    	// Damit wir etwas in den Browser schreiben können, brauchen wir noch einen PrintWriter
    	PrintWriter out = response.getWriter();
    	
    	// einfache HTML Seite, mit dem üblichen HTML-Vorspann
    	out.write("<html>");
    	out.write("<head>");
    	out.write("<title>SessionHandling</title>");
    	out.write("</head>");
    	out.write("<body>");
    	
    	/*
    	 * So, nun schauen wir einfach mal nach, ob unser Benutzer schon einmal seinen Namen eingegeben hat 
    	 */
    	// Session-Object vom request-Object holen
    	session = request.getSession();
    	// Hier ist ein Cast auf String notwendig, da es bei der Entwicklung von HttpSession noch keine
    	// Generics gab und wir beim Zugriff auf getAttribute() ein Object zurückgeliefert bekommen.
    	userName = (String) session.getAttribute("userName");
    	// prüfen ob der Username != null ist UND ob nach dem Entfernen der Leerzeichen 
    	// hinten und vorn der Username ebenfalls nicht leer ist
    	if(userName != null && !userName.trim().isEmpty()) {
    		out.write("<h1>Hallo " + userName + ", schön dich wieder zu sehen</h1>");
    	}
    	// html Tags immer schön wieder schließen ;)
    	else {
    		userName = request.getParameter("userName");
    		session.setAttribute("userName", userName);
    		out.write("<h1>Hallo " + userName + ", herzlich willkommen zu deinem 1. Besuch</h1>");
    	}
    	
    	out.write("</body>");
    	out.write("</html>");

    	// Damit ist das Servlet fertig, kommen wir zur JSP Seite.
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bei Aufruf von doGet() wird dieser Aufruf an processRequest() weitergereicht...
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// bei Aufruf von doPost() wird dieser Aufruf an processRequest() weitergereicht...
		processRequest(request, response);
	}

}
