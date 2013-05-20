<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rezept-Verwaltung 0.1</title>
</head>
<body>
	<h2>Willkommen in meiner kleinen Rezepte-Verwaltung</h2>
	<hr/>
	<form methode="post" action="RecipeServlet">
	<strong>Zutat: </strong>&nbsp; <input type="text" name="ingredient"/><br/>
	<input type="submit" value="hinzuf&uuml;gen"/>
	</form>
	<hr/>
	<h3>Zutatenliste</h3>
	<!-- 
		Im Moment haben wir eine kleine Seite, die uns ein einfaches Formular präsentiert
		in das wir Zutaten eintragen können und mittels Post an das Servlet schicken, allerdings
		muss die action auch angegeben werden, sonst klappts nicht.
		Um die Zutatenliste anzuzeigen brauchen wir nun unser Session-Object und die Liste selbst,
		hierzu verwenden wir ein Scriptlet...
		Weil wir in einer JSP und damit quasi ausserhalb der Java Welt sind, müssen wir den Import
		des List-Interfaces über eine @page import Derektive erledigen.
	 -->
	 <%
	 	List<String> ingredients = (List<String>) session.getAttribute("recipeList");
	 	if(ingredients == null) {
	 %>
	 <!-- 
	 	Ist die Liste noch leer (null) noch nicht initialisiert, soll NullPointerException
	 	vermieden werden, daher prüfen wir auf Null und zeigen an, das noch keine Element auf
	 	der Liste sind.	
	 -->
	 	<strong>derzeit sind noch keine Zutanten auf der Liste</strong>
	 	<ul>
	 <%
	 	}else{
	 		for(String ingredient : ingredients) {
	 			%>
	 			<li><%=ingredient %></li>	
	 		<%}
	 	}
		 %>
	 	</ul>
	 	<!-- 
	 		Damit sind wir nun auch mit der JSP Datei fertig und es ist Zeit das 
	 		Projekt auf den Tomcat zu deployen, um einen Test durchzuführen.
	 		Also STRG + F11 und lost geht's...
	 		
	 	 -->
</body>
</html>